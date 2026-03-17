package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Course;
import com.edutech.progressive.entity.Teacher;
import com.edutech.progressive.exception.CourseAlreadyExistsException;
import com.edutech.progressive.exception.CourseNotFoundException;
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

    @Transactional(readOnly = true)
    @Override
    public List<Course> getAllCourses() throws Exception {
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Course getCourseById(int courseId) throws Exception {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));
    }

    @Override
    public Integer addCourse(Course course) throws Exception {
        // Duplicate name check
        if (course.getCourseName() != null) {
            Course existing = courseRepository.findByCourseName(course.getCourseName());
            if (existing != null) {
                throw new CourseAlreadyExistsException(
                        "Course already exists with name: " + course.getCourseName());
            }
        }

        // Attach teacher by id if only teacherId is present
        if (course.getTeacher() == null && course.getTeacherId() != null) {
            Teacher t = new Teacher();
            t.setTeacherId(course.getTeacherId());
            course.setTeacher(t);
        }

        return courseRepository.save(course).getCourseId();
    }

    @Override
    public void updateCourse(Course course) throws Exception {
        if (course.getCourseId() == null || !courseRepository.existsById(course.getCourseId())) {
            throw new CourseNotFoundException("Course not found with id: " + course.getCourseId());
        }

        // Check duplicate name against other records
        if (course.getCourseName() != null) {
            Course byName = courseRepository.findByCourseName(course.getCourseName());
            if (byName != null && !byName.getCourseId().equals(course.getCourseId())) {
                throw new CourseAlreadyExistsException(
                        "Another course already exists with name: " + course.getCourseName());
            }
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
        if (!courseRepository.existsById(courseId)) {
            throw new CourseNotFoundException("Course not found with id: " + courseId);
        }
        courseRepository.deleteById(courseId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> getAllCourseByTeacherId(int teacherId) {
        return courseRepository.findAllByTeacherId(teacherId);
    }
}