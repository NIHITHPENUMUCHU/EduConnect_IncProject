package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Student;
import com.edutech.progressive.service.StudentService;
import com.edutech.progressive.service.impl.StudentServiceImplArraylist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final StudentServiceImplArraylist arraylistService;

    public StudentController(StudentService studentService,
                             StudentServiceImplArraylist arraylistService) {
        this.studentService = studentService;
        this.arraylistService = arraylistService;
    }

    // ---------- JPA BASED APIs ----------

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() throws Exception {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable int studentId) throws Exception {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @PostMapping
    public ResponseEntity<Integer> addStudent(@RequestBody Student student) throws Exception {
        Integer id = studentService.addStudent(student);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable int studentId,
                                              @RequestBody Student student) throws Exception {
        student.setStudentId(studentId);
        studentService.updateStudent(student);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) throws Exception {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Student>> getAllStudentFromArrayList() {
        return ResponseEntity.ok(arraylistService.getAllStudents());
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addStudentToArrayList(@RequestBody Student student) {
        Integer size = arraylistService.addStudent(student);
        return ResponseEntity.status(201).body(size);
    }

    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Student>> getAllStudentSortedByNameFromArrayList() {
        return ResponseEntity.ok(arraylistService.getAllStudentSortedByName());
    }
}
