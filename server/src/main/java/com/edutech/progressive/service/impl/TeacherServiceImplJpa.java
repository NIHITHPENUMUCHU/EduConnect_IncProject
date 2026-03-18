package com.edutech.progressive.service.impl;

import com.edutech.progressive.dto.TeacherDTO;
import com.edutech.progressive.entity.Teacher;
import com.edutech.progressive.exception.TeacherAlreadyExistsException;
import com.edutech.progressive.repository.CourseRepository;
import com.edutech.progressive.repository.EnrollmentRepository;
import com.edutech.progressive.repository.TeacherRepository;
import com.edutech.progressive.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Primary
@Service
@Transactional
public class TeacherServiceImplJpa implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;        // may be null in tests
    private final EnrollmentRepository enrollmentRepository; // not strictly used here; kept consistent

    // For unit tests (TeacherRepository only)
    public TeacherServiceImplJpa(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = null;
        this.enrollmentRepository = null;
    }

    // For Spring (wider wiring if present)
    @Autowired
    public TeacherServiceImplJpa(TeacherRepository teacherRepository,
                                 CourseRepository courseRepository,
                                 EnrollmentRepository enrollmentRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> getAllTeachers() throws Exception {
        return teacherRepository.findAll();
    }

    @Override
    public Integer addTeacher(Teacher teacher) throws Exception {
        if (teacher.getEmail() != null) {
            Teacher exists = teacherRepository.findByEmail(teacher.getEmail());
            if (exists != null) {
                throw new TeacherAlreadyExistsException(
                        "Teacher already exists with email: " + teacher.getEmail());
            }
        }
        return teacherRepository.save(teacher).getTeacherId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> getTeacherSortedByExperience() throws Exception {
        List<Teacher> list = teacherRepository.findAll();
        list.sort(Comparator.comparingInt(Teacher::getYearsOfExperience));
        return list;
    }

    @Override
    public void updateTeacher(Teacher teacher) throws Exception {
        if (teacher.getEmail() != null) {
            Teacher exists = teacherRepository.findByEmail(teacher.getEmail());
            if (exists != null && !exists.getTeacherId().equals(teacher.getTeacherId())) {
                throw new TeacherAlreadyExistsException(
                        "Another teacher already exists with email: " + teacher.getEmail());
            }
        }
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(int teacherId) throws Exception {
        if (courseRepository != null) {
            courseRepository.deleteByTeacherId(teacherId);
        }
        teacherRepository.deleteById(teacherId);
    }

    @Transactional(readOnly = true)
    @Override
    public Teacher getTeacherById(int teacherId) throws Exception {
        Teacher t = teacherRepository.findByTeacherId(teacherId);
        if (t != null) return t;
        return teacherRepository.findById(teacherId).orElse(null);
    }

    /**
     * Day 13: Modify teacher fields via DTO (no user linkage required for Day 13).
     */
    public void modifyTeacherDetails(TeacherDTO teacherDTO) {
        if (teacherDTO == null || teacherDTO.getTeacherId() == null) {
            throw new IllegalArgumentException("Invalid teacher payload (missing id).");
        }

        Teacher teacher = teacherRepository.findById(teacherDTO.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found with id: " + teacherDTO.getTeacherId()));

        // Email duplicate validation (exclude self)
        if (teacherDTO.getEmail() != null) {
            Teacher existsByEmail = teacherRepository.findByEmail(teacherDTO.getEmail());
            if (existsByEmail != null && !existsByEmail.getTeacherId().equals(teacherDTO.getTeacherId())) {
                throw new TeacherAlreadyExistsException(
                        "Another teacher already exists with email: " + teacherDTO.getEmail());
            }
        }

        if (teacherDTO.getFullName() != null) {
            teacher.setFullName(teacherDTO.getFullName());
        }
        if (teacherDTO.getSubject() != null) {
            teacher.setSubject(teacherDTO.getSubject());
        }
        if (teacherDTO.getContactNumber() != null) {
            teacher.setContactNumber(teacherDTO.getContactNumber());
        }
        if (teacherDTO.getEmail() != null) {
            teacher.setEmail(teacherDTO.getEmail());
        }
        if (teacherDTO.getYearsOfExperience() != null) {
            teacher.setYearsOfExperience(teacherDTO.getYearsOfExperience());
        }

        teacherRepository.save(teacher);
    }
}