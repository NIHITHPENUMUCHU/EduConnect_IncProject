package com.edutech.progressive.dto;

import java.util.Date;

public class StudentDTO {

    private Integer studentId;
    private String fullName;
    private Date dateOfBirth;
    private String contactNumber;
    private String email;
    private String address;

    // Optional auth fields for Day 13
    private String username;
    private String password;

    public Integer getStudentId() {
        return studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
        this.email = email != null ? email.trim() : null;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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