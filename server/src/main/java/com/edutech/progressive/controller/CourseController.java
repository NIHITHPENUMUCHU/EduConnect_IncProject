package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Course;
import com.edutech.progressive.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() throws Exception {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable int courseId) throws Exception {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @PostMapping
    public ResponseEntity<Integer> addCourse(@RequestBody Course course) throws Exception {
        Integer id = courseService.addCourse(course);
        return ResponseEntity.status(201).body(id);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Void> updateCourse(@PathVariable int courseId,
                                             @RequestBody Course course) throws Exception {
        course.setCourseId(courseId);
        courseService.updateCourse(course);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int courseId) throws Exception {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Course>> getAllCourseByTeacherId(@PathVariable int teacherId) {
        return ResponseEntity.ok(courseService.getAllCourseByTeacherId(teacherId));
    }
}
