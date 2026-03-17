package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Teacher;
import com.edutech.progressive.service.TeacherService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        copy.sort(Comparator.comparingInt(Teacher::getYearsOfExperience));
        return copy;
    }

    @Override
    public void emptyArrayList() {
        teacherList = new ArrayList<>();
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        // Not required for Day-2/7 ArrayList flow
    }

    @Override
    public void deleteTeacher(int teacherId) {
        // Not required for Day-2/7 ArrayList flow
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        // Not required for Day-2/7 ArrayList flow
        return null;
    }
}