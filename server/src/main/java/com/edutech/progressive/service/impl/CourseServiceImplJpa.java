package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Course;
import com.edutech.progressive.entity.Teacher;
import com.edutech.progressive.repository.CourseRepository;
import com.edutech.progressive.service.CourseService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Primary
@Service
@Transactional
public class CourseServiceImplJpa implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImplJpa(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() throws Exception {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(int courseId) throws Exception {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public Integer addCourse(Course course) throws Exception {
        if (course.getTeacher() == null && course.getTeacherId() != null) {
            Teacher t = new Teacher();
            t.setTeacherId(course.getTeacherId());
            course.setTeacher(t);
        }
        return courseRepository.save(course).getCourseId();
    }

    @Override
    public void updateCourse(Course course) throws Exception {
        if (!courseRepository.existsById(course.getCourseId())) {
            throw new IllegalArgumentException("Course not found");
        }
        if (course.getTeacher() == null && course.getTeacherId() != null) {
            Teacher t = new Teacher();
            t.setTeacherId(course.getTeacherId());
            course.setTeacher(t);
        }
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(int courseId) throws Exception {
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<Course> getAllCourseByTeacherId(int teacherId) {
        return courseRepository.findAllByTeacherId(teacherId);
    }
}