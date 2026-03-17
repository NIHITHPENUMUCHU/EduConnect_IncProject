package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Attendance;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByStudent_StudentId(int studentId);

    List<Attendance> findByCourse_CourseId(int courseId);

    Optional<Attendance> findByCourse_CourseIdAndStudent_StudentIdAndAttendanceDate(
            int courseId, int studentId, Date attendanceDate
    );

    @Transactional
    @Modifying
    @Query("delete from Attendance a where a.course.courseId = :courseId")
    void deleteByCourseId(@Param("courseId") int courseId);

    @Transactional
    @Modifying
    @Query("delete from Attendance a where a.student.studentId = :studentId")
    void deleteByStudentId(@Param("studentId") int studentId);
}