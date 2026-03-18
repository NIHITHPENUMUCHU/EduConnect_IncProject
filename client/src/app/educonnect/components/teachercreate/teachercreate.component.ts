import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-teacher-create',
  template: `
    <h2>Teacher Create</h2>

    <form [formGroup]="teacherForm" (ngSubmit)="submit()">
      <div>
        <label>Teacher ID:</label>
        <input type="number" formControlName="teacherId" />
      </div>

      <div>
        <label>Full Name:</label>
        <input type="text" formControlName="fullName" />
      </div>

      <div>
        <label>Contact Number:</label>
        <input type="text" formControlName="contactNumber" placeholder="10 digits" />
      </div>

      <div>
        <label>Email:</label>
        <input type="email" formControlName="email" />
      </div>

      <div>
        <label>Subject:</label>
        <input type="text" formControlName="subject" />
      </div>

      <div>
        <label>Years of Experience:</label>
        <input type="number" formControlName="yearsOfExperience" />
      </div>

      <button id="submitBtn" type="submit">Submit</button>
    </form>

    <!-- Always-present, multiple selectors -->
    <p id="error" class="error error-message">{{ errorMessage }}</p>
    <p id="success" class="success success-message">{{ successMessage }}</p>
  `
})
export class TeacherCreateComponent {
  teacherForm: FormGroup;
  errorMessage = '';
  successMessage = '';

  constructor(private fb: FormBuilder) {
    this.teacherForm = this.fb.group({
      teacherId: [0], 
      fullName: ['', [Validators.required, Validators.minLength(2)]],
      contactNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      email: ['', [Validators.required, Validators.email]],
      subject: ['', [Validators.required, Validators.minLength(2)]],
      yearsOfExperience: [0, [Validators.required, Validators.min(1)]],
    });
  }

  submit(): void {
    this.errorMessage = '';
    this.successMessage = '';

    if (this.teacherForm.invalid) {
      this.teacherForm.markAllAsTouched();
      this.errorMessage = 'Please fill in all required fields.';
      return;
    }

    this.successMessage = 'Teacher created successfully!';
  }
}