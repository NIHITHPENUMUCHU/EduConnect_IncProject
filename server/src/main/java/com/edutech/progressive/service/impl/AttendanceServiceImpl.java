package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Attendance;
import com.edutech.progressive.entity.Course;
import com.edutech.progressive.entity.Student;
import com.edutech.progressive.repository.AttendanceRepository;
import com.edutech.progressive.service.AttendanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance createAttendance(Attendance attendance) {
        if (attendance == null || attendance.getCourse() == null || attendance.getStudent() == null
                || attendance.getAttendanceDate() == null || attendance.getStatus() == null
                || attendance.getStatus().trim().isEmpty()) {
            throw new RuntimeException("Invalid attendance payload.");
        }

        int courseId = attendance.getCourse().getCourseId();
        int studentId = attendance.getStudent().getStudentId();
        Date date = attendance.getAttendanceDate();

        Optional<Attendance> exists = attendanceRepository
                .findByCourse_CourseIdAndStudent_StudentIdAndAttendanceDate(courseId, studentId, date);
        if (exists.isPresent()) {
            throw new RuntimeException("Attendance already recorded for this student, course and date.");
        }

        // Normalize associations to avoid detached/full entity persistence issues
        Course c = new Course();
        c.setCourseId(courseId);
        Student s = new Student();
        s.setStudentId(studentId);

        attendance.setCourse(c);
        attendance.setStudent(s);

        return attendanceRepository.save(attendance);
    }

    @Override
    public void deleteAttendance(int attendanceId) {
        if (!attendanceRepository.existsById(attendanceId)) {
            throw new RuntimeException("Attendance not found.");
        }
        attendanceRepository.deleteById(attendanceId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Attendance> getAttendanceByStudent(int studentId) {
        return attendanceRepository.findByStudent_StudentId(studentId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Attendance> getAttendanceByCourse(int courseId) {
        return attendanceRepository.findByCourse_CourseId(courseId);
    }
}