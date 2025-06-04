package org.example.javaspringmavenpractice.courses.controller;

import org.example.javaspringmavenpractice.courses.dto.CourseDTO;
import org.example.javaspringmavenpractice.courses.model.Course;
import org.example.javaspringmavenpractice.courses.model.Enrollment;
import org.example.javaspringmavenpractice.courses.model.User;
import org.example.javaspringmavenpractice.courses.repository.CourseRepository;
import org.example.javaspringmavenpractice.courses.repository.UserRepository;
import org.example.javaspringmavenpractice.courses.service.CoursesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoursesController {
    private CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @PostMapping("/courses")
    public void postCourse(@RequestBody Course course) {
        coursesService.saveCourse(course);
    }

    @GetMapping("/courses")
    @ResponseBody
    public List<CourseDTO> getAllCourses() {
        return coursesService.getAllCourses();
    }

    @PostMapping("/users")
    public void postUser(@RequestBody User user) {
        coursesService.saveUser(user);
    }

    @PostMapping("/enrollments")
    public void postEnrollment(@RequestBody Enrollment enrollment) {
        coursesService.saveEnrollment(enrollment);
    }

    @GetMapping("/courses/{id}/users")
    public List<User> getUsersOnCourse(@PathVariable Long id) {
        return coursesService.getUsersOnCourse(id);
    }

    @GetMapping("/instructors/{id}/courses")
    public List<CourseDTO> getCoursesByInstructor(@PathVariable Long id) {
        return coursesService.getCoursesByInstructor(id);
    }



}
