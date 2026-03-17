package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Student;
import com.edutech.progressive.service.impl.StudentServiceImplArraylist;
import com.edutech.progressive.service.impl.StudentServiceImplJpa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImplJpa studentServiceJpa;
    private final StudentServiceImplArraylist studentServiceArrayList;

    public StudentController(StudentServiceImplJpa studentServiceJpa,
                             StudentServiceImplArraylist studentServiceArrayList) {
        this.studentServiceJpa = studentServiceJpa;
        this.studentServiceArrayList = studentServiceArrayList;
    }

    // GET /student
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentServiceJpa.getAllStudents());
    }

    // GET /student/{studentId}
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable int studentId) {
        Student s = studentServiceJpa.getStudentById(studentId);
        return (s == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(s);
    }

    // POST /student
    @PostMapping
    public ResponseEntity<Integer> addStudent(@RequestBody Student student) {
        Integer id = studentServiceJpa.addStudent(student);
        return ResponseEntity.created(URI.create("/student/" + id)).body(id);
    }

    // PUT /student/{studentId}
    @PutMapping("/{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable int studentId, @RequestBody Student student) {
        // enforce path ID
        student.setStudentId(studentId);

        // optional guard to return 404 if target doesn't exist
        if (studentServiceJpa.getStudentById(studentId) == null) {
            return ResponseEntity.notFound().build();
        }

        studentServiceJpa.updateStudent(student);
        return ResponseEntity.ok().build();
    }

    // DELETE /student/{studentId}
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) {
        if (studentServiceJpa.getStudentById(studentId) == null) {
            return ResponseEntity.notFound().build();
        }
        studentServiceJpa.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    // GET /student/fromArrayList
    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Student>> getAllStudentFromArrayList() {
        return ResponseEntity.ok(studentServiceArrayList.getAllStudents());
    }

    // GET /student/fromArrayList/sorted
    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Student>> getAllStudentSortedByNameFromArrayList() {
        return ResponseEntity.ok(studentServiceArrayList.getAllStudentSortedByName());
    }

    // POST /student/toArrayList
@PostMapping("/toArrayList")
public ResponseEntity<Integer> addStudentToArrayList(@RequestBody Student student) {
    Integer size = studentServiceArrayList.addStudent(student);
    return ResponseEntity.status(201).body(size);
}
}