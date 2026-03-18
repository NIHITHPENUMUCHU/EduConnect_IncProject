import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

interface TeacherModel {
  teacherId: number;
  fullName: string;
  contactNumber: string;
  email: string;
  subject: string;
  yearsOfExperience: number;
}

@Component({
  selector: 'app-teacher-sample',
  standalone: true,
  imports: [CommonModule],
  template: `
    <h2>Teacher Details</h2>
    <div class="teacher-details">
      <p>Teacher ID: {{ teacher.teacherId }}</p>
      <p>Full Name: {{ teacher.fullName }}</p>
      <p>Subject: {{ teacher.subject }}</p>
      <p>Contact Number: {{ teacher.contactNumber }}</p>
      <p>Email: {{ teacher.email }}</p>
      <p>Years of Experience: {{ teacher.yearsOfExperience }}</p>
    </div>
  `
})
export class TeacherSampleComponent {
  teacher: TeacherModel = {
    teacherId: 1,
    fullName: 'Dr. Jane Smith',
    contactNumber: '9876543210',
    email: 'jane@school.com',
    subject: 'English',
    yearsOfExperience: 15
  };
}