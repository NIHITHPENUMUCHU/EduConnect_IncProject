package com.edutech.progressive.entity;

import java.util.Date;

public class Student {

    private int studentId;
    private String fullName;
    private Date dateOfBirth;
    private String contactNumber;
    private String email;
    private String address;

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(int studentId, String fullName, Date dateOfBirth, String contactNumber, String email, String address) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
    }

    // Getters
    public int getStudentId() {
        return studentId;
    }
    public String getFullName() {
        return fullName;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }

    // Setters
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}