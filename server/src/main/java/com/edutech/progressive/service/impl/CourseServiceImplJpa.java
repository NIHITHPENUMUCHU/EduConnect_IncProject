package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Course;
import com.edutech.progressive.repository.CourseRepository;
import com.edutech.progressive.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImplJpa implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImplJpa(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAllCourses() throws Exception {
        return courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
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
        if (course.getCourseId() == 0) {
            throw new IllegalArgumentException("courseId must be provided for update");
        }
        if (!courseRepository.existsById(course.getCourseId())) {
            throw new IllegalArgumentException("Course not found with id: " + course.getCourseId());
        }
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(int courseId) throws Exception {
        if (!courseRepository.existsById(courseId)) {
            // For Day 6, throwing is acceptable to indicate not found
            throw new IllegalArgumentException("Course not found with id: " + courseId);
        }
        courseRepository.deleteById(courseId);
    }

    // Day 6: No repository query method yet; filter in-memory
    @Override
    @Transactional(readOnly = true)
    public List<Course> getAllCourseByTeacherId(int teacherId) {
        return courseRepository.findAll()
                .stream()
                .filter(c -> c.getTeacherId() == teacherId)
                .collect(Collectors.toList());
    }
}

