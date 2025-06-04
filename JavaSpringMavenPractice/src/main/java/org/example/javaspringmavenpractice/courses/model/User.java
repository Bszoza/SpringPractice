package org.example.javaspringmavenpractice.courses.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Enrollment> courses;

    public User() {
    }

    public User(String nickname, List<Enrollment> courses) {
        this.nickname = nickname;
        this.courses = courses;
    }

    public List<Enrollment> getCourses() {
        return courses;
    }

    public void setCourses(List<Enrollment> courses) {
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", courses=" + courses +
                '}';
    }
}
