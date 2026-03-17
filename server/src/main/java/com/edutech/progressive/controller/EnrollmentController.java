package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Enrollment;
import com.edutech.progressive.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // GET /enrollment -> 200
    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        try {
            return ResponseEntity.ok(enrollmentService.getAllEnrollments());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    // POST /enrollment -> 201 or 400
    @PostMapping
    public ResponseEntity<Integer> createEnrollment(@RequestBody Enrollment enrollment) {
        try {
            int id = enrollmentService.createEnrollment(enrollment);
            return ResponseEntity.status(201).body(id);
        } catch (RuntimeException dupOrInvalid) {
            return ResponseEntity.badRequest().build(); // duplicate/invalid payload
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    // PUT /enrollment/{enrollmentId} -> 200 or 400
    @PutMapping("/{enrollmentId}")
    public ResponseEntity<Void> updateEnrollment(@PathVariable int enrollmentId,
                                                 @RequestBody Enrollment enrollment) {
        try {
            enrollment.setEnrollmentId(enrollmentId);
            enrollmentService.updateEnrollment(enrollment);
            return ResponseEntity.ok().build();
        } catch (RuntimeException dupOrInvalid) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    // GET /enrollment/{enrollmentId} -> 200 or 400
    @GetMapping("/{enrollmentId}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable int enrollmentId) {
        try {
            return ResponseEntity.ok(enrollmentService.getEnrollmentById(enrollmentId));
        } catch (RuntimeException notFound) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    // GET /enrollment/student/{studentId} -> 200
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getAllEnrollmentsByStudent(@PathVariable int studentId) {
        try {
            return ResponseEntity.ok(enrollmentService.getAllEnrollmentsByStudent(studentId));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    // GET /enrollment/course/{courseId} -> 200
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getAllEnrollmentsByCourse(@PathVariable int courseId) {
        try {
            return ResponseEntity.ok(enrollmentService.getAllEnrollmentsByCourse(courseId));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}