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
  private base = environment?.apiUrl && environment.apiUrl.length > 0 ? environment.apiUrl : '';
  private json = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private http: HttpClient) {}

  // -------------------- STUDENTS --------------------
  addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(`${this.base}/students`, student, this.json);
  }

  updateStudent(student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.base}/students/${student.studentId}`, student, this.json);
  }

  deleteStudent(studentId: number): Observable<any> {
    return this.http.delete<any>(`${this.base}/students/${studentId}`, this.json);
  }

  getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.base}/students`, this.json);
  }

  getStudentById(studentId: number): Observable<Student> {
    return this.http.get<Student>(`${this.base}/students/${studentId}`, this.json);
  }

  // -------------------- TEACHERS --------------------
  addTeacher(teacher: Teacher): Observable<Teacher> {
    return this.http.post<Teacher>(`${this.base}/teacher`, teacher, this.json);
  }

  updateTeacher(teacher: Teacher): Observable<Teacher> {
    return this.http.put<Teacher>(`${this.base}/teacher/${teacher.teacherId}`, teacher, this.json);
  }

  deleteTeacher(teacherId: number): Observable<any> {
    return this.http.delete<any>(`${this.base}/teacher/${teacherId}`, this.json);
  }

  getAllTeachers(): Observable<Teacher[]> {
    return this.http.get<Teacher[]>(`${this.base}/teacher`, this.json);
  }

  getTeacherById(teacherId: number): Observable<Teacher> {
    return this.http.get<Teacher>(`${this.base}/teacher/${teacherId}`, this.json);
  }

  // -------------------- COURSES --------------------
  addCourse(course: Course): Observable<Course> {
    return this.http.post<Course>(`${this.base}/courses`, course, this.json);
  }

  updateCourse(course: Course): Observable<Course> {
    return this.http.put<Course>(`${this.base}/courses/${course.courseId}`, course, this.json);
  }

  deleteCourse(courseId: number): Observable<any> {
    return this.http.delete<any>(`${this.base}/courses/${courseId}`, this.json);
  }

  getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.base}/courses`, this.json);
  }

  getCourseById(courseId: number): Observable<Course> {
    return this.http.get<Course>(`${this.base}/courses/${courseId}`, this.json);
  }

  getCoursesByTeacherId(teacherId: number): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.base}/courses/teacher/${teacherId}`, this.json);
  }

  // -------------------- ENROLLMENTS --------------------
  addEnrollment(payload: Enrollment): Observable<Enrollment> {
    return this.http.post<Enrollment>(`${this.base}/enrollments`, payload, this.json);
  }

  updateEnrollment(payload: Enrollment): Observable<Enrollment> {
    return this.http.put<Enrollment>(`${this.base}/enrollments/${payload.enrollmentId}`, payload, this.json);
  }

  getAllEnrollments(): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.base}/enrollments`, this.json);
  }

  getEnrollmentById(id: number): Observable<Enrollment> {
    return this.http.get<Enrollment>(`${this.base}/enrollments/${id}`, this.json);
  }

  getEnrollmentsByStudentId(studentId: number): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.base}/enrollments/student/${studentId}`, this.json);
  }

  // -------------------- USER --------------------
  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.base}/user/${userId}`, this.json);
  }
}