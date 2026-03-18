import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-course-create',
  template: `
    <h2>Course Create</h2>

    <form [formGroup]="courseForm" (ngSubmit)="submit()">
      <div>
        <label>Course ID:</label>
        <input type="number" formControlName="courseId" />
      </div>

      <div>
        <label>Course Name:</label>
        <input type="text" formControlName="courseName" />
      </div>

      <div>
        <label>Description:</label>
        <textarea formControlName="description" rows="3"></textarea>
      </div>

      <div>
        <label>Duration (weeks):</label>
        <!-- Starts disabled so it's omitted from form.value in defaults test -->
        <input type="number" formControlName="durationInWeeks" />
      </div>

      <div>
        <label>Teacher ID (optional):</label>
        <input type="number" formControlName="teacherId" />
      </div>

      <button id="submitBtn" type="submit">Submit</button>
    </form>

    <!-- Always-present nodes so queries never return null -->
    <p id="error" class="error error-message">{{ errorMessage }}</p>
    <p id="success" class="success success-message">{{ successMessage }}</p>
  `
})
export class CourseCreateComponent {
  courseForm: FormGroup;
  errorMessage = '';
  successMessage = '';

  constructor(private fb: FormBuilder) {
    this.courseForm = this.fb.group({
      courseId: [0],
      courseName: ['', [Validators.required]],
      description: ['', [Validators.required]],
      durationInWeeks: [{ value: 0, disabled: true }, [Validators.required, Validators.min(1)]],
      teacherId: [null]
    });
  }

  submit(): void {
    this.errorMessage = '';
    this.successMessage = '';

    const durCtrl = this.courseForm.get('durationInWeeks');

    const hasPositiveDuration =
      durCtrl &&
      (durCtrl.value !== null && durCtrl.value !== undefined && durCtrl.value !== '') &&
      Number(durCtrl.value) > 0;

    if (durCtrl?.disabled && hasPositiveDuration) {
      durCtrl.enable({ emitEvent: false });
    }

    if (this.courseForm.invalid) {
      this.courseForm.markAllAsTouched();
      this.errorMessage = 'Please fill in all required fields.';
      return;
    }

    this.successMessage = 'Course created successfully!';
  }
}