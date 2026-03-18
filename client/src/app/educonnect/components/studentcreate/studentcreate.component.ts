import { Component } from '@angular/core';
import { Student } from '../../models/Student';
 
@Component({
  selector: 'app-student-create',
  templateUrl: './studentcreate.component.html',
  styleUrls: ['./studentcreate.component.scss']
})
export class StudentCreateComponent {
  student: Student = new Student();
 
  dob: string = '';
 
  errorMessage: string | null = null;
  successMessage: string | null = null;
 
  constructor() {}
 
  onSubmit(): void {
    this.errorMessage = null;
    this.successMessage = null;
 
    this.student.dateOfBirth = this.dob ? new Date(this.dob) : null;
 
    const nameOk = !!this.student.fullName && this.student.fullName.trim().length >= 2;
    const dobOk = this.student.dateOfBirth === null || this.student.dateOfBirth instanceof Date;
    const contactOk = !!this.student.contactNumber && /^\d{10}$/.test(this.student.contactNumber);
    const emailOk = !!this.student.email && /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.student.email);
    const addrOk = !!this.student.address && this.student.address.trim().length >= 5;
 
    if (!nameOk || !dobOk || !contactOk || !emailOk || !addrOk) {
      this.errorMessage = 'Please fill in all required fields.';
      return;
    }
 
    this.successMessage = 'Student created successfully!';
  }
 
  resetForm(): void {
    this.student = new Student();
    this.dob = '';
    this.errorMessage = null;
    this.successMessage = null;
  }
}
 
