package com.edutech.progressive.service.impl;

import com.edutech.progressive.dto.StudentDTO;
import com.edutech.progressive.entity.Student;
import com.edutech.progressive.exception.StudentAlreadyExistsException;
import com.edutech.progressive.repository.EnrollmentRepository;
import com.edutech.progressive.repository.StudentRepository;
import com.edutech.progressive.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final EnrollmentRepository enrollmentRepository; // optional, may be null in tests

    // Constructor used by some tests (only StudentRepository passed)
    public StudentServiceImplJpa(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = null;
    }

    // Constructor used by Spring (full wiring)
    @Autowired
    public StudentServiceImplJpa(StudentRepository studentRepository,
                                 EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional(readOnly = true)
    @Override
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

    @Transactional(readOnly = true)
    @Override
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
            if (exists != null && exists.getStudentId() != student.getStudentId()) {
                throw new StudentAlreadyExistsException(
                        "Another student already exists with email: " + student.getEmail());
            }
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int studentId) throws Exception {
        // Day-10: also remove related enrollments (if repo is wired)
        if (enrollmentRepository != null) {
            enrollmentRepository.deleteByStudentId(studentId);
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional(readOnly = true)
    @Override
    public Student getStudentById(int studentId) throws Exception {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public void modifyStudentDetails(StudentDTO studentDTO) {
        // Day-13 feature — intentionally left empty for now
    }
}