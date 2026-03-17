package com.edutech.progressive.entity;

public class Teacher {

    private int teacherId;
    private String fullName;
    private String subject;
    private String contactNumber;
    private String email;
    private int yearsOfExperience;

    // Default constructor
    public Teacher() {
    }

    // Parameterized constructor
    public Teacher(int teacherId, String fullName, String subject, String contactNumber, String email, int yearsOfExperience) {
        this.teacherId = teacherId;
        this.fullName = fullName;
        this.subject = subject;
        this.contactNumber = contactNumber;
        this.email = email;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getters
    public int getTeacherId() {
        return teacherId;
    }
    public String getFullName() {
        return fullName;
    }
    public String getSubject() {
        return subject;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public String getEmail() {
        return email;
    }
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    // Setters
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}