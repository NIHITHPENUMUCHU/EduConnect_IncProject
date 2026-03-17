package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    // For Day 6: support courses by teacherId
    List<Course> findAllByTeacherId(int teacherId);
}