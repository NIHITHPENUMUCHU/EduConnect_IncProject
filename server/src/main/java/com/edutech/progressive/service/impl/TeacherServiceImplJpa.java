package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Teacher;
import com.edutech.progressive.exception.TeacherAlreadyExistsException;
import com.edutech.progressive.repository.CourseRepository;
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
    private final CourseRepository courseRepository;

    // Used by tests
    public TeacherServiceImplJpa(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = null;
    }

    // Used by Spring
    @Autowired
    public TeacherServiceImplJpa(TeacherRepository teacherRepository,
                                 CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional(readOnly = true)
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

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> getTeacherSortedByExperience() throws Exception {
        List<Teacher> list = teacherRepository.findAll();
        list.sort(Comparator.comparingInt(Teacher::getYearsOfExperience));
        return list;
    }

    @Override
    public void updateTeacher(Teacher teacher) throws Exception {
        if (teacher.getEmail() != null) {
            Teacher exists = teacherRepository.findByEmail(teacher.getEmail());

            // ✅ FIX: primitive int comparison
            if (exists != null && exists.getTeacherId() != teacher.getTeacherId()) {
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

    @Override
    @Transactional(readOnly = true)
    public Teacher getTeacherById(int teacherId) throws Exception {
        Teacher t = teacherRepository.findByTeacherId(teacherId);
        if (t != null) return t;
        return teacherRepository.findById(teacherId).orElse(null);
    }
}