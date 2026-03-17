package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Teacher;
import com.edutech.progressive.service.TeacherService;

public class TeacherServiceImplArraylist implements TeacherService {

    private static List<Teacher> teacherList = new ArrayList<>();

    @Override
    public List<Teacher> getAllTeachers() {
        return new ArrayList<>(teacherList); 
    }

    @Override
    public Integer addTeacher(Teacher teacher) {
        teacherList.add(teacher);
        return teacherList.size();
    }

    @Override
    public List<Teacher> getTeacherSortedByExperience() {
        List<Teacher> copy = new ArrayList<>(teacherList);
        Collections.sort(copy); 
        return copy;
    }

    @Override
    public void emptyArrayList() {
        teacherList = new ArrayList<>();
    }

    @Override
    public void updateTeacher(Teacher teacher) {
    }

    @Override
    public void deleteTeacher(int teacherId) {
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        return null;
    }
}