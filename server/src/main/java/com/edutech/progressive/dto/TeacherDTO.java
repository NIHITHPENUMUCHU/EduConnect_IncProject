package com.edutech.progressive.dto;

public class TeacherDTO {

    private Integer teacherId;
    private String fullName;
    private String subject;
    private String contactNumber;
    private String email;
    private Integer yearsOfExperience;

    // Optional auth fields in case future linkage is needed
    private String username;
    private String password;

    public Integer getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = (email != null ? email.trim() : null);
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }
    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

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
}