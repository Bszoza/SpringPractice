package org.example.javaspringmavenpractice.courses.service;

import org.example.javaspringmavenpractice.courses.controller.CoursesController;
import org.example.javaspringmavenpractice.courses.dto.CourseDTO;
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
    private final CoursesController coursesController;
    private InstructorRepository instructorRepository;
    public CoursesService(UserRepository userRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository,
                          CoursesController coursesController, InstructorRepository instructorRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.coursesController = coursesController;
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

    public List<User> getUsersOnCourse(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        List<User> users = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourse().getId().equals(courseId)) {
                users.add(enrollment.getUser());
            }
        }

        return users;
    }

    public List<CourseDTO> getCoursesByInstructor(Long instructorId) {
        return instructorRepository.findById(instructorId)
                .map(instructor -> courseToDTO(instructor.getCourses()))
                .orElse(new ArrayList<>());
    }

    public List<CourseDTO> courseToDTO(List<Course> courses) {
        List<CourseDTO> courseToDTO = new ArrayList<>();
        for(Course course : courses) {
            courseToDTO.add(new CourseDTO(course.getName(), course.getDescription(), course.getHours()));
        }
        return courseToDTO;
    }
}
