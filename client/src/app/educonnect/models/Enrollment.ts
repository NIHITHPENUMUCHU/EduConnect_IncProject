import { Student } from "./Student";
import { Course } from "./Course";

export class Enrollment {
  enrollmentId: number;
  student: Student;
  course: Course;
  enrollmentDate: Date;
  [key: string]: any;
  constructor(enrollmentId: number, student: Student, course: Course, enrollmentDate: Date) {
    this.enrollmentId = enrollmentId;
    this.student = student;
    this.course = course;
    this.enrollmentDate = enrollmentDate;
  }
}