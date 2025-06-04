package org.example.javaspringmavenpractice.courses.repository;

import org.example.javaspringmavenpractice.courses.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
