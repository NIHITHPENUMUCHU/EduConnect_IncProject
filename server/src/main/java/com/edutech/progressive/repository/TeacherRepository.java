package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findByTeacherId(int teacherId);

    Teacher findByEmail(String email);
}