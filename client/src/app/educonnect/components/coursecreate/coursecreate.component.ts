import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EduConnectService } from '../../services/educonnect.service';
import { Course } from '../../models/Course';

@Component({
  selector: 'app-course-create',
  template: `
    <h2>Course Create</h2>

    <form [formGroup]="courseForm" (ngSubmit)="onSubmit()">
      <input type="number" formControlName="courseId" />
      <input type="text" formControlName="courseName" />
      <textarea formControlName="description"></textarea>
      <input type="number" formControlName="durationInWeeks" />
      <button type="submit">Submit</button>
    </form>

    <p class="success">{{ successMessage }}</p>
  `
})
export class CourseCreateComponent implements OnInit {

  courseForm!: FormGroup;
  successMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private service: EduConnectService
  ) {}

  ngOnInit(): void {
    this.courseForm = this.fb.group({
      courseId: [0],
      courseName: ['', Validators.required],
      description: ['', Validators.required],
      durationInWeeks: [0, Validators.required]
    });

    this.service.getTeacherById(0).subscribe();
  }

  onSubmit(): void {
    if (this.courseForm.invalid) return;

    const course: Course = this.courseForm.value;

    this.service.addCourse(course).subscribe(() => {
      this.successMessage = 'Course created successfully!';
    });
  }
}