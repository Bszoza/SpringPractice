package org.example.javaspringmavenpractice.courses.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.example.javaspringmavenpractice.courses.controller.CoursesController;
import org.example.javaspringmavenpractice.courses.dto.CourseDTO;
import org.example.javaspringmavenpractice.courses.dto.UserDTO;
import org.example.javaspringmavenpractice.courses.model.Course;
import org.example.javaspringmavenpractice.courses.model.Enrollment;
import org.example.javaspringmavenpractice.courses.model.User;
import org.example.javaspringmavenpractice.courses.repository.CourseRepository;
import org.example.javaspringmavenpractice.courses.repository.EnrollmentRepository;
import org.example.javaspringmavenpractice.courses.repository.InstructorRepository;
import org.example.javaspringmavenpractice.courses.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private InstructorRepository instructorRepository;
    private DTOMapper mapper;

    public CoursesService(UserRepository userRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository,
                          InstructorRepository instructorRepository, DTOMapper mapper) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.instructorRepository = instructorRepository;
        this.mapper = mapper;
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return mapper.courseToDTO(courses);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveEnrollment(Enrollment enrollment) {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        for(Enrollment enroll : enrollments) {
            if(enroll.equals(enrollment)) {
                throw new IllegalStateException("Enrollment already exists");
            }
        }
        enrollmentRepository.save(enrollment);
    }

    public List<UserDTO> getUsersOnCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found"));
        Optional<List<Enrollment>> enrollments = enrollmentRepository.findByCourseId(courseId);
        if (enrollments.isPresent()) {

            List<User> users = enrollments.get().stream().map(Enrollment::getUser).toList();
            return mapper.userToDTO(users);
        } else throw new EntityNotFoundException("Course not found");

    }

    public List<CourseDTO> getCoursesByInstructor(Long instructorId) {
        return instructorRepository.findById(instructorId)
                .map(instructor -> mapper.courseToDTO(instructor.getCourses()))
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }

    public List<CourseDTO> getCoursesOfUser(Long userId) {
        Optional<List<Enrollment>> courseEnrollmentList = enrollmentRepository.findByUserId(userId);
        if (courseEnrollmentList.isPresent()) {
            List<Course> courseList = new ArrayList<>();
            for (Enrollment enrollment : courseEnrollmentList.get()) {
                courseList.add(enrollment.getCourse());
            }
            return mapper.courseToDTO(courseList);
        } else throw new EntityNotFoundException("Course not found");
    }

    public void deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            if (user.get().getCourses().isEmpty()) {
                userRepository.delete(user.get());
            } else throw new IllegalStateException("Student still has courses");
        } else throw new EntityNotFoundException("User not found");
    }

    public void courseEdit(Long courseId, CourseDTO courseMod) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            course.get().setName(courseMod.getName());
            course.get().setDescription(courseMod.getDescription());
            course.get().setHours(courseMod.getHours());
            courseRepository.save(course.get());
        } else throw new EntityNotFoundException("Course not found");
    }

    public CourseDTO courseAndStudents(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            CourseDTO courseDTO = mapper.courseToDTO(List.of(course.get())).get(0);
            courseDTO.setUsers(mapper.userToDTO(courseRepository.getAllUser()));
            return courseDTO;
        } else throw new EntityNotFoundException("Course not found");
    }

}
