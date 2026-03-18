import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

interface StudentModel {
  studentId: number;
  fullName: string;
  dateOfBirth: Date;
  contactNumber: string;
  email: string;
  address: string;
}

@Component({
  selector: 'app-student-sample',
  standalone: true,
  imports: [CommonModule],
  template: `
    <h2>Student Details</h2>
    <div class="student-details">
      <p>Student ID: {{ student.studentId }}</p>
      <p>Full Name: {{ student.fullName }}</p>
      <p>Date of Birth: {{ student.dateOfBirth }}</p>
      <p>Contact Number: {{ student.contactNumber }}</p>
      <p>Email: {{ student.email }}</p>
      <p>Address: {{ student.address }}</p>
    </div>
  `
})
export class StudentSampleComponent {
  student: StudentModel = {
    studentId: 1,
    fullName: 'John Doe',
    dateOfBirth: new Date('1990-01-01'),
    contactNumber: '1234567890',
    email: 'john@example.com',
    address: '123 Main Street'
  };
}