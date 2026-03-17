package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Student;
import com.edutech.progressive.service.StudentService;

public class StudentServiceImplArraylist implements StudentService {

    private static List<Student> studentList = new ArrayList<>();

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentList); // return a copy to avoid external mutation
    }

    @Override
    public Integer addStudent(Student student) {
        studentList.add(student);
        return studentList.size();
    }

    @Override
    public List<Student> getAllStudentSortedByName() {
        List<Student> copy = new ArrayList<>(studentList);
        Collections.sort(copy); // uses Student.compareTo (fullName lexicographical)
        return copy;
    }

    @Override
    public void emptyArrayList() {
        studentList = new ArrayList<>();
    }

    @Override
    public void updateStudent(Student student) {
    }

    @Override
    public void deleteStudent(int studentId) {
    }

    @Override
    public Student getStudentById(int studentId) {
        return null;
    }
}
