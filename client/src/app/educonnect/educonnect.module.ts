import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
 
import { StudentCreateComponent } from './components/studentcreate/studentcreate.component';
import { TeacherArrayComponent } from './components/teacherarray/teacherarray.component';
import { TeacherCreateComponent } from './components/teachercreate/teachercreate.component';
import { CourseCreateComponent } from './components/coursecreate/coursecreate.component';
 
import { EnrollmentComponent } from './components/enrollment/enrollment.component';
 
@NgModule({
  declarations: [
    StudentCreateComponent,
    TeacherArrayComponent,
    TeacherCreateComponent,
    CourseCreateComponent,
    EnrollmentComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class EduconnectModule {}