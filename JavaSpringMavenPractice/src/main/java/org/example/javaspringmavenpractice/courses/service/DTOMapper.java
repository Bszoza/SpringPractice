package org.example.javaspringmavenpractice.courses.service;

import org.example.javaspringmavenpractice.courses.dto.CourseDTO;
import org.example.javaspringmavenpractice.courses.dto.UserDTO;
import org.example.javaspringmavenpractice.courses.model.Course;
import org.example.javaspringmavenpractice.courses.model.Enrollment;
import org.example.javaspringmavenpractice.courses.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOMapper {
    public List<UserDTO> userToDTO(List<User> users) {
        List<UserDTO> userToDTO = new ArrayList<>();
        for (User user : users) {
            List<Enrollment> enrollments = user.getCourses();
            List<Course> courses = new ArrayList<>();
            for (Enrollment enrollment : enrollments) {
                courses.add(enrollment.getCourse());
            }
            List<CourseDTO> courseDTOs = courseToDTO(courses);
            userToDTO.add(new UserDTO(user.getId(), user.getNickname(), courseDTOs));
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
