package com.edutech.progressive.service.impl;

import com.edutech.progressive.dto.StudentDTO;
import com.edutech.progressive.entity.Student;
import com.edutech.progressive.entity.User;
import com.edutech.progressive.exception.StudentAlreadyExistsException;
import com.edutech.progressive.repository.AttendanceRepository;
import com.edutech.progressive.repository.EnrollmentRepository;
import com.edutech.progressive.repository.StudentRepository;
import com.edutech.progressive.repository.UserRepository;
import com.edutech.progressive.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Primary
@Service
@Transactional
public class StudentServiceImplJpa implements StudentService {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;   // optional in tests
    private final AttendanceRepository attendanceRepository;   // optional in tests
    private final UserRepository userRepository;               // optional in tests
    private final PasswordEncoder passwordEncoder;             // optional in tests

    // Minimal constructor (used by some unit tests)
    public StudentServiceImplJpa(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = null;
        this.attendanceRepository = null;
        this.userRepository = null;
        this.passwordEncoder = null;
    }

    // Extended constructor (used by Spring)
    @Autowired
    public StudentServiceImplJpa(StudentRepository studentRepository,
                                 EnrollmentRepository enrollmentRepository,
                                 AttendanceRepository attendanceRepository,
                                 UserRepository userRepository,
                                 PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        if (enrollmentRepository != null) {
            enrollmentRepository.deleteByStudentId(studentId);
        }
        if (attendanceRepository != null) {
            attendanceRepository.deleteByStudentId(studentId);
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional(readOnly = true)
    @Override
    public Student getStudentById(int studentId) throws Exception {
        return studentRepository.findById(studentId).orElse(null);
    }

    /**
     * Day 13: Modify student + related user (if present), with validations.
     */
    @Override
    public void modifyStudentDetails(StudentDTO studentDTO) {
        if (studentDTO == null || studentDTO.getStudentId() == null) {
            throw new IllegalArgumentException("Invalid student payload (missing id).");
        }

        Student student = studentRepository.findById(studentDTO.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studentDTO.getStudentId()));

        // Email duplicate validation (exclude self)
        if (studentDTO.getEmail() != null) {
            Student existsByEmail = studentRepository.findByEmail(studentDTO.getEmail());
            if (existsByEmail != null && existsByEmail.getStudentId() != studentDTO.getStudentId()) {
                throw new StudentAlreadyExistsException(
                        "Another student already exists with email: " + studentDTO.getEmail()
                );
            }
        }

        // Map only non-null fields
        if (studentDTO.getFullName() != null) {
            student.setFullName(studentDTO.getFullName());
        }
        if (studentDTO.getDateOfBirth() != null) {
            student.setDateOfBirth(studentDTO.getDateOfBirth());
        }
        if (studentDTO.getContactNumber() != null) {
            student.setContactNumber(studentDTO.getContactNumber());
        }
        if (studentDTO.getEmail() != null) {
            student.setEmail(studentDTO.getEmail());
        }
        if (studentDTO.getAddress() != null) {
            student.setAddress(studentDTO.getAddress());
        }

        studentRepository.save(student);

        // Optionally update linked user if repositories are wired
        if (userRepository != null) {
            User user = userRepository.findByStudent_StudentId(studentDTO.getStudentId());
            if (user != null) {
                // Username update with duplicate check
                if (studentDTO.getUsername() != null) {
                    User byUsername = userRepository.findByUsername(studentDTO.getUsername());
                    if (byUsername != null && !byUsername.getUserId().equals(user.getUserId())) {
                        throw new IllegalArgumentException("Username already exists: " + studentDTO.getUsername());
                    }
                    user.setUsername(studentDTO.getUsername());
                }
                // Password update if provided
                if (studentDTO.getPassword() != null && !studentDTO.getPassword().isBlank()) {
                    if (passwordEncoder != null) {
                        user.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
                    } else {
                        user.setPassword(studentDTO.getPassword()); // fallback (tests might not wire encoder)
                    }
                }
                userRepository.save(user);
            }
        }
    }
}