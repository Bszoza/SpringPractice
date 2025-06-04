package org.example.javaspringmavenpractice.courses.dto;
import org.example.javaspringmavenpractice.courses.model.Course;
import org.example.javaspringmavenpractice.courses.model.User;

import java.time.LocalDate;

public class EnrollmentsDTO {
    private User user;
    private Course course;
    private LocalDate enrollmentDate;
    private Boolean completed;

    public EnrollmentsDTO() {
    }

    public EnrollmentsDTO(User user, Course course, LocalDate enrollmentDate, Boolean completed) {
        this.user = user;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.completed = completed;
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

    public void setCourse(Course course) {
        this.course = course;
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

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "EnrollmentsDTO{" +
                "user=" + user +
                ", course=" + course +
                ", enrollmentDate=" + enrollmentDate +
                ", completed=" + completed +
                '}';
    }
}
