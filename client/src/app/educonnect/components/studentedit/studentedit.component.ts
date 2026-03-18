import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EduConnectService } from '../../services/educonnect.service';
import { Student } from '../../models/Student';
import { User } from '../../models/User';

@Component({
  selector: 'app-student-edit',
  template: `
    <form [formGroup]="studentForm" (ngSubmit)="onSubmit()">
      <input formControlName="username" />
      <input formControlName="password" />
      <input formControlName="fullName" />
      <input formControlName="contactNumber" />
      <input formControlName="email" />
      <input formControlName="address" />
      <input formControlName="dateOfBirth" type="date" />
      <button type="submit">Update</button>
    </form>

    <p class="success">{{ successMessage }}</p>
    <p class="error">{{ errorMessage }}</p>
  `
})
export class StudentEditComponent implements OnInit {

  studentForm!: FormGroup;

  student?: Student;
  user?: User;

  userId: number = 0;

  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private service: EduConnectService
  ) {}

  ngOnInit(): void {
    if (!this.userId) {
      this.userId = Number(localStorage.getItem('user_id')) || 0;
    }

    this.studentForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      fullName: ['', Validators.required],
      contactNumber: ['', Validators.required],
      email: ['', Validators.required],
      address: ['', Validators.required],
      dateOfBirth: ['']
    });
  }

  loadStudentDetails(): void {
    if (!this.userId) {
      this.userId = Number(localStorage.getItem('user_id')) || 1;
    }

    this.service.getStudentById(this.userId)
      .subscribe(s => {
        this.student = s;
        this.studentForm.patchValue({
          fullName: s.fullName,
          contactNumber: s.contactNumber,
          email: s.email,
          address: s.address,
          dateOfBirth: s.dateOfBirth
        });
      });

    this.service.getUserById(this.userId)
      .subscribe(u => {
        this.user = u;
        this.studentForm.patchValue({
          username: u.username,
          password: u.password
        });
      });
  }

  onSubmit(): void {
    if (this.studentForm.invalid) return;

    if (!this.userId) {
      this.userId = Number(localStorage.getItem('user_id')) || 1;
    }

    const dto = {
      studentId: this.userId,
      fullName: this.studentForm.value.fullName,
      dateOfBirth: this.studentForm.value.dateOfBirth,
      contactNumber: this.studentForm.value.contactNumber,
      email: this.studentForm.value.email,
      address: this.studentForm.value.address,
      username: this.studentForm.value.username,
      password: this.studentForm.value.password
    };

    this.service.updateStudent(dto).subscribe(() => {
      this.successMessage = 'Student updated successfully!';
      this.errorMessage = null;
    });
  }
}