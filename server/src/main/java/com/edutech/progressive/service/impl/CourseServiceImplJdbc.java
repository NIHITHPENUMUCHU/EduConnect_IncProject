package com.edutech.progressive.service.impl;

import com.edutech.progressive.dao.CourseDAO;
import com.edutech.progressive.entity.Course;
import com.edutech.progressive.service.CourseService;

import java.sql.SQLException;
import java.util.List;


public class CourseServiceImplJdbc implements CourseService {

    private final CourseDAO courseDAO;

    public CourseServiceImplJdbc(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Course> getAllCourses() throws Exception {
        try {
            return courseDAO.getAllCourses();
        } catch (SQLException e) {
            throw new Exception("Failed to retrieve courses", e);
        }
    }

    @Override
    public Course getCourseById(int courseId) throws Exception {
        try {
            return courseDAO.getCourseById(courseId);
        } catch (SQLException e) {
            throw new Exception("Failed to retrieve course", e);
        }
    }

    @Override
    public Integer addCourse(Course course) throws Exception {
        try {
            return courseDAO.addCourse(course);
        } catch (SQLException e) {
            throw new Exception("Failed to add course", e);
        }
    }

    @Override
    public void updateCourse(Course course) throws Exception {
        try {
            courseDAO.updateCourse(course);
        } catch (SQLException e) {
            throw new Exception("Failed to update course", e);
        }
    }

    @Override
    public void deleteCourse(int courseId) throws Exception {
        try {
            courseDAO.deleteCourse(courseId);
        } catch (SQLException e) {
            throw new Exception("Failed to delete course", e);
        }
    }

    @Override
    public List<Course> getAllCourseByTeacherId(int teacherId) {
        return null; // Not used in JDBC flow
    }
}