package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Teacher;
import com.edutech.progressive.service.TeacherService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(@Qualifier("teacherServiceImplJpa") TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // GET /teacher
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() throws Exception {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    // GET /teacher/{teacherId}
    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int teacherId) throws Exception {
        return ResponseEntity.ok(teacherService.getTeacherById(teacherId));
    }

    @PostMapping
    public ResponseEntity<Integer> addTeacher(@RequestBody Teacher teacher) throws Exception {
        Integer id = teacherService.addTeacher(teacher);
        return ResponseEntity.status(201).body(id); // ✅ 201 CREATED
    }

    // PUT /teacher/{teacherId}
    @PutMapping("/{teacherId}")
    public ResponseEntity<Void> updateTeacher(@PathVariable int teacherId,
            @RequestBody Teacher teacher) throws Exception {
        teacher.setTeacherId(teacherId);
        teacherService.updateTeacher(teacher);
        return ResponseEntity.ok().build();
    }

    // DELETE /teacher/{teacherId}
    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable int teacherId) throws Exception {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.noContent().build();
    }

    // GET /teacher/yearsofexperience
    @GetMapping("/yearsofexperience")
    public ResponseEntity<List<Teacher>> getTeacherSortedByYearsOfExperience() throws Exception {
        return ResponseEntity.ok(teacherService.getTeacherSortedByExperience());
    }
}