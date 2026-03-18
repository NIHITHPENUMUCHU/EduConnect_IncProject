package com.edutech.progressive.controller;

import com.edutech.progressive.dto.TeacherDTO;
import com.edutech.progressive.entity.Teacher;
import com.edutech.progressive.exception.TeacherAlreadyExistsException;
import com.edutech.progressive.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        try {
            return ResponseEntity.ok(teacherService.getAllTeachers());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int teacherId) {
        try {
            return ResponseEntity.ok(teacherService.getTeacherById(teacherId));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addTeacher(@RequestBody Teacher teacher) {
        try {
            Integer id = teacherService.addTeacher(teacher);
            return ResponseEntity.status(201).body(id);
        } catch (TeacherAlreadyExistsException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<Void> updateTeacher(@PathVariable int teacherId,
                                              @RequestBody Teacher teacher) {
        try {
            teacher.setTeacherId(teacherId);
            teacherService.updateTeacher(teacher);
            return ResponseEntity.ok().build();
        } catch (TeacherAlreadyExistsException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable int teacherId) {
        try {
            teacherService.deleteTeacher(teacherId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    // Day 13: Modify via DTO
    @PutMapping("/modify")
    public ResponseEntity<Void> modifyTeacherDetails(@RequestBody TeacherDTO dto) {
        try {
            // Exposed only if TeacherService declares method; if default, cast may be needed.
            if (teacherService instanceof com.edutech.progressive.service.impl.TeacherServiceImplJpa) {
                ((com.edutech.progressive.service.impl.TeacherServiceImplJpa) teacherService).modifyTeacherDetails(dto);
            } else {
                // If interface has default method, call it (no-op). Provided for compatibility.
                // teacherService.modifyTeacherDetails(dto);
                throw new IllegalStateException("Modify operation not supported in current service binding.");
            }
            return ResponseEntity.ok().build();
        } catch (TeacherAlreadyExistsException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}