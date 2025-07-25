package org.example.javaspringmavenpractice.courses.repository;

import org.example.javaspringmavenpractice.courses.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
   Optional <List<Enrollment>> findByCourseId(Long courseId);
   Optional<List<Enrollment>> findByUserId(Long userId);
}
