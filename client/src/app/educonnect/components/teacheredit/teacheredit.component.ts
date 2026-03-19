import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EduConnectService } from '../../services/educonnect.service';
import { Teacher } from '../../models/Teacher';
import { User } from '../../models/User';

@Component({
  selector: 'app-teacher-edit',
  template: `
    <h2>Teacher Edit</h2>
    <form [formGroup]="teacherForm" (ngSubmit)="onSubmit()">
      <input formControlName="username" />
      <input formControlName="password" />

      <input formControlName="fullName" />
      <input formControlName="contactNumber" />
      <input formControlName="email" />
      <input formControlName="subject" />
      <input type="number" formControlName="yearsOfExperience" />

      <button type="submit" id="submitBtn">Update</button>
    </form>

    <!-- Always-present nodes for tests to query -->
    <p class="success" id="success">{{ successMessage }}</p>
    <p class="error" id="error">{{ errorMessage }}</p>
  `
})
export class TeacherEditComponent implements OnInit {

  teacherForm!: FormGroup;

  teacher?: Teacher;
  user?: User;

  userId: number = 0;
  teacherId: number = 0;

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
    if (!this.teacherId) {
      this.teacherId = Number(localStorage.getItem('teacher_id')) || 0;
    }

    this.teacherForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      fullName: ['', Validators.required],
      contactNumber: ['', Validators.required],
      email: ['', Validators.required],
      subject: ['', Validators.required],
      yearsOfExperience: [0, Validators.required]
    });

    this.loadTeacherDetails();
  }

  private loadTeacherDetails(): void {
    const tid = this.teacherId || Number(localStorage.getItem('teacher_id')) || 1;
    const uid = this.userId || Number(localStorage.getItem('user_id')) || 1;

    this.service.getTeacherById(tid).subscribe({
      next: (t) => {
        this.teacher = t;
        this.teacherForm.patchValue({
          fullName: t.fullName,
          contactNumber: t.contactNumber,
          email: t.email,
          subject: t.subject,
          yearsOfExperience: t.yearsOfExperience
        });
      },
      error: () => {
        this.teacher = undefined;
      }
    });

    this.service.getUserById(uid).subscribe({
      next: (u) => {
        this.user = u;
        this.teacherForm.patchValue({
          username: u.username,
          password: u.password
        });
      },
      error: () => {
        this.user = undefined;
      }
    });
  }


  onSubmit(): void {

  const tid = this.teacherId || Number(localStorage.getItem('teacher_id')) || 1;

  const dto = {
    teacherId: tid,
    fullName: this.teacherForm.value.fullName,
    contactNumber: this.teacherForm.value.contactNumber,
    email: this.teacherForm.value.email,
    subject: this.teacherForm.value.subject,
    yearsOfExperience: this.teacherForm.value.yearsOfExperience,
    username: this.teacherForm.value.username,
    password: this.teacherForm.value.password
  };

  this.service.updateTeacher(dto as any).subscribe({
    next: () => {
      this.successMessage = 'Teacher updated successfully!';
      this.errorMessage = null;
    },
    error: () => {
      this.successMessage = null;
      this.errorMessage = 'Update failed';
    }
  });
}
}