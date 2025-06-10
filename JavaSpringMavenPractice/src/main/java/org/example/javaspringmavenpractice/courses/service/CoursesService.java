package org.example.javaspringmavenpractice.courses.service;

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
import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private InstructorRepository instructorRepository;

    public CoursesService(UserRepository userRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository,
                           InstructorRepository instructorRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.instructorRepository = instructorRepository;
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courseToDTO(courses);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveEnrollment(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }

    public List<UserDTO> getUsersOnCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found"));
        Optional<List<Enrollment>> enrollments = enrollmentRepository.findByCourseId(courseId);
        if(enrollments.isPresent()) {

        List<User> users = enrollments.get().stream().map(Enrollment::getUser).toList();
            return userToDTO(users);
        }else throw new EntityNotFoundException("Course not found");

    }

    public List<CourseDTO> getCoursesByInstructor(Long instructorId) {
        return instructorRepository.findById(instructorId)
                .map(instructor -> courseToDTO(instructor.getCourses()))
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }

    public List<CourseDTO> getCoursesOfUser(Long userId) {
        Optional<List<Enrollment>> courseEnrollmentList = enrollmentRepository.findByUserId(userId);
        if (courseEnrollmentList.isPresent()) {
            List<Course> courseList = new ArrayList<>();
            for (Enrollment enrollment : courseEnrollmentList.get()) {
                courseList.add(enrollment.getCourse());
            }
            return courseToDTO(courseList);
        } else throw new EntityNotFoundException("Course not found");
    }

    public List<UserDTO> userToDTO(List<User> users) {
        List<UserDTO> userToDTO = new ArrayList<>();
        for(User user : users){
            List<Enrollment> enrollments = user.getCourses();
            List<Course> courses = new ArrayList<>();
            for(Enrollment enrollment : enrollments){
                courses.add(enrollment.getCourse());
            }
            List<CourseDTO> courseDTOs = courseToDTO(courses);
            userToDTO.add(new UserDTO(user.getId(),user.getNickname(),courseDTOs));
        }
        return userToDTO;
    }

    public List<CourseDTO> courseToDTO(List<Course> courses) {
        List<CourseDTO> courseToDTO = new ArrayList<>();
        for (Course course : courses) {
            courseToDTO.add(new CourseDTO(course.getName(), course.getDescription(), course.getHours()));
        }
        return courseToDTO;
    }

}
