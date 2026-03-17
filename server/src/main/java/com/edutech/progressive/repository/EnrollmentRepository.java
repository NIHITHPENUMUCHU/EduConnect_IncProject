package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Enrollment;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    Optional<Enrollment> findByStudent_StudentIdAndCourse_CourseId(int studentId, int courseId);

    List<Enrollment> findAllByStudent_StudentId(int studentId);

    List<Enrollment> findAllByCourse_CourseId(int courseId);

    @Transactional
    @Modifying
    @Query("delete from Enrollment e where e.course.courseId = :courseId")
    void deleteByCourseId(@Param("courseId") int courseId);

    @Transactional
    @Modifying
    @Query("delete from Enrollment e where e.student.studentId = :studentId")
    void deleteByStudentId(@Param("studentId") int studentId);

    @Transactional
    @Modifying
    @Query("delete from Enrollment e where e.course.teacher.teacherId = :teacherId")
    void deleteByTeacherId(@Param("teacherId") int teacherId);
}