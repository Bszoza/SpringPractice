package org.example.javaspringmavenpractice.courses.repository;

import org.example.javaspringmavenpractice.courses.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
