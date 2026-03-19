import { Component, OnInit } from '@angular/core';
import { EduConnectService } from '../../services/educonnect.service';
import { Teacher } from '../../models/Teacher';
import { Course } from '../../models/Course';
import { Student } from '../../models/Student';
import { Enrollment } from '../../models/Enrollment';

@Component({
  selector: 'app-dashboard',
  template: `<h2>Dashboard</h2>`
})
export class DashboardComponent implements OnInit {

  role: string | null = null;
  userId: number = 0;

  teacherId: number = 0;
  studentId: number = 0;

  teacherDetails?: Teacher;
  studentDetails?: Student;

  courses: Course[] = [];
  students: Student[] = [];
  enrollments: Enrollment[] = [];

  constructor(private service: EduConnectService) {}

  ngOnInit(): void {
    this.role = localStorage.getItem('role');

    this.userId = Number(localStorage.getItem('user_id')) || 0;
    this.teacherId = Number(localStorage.getItem('teacher_id')) || 0;
    this.studentId = Number(localStorage.getItem('student_id')) || 0;

    if (this.role === 'TEACHER') {
      this.loadTeacherData();
    }
    if (this.role === 'STUDENT') {
      this.loadStudentData();
    }
  }

  loadTeacherData(): void {
    this.service.getTeacherById(this.teacherId)
      .subscribe(t => this.teacherDetails = t);

    this.service.getCoursesByTeacherId(this.teacherId)
      .subscribe(c => this.courses = c);

    this.service.getAllStudents()
      .subscribe(s => this.students = s);
  }

  loadStudentData(): void {
    this.service.getStudentById(this.studentId).subscribe({
      next: s => this.studentDetails = s,
      error: () => this.studentDetails = undefined
    });

    this.service.getEnrollmentsByStudent(this.studentId)
      .subscribe(e => this.enrollments = e);

    this.service.getAllCourses()
      .subscribe(c => this.courses = c);
  }

  deleteTeacher(): void {
    const tid = this.teacherId || Number(localStorage.getItem('teacher_id')) || 1;
    this.service.deleteTeacher(tid).subscribe(() => {
      this.teacherDetails = undefined;
    });
  }

  deleteCourse(courseId: number): void {
    this.service.deleteCourse(courseId).subscribe(() => {
      this.courses = (this.courses || []).filter(c => c.courseId !== courseId);
    });
  }
}