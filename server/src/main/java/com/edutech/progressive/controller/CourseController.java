package com.edutech.progressive.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Course;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @GetMapping
    public List<Course> getAllCourses() {
        return null; // Day 4 placeholder
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable int courseId) {
        return null; // Day 4 placeholder
    }

    @PostMapping
    public int addCourse(@RequestBody Course course) {
        return -1; // Day 4 placeholder
    }

    @PutMapping("/{courseId}")
    public void updateCourse(@PathVariable int courseId, @RequestBody Course course) {
        // Day 4 placeholder
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable int courseId) {
        // Day 4 placeholder
    }

    @GetMapping("/teacher/{teacherId}")
    public List<Course> getAllCourseByTeacherId(@PathVariable int teacherId) {
        return null; // Day 4 placeholder
    }
}