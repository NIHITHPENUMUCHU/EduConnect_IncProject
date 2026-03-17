package com.edutech.progressive.service.impl;

import com.edutech.progressive.dao.TeacherDAO;
import com.edutech.progressive.entity.Teacher;
import com.edutech.progressive.service.TeacherService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeacherServiceImplJdbc implements TeacherService {

    private final TeacherDAO teacherDAO;

    public TeacherServiceImplJdbc(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public List<Teacher> getAllTeachers() throws Exception {
        try {
            return teacherDAO.getAllTeachers();
        } catch (SQLException e) {
            throw new Exception("Failed to retrieve teachers from database.", e);
        }
    }

    @Override
    public Integer addTeacher(Teacher teacher) throws Exception {
        try {
            return teacherDAO.addTeacher(teacher);
        } catch (SQLException e) {
            throw new Exception("Failed to add new teacher.", e);
        }
    }

    @Override
    public List<Teacher> getTeacherSortedByExperience() throws Exception {
        try {
            List<Teacher> list = new ArrayList<>(teacherDAO.getAllTeachers());
            list.sort(Comparator.comparingInt(Teacher::getYearsOfExperience));
            return list;
        } catch (SQLException e) {
            throw new Exception("Failed to retrieve teachers sorted by experience.", e);
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) throws Exception {
        try {
            teacherDAO.updateTeacher(teacher);
        } catch (SQLException e) {
            throw new Exception("Failed to update teacher with ID: " + teacher.getTeacherId(), e);
        }
    }

    @Override
    public void deleteTeacher(int teacherId) throws Exception {
        try {
            teacherDAO.deleteTeacher(teacherId);
        } catch (SQLException e) {
            throw new Exception("Failed to delete teacher with ID: " + teacherId, e);
        }
    }

    @Override
    public Teacher getTeacherById(int teacherId) throws Exception {
        try {
            return teacherDAO.getTeacherById(teacherId);
        } catch (SQLException e) {
            throw new Exception("Failed to retrieve teacher with ID: " + teacherId, e);
        }
    }

    @Override
    public void emptyArrayList() {
        // Not used in JDBC implementation
    }
}