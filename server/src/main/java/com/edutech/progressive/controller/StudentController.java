package com.edutech.progressive.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Student;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return null; // Day 4 placeholder
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable int studentId) {
        return null; // Day 4 placeholder
    }

    @PostMapping
    public ResponseEntity<Integer> addStudent(@RequestBody Student student) {
        return null; // Day 4 placeholder
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable int studentId, @RequestBody Student student) {
        return null; // Day 4 placeholder
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) {
        return null; // Day 4 placeholder
    }

    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Student>> getAllStudentFromArrayList() {
        return null; // Day 4 placeholder
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Void> addStudentToArrayList() {
        return null; // Day 4 placeholder
    }

    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Student>> getAllStudentSortedByNameFromArrayList() {
        return null; // Day 4 placeholder
    }
}