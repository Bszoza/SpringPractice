package org.example.javaspringmavenpractice.courses.model;

import jakarta.persistence.*;

import java.time.LocalDate;

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

}
