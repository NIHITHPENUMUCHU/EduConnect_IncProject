package com.edutech.progressive.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Teacher;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return null; // Day 4 placeholder
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int teacherId) {
        return null; // Day 4 placeholder
    }

    @PostMapping
    public ResponseEntity<Integer> addTeacher(@RequestBody Teacher teacher) {
        return null; // Day 4 placeholder
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<Void> updateTeacher(@PathVariable int teacherId, @RequestBody Teacher teacher) {
        return null; // Day 4 placeholder
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable int teacherId) {
        return null; // Day 4 placeholder
    }

    @GetMapping("/yearsofexperience")
    public ResponseEntity<List<Teacher>> getTeacherSortedByYearsOfExperience() {
        return null; // Day 4 placeholder
    }
}