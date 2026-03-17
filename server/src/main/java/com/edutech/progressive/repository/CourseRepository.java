package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findByCourseId(int courseId);

    Course findByCourseName(String courseName);

    List<Course> findAllByTeacherId(int teacherId);

    void deleteByTeacherId(int teacherId);
}