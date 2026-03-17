package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Course;
import com.edutech.progressive.entity.Teacher;
import com.edutech.progressive.repository.CourseRepository;
import com.edutech.progressive.repository.EnrollmentRepository;
import com.edutech.progressive.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Primary
@Service
@Transactional
public class CourseServiceImplJpa implements CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository; // optional, may be null in tests

    // Constructor used by some tests (only CourseRepository passed)
    public CourseServiceImplJpa(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = null;
    }

    // Constructor used by Spring (full wiring)
    @Autowired
    public CourseServiceImplJpa(CourseRepository courseRepository,
                                EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
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
                .orElseThrow(() -> new NoSuchElementException("Course not found with id: " + courseId));
    }

    @Override
    public Integer addCourse(Course course) throws Exception {
        // Day-9: prevent duplicate course name
        if (course.getCourseName() != null) {
            Course existing = courseRepository.findByCourseName(course.getCourseName());
            if (existing != null) {
                throw new IllegalArgumentException("Course already exists with name: " + course.getCourseName());
            }
        }

        // Attach teacher if only teacherId is given
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
            throw new NoSuchElementException("Course not found with id: " + course.getCourseId());
        }

        // Day-9: duplicate course name on update (exclude self)
        if (course.getCourseName() != null) {
            Course byName = courseRepository.findByCourseName(course.getCourseName());
            if (byName != null && !byName.getCourseId().equals(course.getCourseId())) {
                throw new IllegalArgumentException("Another course already exists with name: " + course.getCourseName());
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
            throw new NoSuchElementException("Course not found with id: " + courseId);
        }

        // Day-10: delete related enrollments first (if repo is wired)
        if (enrollmentRepository != null) {
            enrollmentRepository.deleteByCourseId(courseId);
        }

        courseRepository.deleteById(courseId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> getAllCourseByTeacherId(int teacherId) {
        return courseRepository.findAllByTeacherId(teacherId);
    }
}