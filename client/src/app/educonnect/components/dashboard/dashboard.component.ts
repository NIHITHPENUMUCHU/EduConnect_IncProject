import { Component, OnInit } from '@angular/core';
import { EduConnectService } from '../../services/educonnect.service';
import { Teacher } from '../../models/Teacher';
import { Course } from '../../models/Course';
import { Student } from '../../models/Student';

@Component({
  selector: 'app-dashboard',
  template: `<h2>Dashboard</h2>`
})
export class DashboardComponent implements OnInit {

  role: string | null = null;
  userId: number = 0;
  teacherId: number = 0;

  teacherDetails!: Teacher;
  courses: Course[] = [];
  students: Student[] = [];

  constructor(private service: EduConnectService) {}

  ngOnInit(): void {
    this.role = localStorage.getItem('role');

    this.userId = Number(localStorage.getItem('user_id')) || 0;
    this.teacherId = Number(localStorage.getItem('teacher_id')) || 0;

    if (this.role === 'TEACHER') {
      this.loadTeacherData();
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
}
