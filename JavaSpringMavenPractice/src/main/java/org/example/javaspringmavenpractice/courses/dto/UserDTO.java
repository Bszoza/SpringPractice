package org.example.javaspringmavenpractice.courses.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String nickname;
    private List<CourseDTO> courses;

    public UserDTO() {
    }

    public UserDTO(Long id, String nickname, List<CourseDTO> courses) {
        this.id = id;
        this.nickname = nickname;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", courses=" + courses +
                '}';
    }
}
