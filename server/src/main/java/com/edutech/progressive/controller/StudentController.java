package com.edutech.progressive.controller;

import com.edutech.progressive.dto.StudentDTO;
import com.edutech.progressive.entity.Student;
import com.edutech.progressive.exception.StudentAlreadyExistsException;
import com.edutech.progressive.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Day 6/8 endpoints (kept)
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            return ResponseEntity.ok(studentService.getAllStudents());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable int studentId) {
        try {
            return ResponseEntity.ok(studentService.getStudentById(studentId));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addStudent(@RequestBody Student student) {
        try {
            Integer id = studentService.addStudent(student);
            return ResponseEntity.status(201).body(id);
        } catch (StudentAlreadyExistsException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable int studentId,
                                              @RequestBody Student student) {
        try {
            student.setStudentId(studentId);
            studentService.updateStudent(student);
            return ResponseEntity.ok().build();
        } catch (StudentAlreadyExistsException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) {
        try {
            studentService.deleteStudent(studentId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    // Day 13: Modify via DTO
    @PutMapping("/modify")
    public ResponseEntity<Void> modifyStudentDetails(@RequestBody StudentDTO dto) {
        try {
            studentService.modifyStudentDetails(dto);
            return ResponseEntity.ok().build();
        } catch (StudentAlreadyExistsException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}