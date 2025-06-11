package org.example.javaspringmavenpractice.courses.controller;

import org.example.javaspringmavenpractice.courses.dto.CourseDTO;
import org.example.javaspringmavenpractice.courses.dto.UserDTO;
import org.example.javaspringmavenpractice.courses.model.Course;
import org.example.javaspringmavenpractice.courses.model.Enrollment;
import org.example.javaspringmavenpractice.courses.model.User;
import org.example.javaspringmavenpractice.courses.service.CoursesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CoursesController {
    private final CoursesService coursesService;

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
    public List<UserDTO> getUsersOnCourse(@PathVariable Long id) {
        return coursesService.getUsersOnCourse(id);
    }

    @GetMapping("/instructors/{id}/courses")
    public List<CourseDTO> getCoursesByInstructor(@PathVariable Long id) {
        return coursesService.getCoursesByInstructor(id);
    }

    @GetMapping("/users/{id}/courses")
    public List<CourseDTO> getCoursesOfUser(@PathVariable Long id){
        return coursesService.getCoursesOfUser(id);
    }

    @DeleteMapping("/delete/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        coursesService.deleteUser(id);
    }

    @PutMapping("/courses/{id}")
    public void putCourse(@PathVariable Long id, @RequestBody CourseDTO course) {
        coursesService.courseEdit(id, course);
    }

    @GetMapping("/courses/{id}")
    public CourseDTO getCourseAndStudents(@PathVariable Long id) {
        return coursesService.courseAndStudents(id);
    }

}
