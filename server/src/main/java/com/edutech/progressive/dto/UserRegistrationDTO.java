package com.edutech.progressive.dto;

public class UserRegistrationDTO {

    private String username;
    private String password;
    private String role;
    private Integer studentId; // optional link if needed later

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = (username != null ? username.trim() : null);
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = (role != null ? role.trim() : null);
    }

    public Integer getStudentId() {
        return studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}