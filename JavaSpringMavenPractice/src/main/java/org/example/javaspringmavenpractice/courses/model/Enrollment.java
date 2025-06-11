package org.example.javaspringmavenpractice.courses.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Course course;
    private LocalDate enrollmentDate;
    private Boolean completed;

    public Enrollment() {
    }

    public Enrollment(User user, Course course, LocalDate enrollmentDate, Boolean completed) {
        this.user = user;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course courses) {
        this.course = courses;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed0) {
        this.completed = completed0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(user, that.user) && Objects.equals(course, that.course) && Objects.equals(enrollmentDate, that.enrollmentDate) && Objects.equals(completed, that.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, course, enrollmentDate, completed);
    }

}
