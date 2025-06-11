package org.example.javaspringmavenpractice.courses.repository;

import org.example.javaspringmavenpractice.courses.model.Course;
import org.example.javaspringmavenpractice.courses.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<User> getAllUser();
}
