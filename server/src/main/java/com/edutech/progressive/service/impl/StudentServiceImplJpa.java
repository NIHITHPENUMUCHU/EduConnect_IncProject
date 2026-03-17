package com.edutech.progressive.service.impl;

import com.edutech.progressive.dto.StudentDTO;
import com.edutech.progressive.entity.Student;
import com.edutech.progressive.exception.StudentAlreadyExistsException;
import com.edutech.progressive.repository.StudentRepository;
import com.edutech.progressive.service.StudentService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Primary
@Service
@Transactional
public class StudentServiceImplJpa implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImplJpa(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() throws Exception {
        return studentRepository.findAll();
    }

    @Override
    public Integer addStudent(Student student) throws Exception {
        if (student.getEmail() != null) {
            Student exists = studentRepository.findByEmail(student.getEmail());
            if (exists != null) {
                throw new StudentAlreadyExistsException(
                        "Student already exists with email: " + student.getEmail());
            }
        }
        return studentRepository.save(student).getStudentId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudentSortedByName() throws Exception {
        List<Student> list = studentRepository.findAll();
        list.sort(Comparator.comparing(
                s -> s.getFullName() == null ? "" : s.getFullName(),
                String.CASE_INSENSITIVE_ORDER
        ));
        return list;
    }

    @Override
    public void updateStudent(Student student) throws Exception {
        if (student.getEmail() != null) {
            Student exists = studentRepository.findByEmail(student.getEmail());

            // ✅ FIX: primitive int comparison
            if (exists != null && exists.getStudentId() != student.getStudentId()) {
                throw new StudentAlreadyExistsException(
                        "Another student already exists with email: " + student.getEmail());
            }
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int studentId) throws Exception {
        studentRepository.deleteById(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(int studentId) throws Exception {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public void modifyStudentDetails(StudentDTO studentDTO) {
        // Not required until Day 13
    }
}