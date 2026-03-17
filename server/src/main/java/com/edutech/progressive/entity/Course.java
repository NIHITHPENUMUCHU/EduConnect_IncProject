package com.edutech.progressive.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "course")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "teacher"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    private String courseName;

    private String description;

    // Day-7: Many-to-One relationship with Teacher using FK teacher_id
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    /**
     * Read-only FK mirror so existing JDBC/DAO code can still do c.setTeacherId(...)
     * JPA will not write using this column from the entity (insertable/updatable = false).
     */
    @Column(name = "teacher_id", insertable = false, updatable = false)
    private Integer teacherId;

    public Course() {}

    public Course(Integer courseId, String courseName, String description) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
    }

    // Getters & setters

    public Integer getCourseId() {
        return courseId;
    }
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * Kept intentionally for Day-3 JDBC compatibility where DAO code calls setTeacherId(..).
     * Since the column is read-only for JPA, this does not affect persistence.
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}