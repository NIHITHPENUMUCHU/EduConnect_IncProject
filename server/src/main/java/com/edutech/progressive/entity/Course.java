package com.edutech.progressive.entity;

public class Course {

    private int courseId;
    private String courseName;
    private String description;
    private int teacherId;

    // Default constructor
    public Course() {
    }

    // Parameterized constructor
    public Course(int courseId, String courseName, String description, int teacherId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.teacherId = teacherId;
    }

    // Getters
    public int getCourseId() {
        return courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getDescription() {
        return description;
    }
    public int getTeacherId() {
        return teacherId;
    }

    // Setters
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}