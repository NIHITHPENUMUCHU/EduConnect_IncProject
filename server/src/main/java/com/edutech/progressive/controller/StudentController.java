package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Student;
import com.edutech.progressive.service.StudentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    // IMPORTANT: We explicitly inject the JPA implementation to avoid bean ambiguity.
    private final StudentService studentService;

    public StudentController(@Qualifier("studentServiceImplJpa") StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /student --> 200
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() throws Exception {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // GET /student/sorted --> 200
    @GetMapping("/sorted")
    public ResponseEntity<List<Student>> getAllStudentsSortedByName() throws Exception {
        return ResponseEntity.ok(studentService.getAllStudentSortedByName());
    }

    // PUT /student/{studentId} --> 200
    @PutMapping("/{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable int studentId,
                                              @RequestBody Student student) throws Exception {
        student.setStudentId(studentId);
        studentService.updateStudent(student);
        return ResponseEntity.ok().build();
    }

    // DELETE /student/{studentId} --> 204
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) throws Exception {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}