import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';

import { Student } from '../models/Student';
import { Teacher } from '../models/Teacher';
import { Course } from '../models/Course';
import { Enrollment } from '../models/Enrollment';
import { User } from '../models/User';

@Injectable({ providedIn: 'root' })
export class EduConnectService {

  private base = environment?.apiUrl && environment.apiUrl.length > 0
    ? environment.apiUrl
    : '';

  private json = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {}

  // -------- STUDENTS --------
  addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(`${this.base}/students`, student, this.json);
  }

  updateStudent(student: any): Observable<Student> {
    return this.http.put<Student>(
      `${this.base}/students/${student.studentId ?? ''}`,
      student,
      this.json
    );
  }

  getStudentById(studentId: number): Observable<Student> {
    return this.http.get<Student>(`${this.base}/students/${studentId}`, this.json);
  }

  getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.base}/students`, this.json);
  }

  // -------- TEACHERS --------
  getTeacherById(teacherId: number): Observable<Teacher> {
    return this.http.get<Teacher>(`${this.base}/teacher/${teacherId}`, this.json);
  }

  // -------- COURSES --------
  addCourse(course: Course): Observable<Course> {
    return this.http.post<Course>(`${this.base}/courses`, course, this.json);
  }

  getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.base}/courses`, this.json);
  }

  getCoursesByTeacherId(teacherId: number): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.base}/courses/teacher/${teacherId}`, this.json);
  }

  // -------- ENROLLMENTS --------
  getEnrollmentsByStudent(studentId: number): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(
      `${this.base}/enrollments/student/${studentId}`,
      this.json
    );
  }

  // -------- USER --------
  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.base}/user/${userId}`, this.json);
  }
}