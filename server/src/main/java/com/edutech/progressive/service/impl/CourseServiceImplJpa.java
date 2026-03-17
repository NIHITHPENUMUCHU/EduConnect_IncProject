package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Course;
import com.edutech.progressive.repository.CourseRepository;
import com.edutech.progressive.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImplJpa implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImplJpa(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> getAllCourses() throws Exception {
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Course getCourseById(int courseId) throws Exception {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public Integer addCourse(Course course) throws Exception {
        Course saved = courseRepository.save(course);
        return saved.getCourseId();
    }

    @Override
    public void updateCourse(Course course) throws Exception {
        if (!courseRepository.existsById(course.getCourseId())) {
            throw new IllegalArgumentException("Course not found with id: " + course.getCourseId());
        }
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(int courseId) throws Exception {
        if (!courseRepository.existsById(courseId)) {
            throw new IllegalArgumentException("Course not found with id: " + courseId);
        }
        courseRepository.deleteById(courseId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> getAllCourseByTeacherId(int teacherId) {
        return courseRepository.findAllByTeacherId(teacherId);
    }
}