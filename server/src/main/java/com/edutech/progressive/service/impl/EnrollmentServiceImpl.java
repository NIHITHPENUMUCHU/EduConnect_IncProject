package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Course;
import com.edutech.progressive.entity.Enrollment;
import com.edutech.progressive.entity.Student;
import com.edutech.progressive.repository.EnrollmentRepository;
import com.edutech.progressive.service.EnrollmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public int createEnrollment(Enrollment enrollment) {
        if (enrollment == null || enrollment.getStudent() == null || enrollment.getCourse() == null) {
            throw new RuntimeException("Invalid enrollment payload.");
        }
        int studentId = enrollment.getStudent().getStudentId();
        int courseId = enrollment.getCourse().getCourseId();

        Optional<Enrollment> existing = enrollmentRepository
                .findByStudent_StudentIdAndCourse_CourseId(studentId, courseId);
        if (existing.isPresent()) {
            throw new RuntimeException("Student is already enrolled in this course.");
        }

        if (enrollment.getEnrollmentDate() == null) {
            enrollment.setEnrollmentDate(new Date());
        }
        Enrollment saved = enrollmentRepository.save(enrollment);
        return saved.getEnrollmentId();
    }

    @Override
    public void updateEnrollment(Enrollment updatedEnrollment) {
        if (updatedEnrollment == null || updatedEnrollment.getEnrollmentId() == null) {
            throw new RuntimeException("Invalid enrollment payload (missing ID).");
        }

        Enrollment current = enrollmentRepository.findById(updatedEnrollment.getEnrollmentId())
                .orElseThrow(() -> new RuntimeException("Enrollment not found."));

        // Check duplicate after update if student/course changed
        if (updatedEnrollment.getStudent() != null && updatedEnrollment.getCourse() != null) {
            int sId = updatedEnrollment.getStudent().getStudentId();
            int cId = updatedEnrollment.getCourse().getCourseId();
            Optional<Enrollment> dup = enrollmentRepository
                    .findByStudent_StudentIdAndCourse_CourseId(sId, cId);
            if (dup.isPresent() && !dup.get().getEnrollmentId()
                    .equals(updatedEnrollment.getEnrollmentId())) {
                throw new RuntimeException("Student is already enrolled in this course.");
            }
        }

        // Merge updates
        if (updatedEnrollment.getStudent() != null) {
            Student s = new Student();
            s.setStudentId(updatedEnrollment.getStudent().getStudentId());
            current.setStudent(s);
        }
        if (updatedEnrollment.getCourse() != null) {
            Course c = new Course();
            c.setCourseId(updatedEnrollment.getCourse().getCourseId());
            current.setCourse(c);
        }
        if (updatedEnrollment.getEnrollmentDate() != null) {
            current.setEnrollmentDate(updatedEnrollment.getEnrollmentDate());
        }

        enrollmentRepository.save(current);
    }

    @Transactional(readOnly = true)
    @Override
    public Enrollment getEnrollmentById(int enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found."));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Enrollment> getAllEnrollmentsByStudent(int studentId) {
        return enrollmentRepository.findAllByStudent_StudentId(studentId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Enrollment> getAllEnrollmentsByCourse(int courseId) {
        return enrollmentRepository.findAllByCourse_CourseId(courseId);
    }
}