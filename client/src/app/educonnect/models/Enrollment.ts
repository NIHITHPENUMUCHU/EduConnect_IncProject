export class Enrollment {
  constructor(
    public enrollmentId?: number,
    public studentId?: number,
    public courseId?: number,
    public enrollmentDate?: Date 
  ) {}

  public logAttributes(): void {
    console.log('enrollmentId:', this.enrollmentId);
    console.log('studentId:', this.studentId);
    console.log('courseId:', this.courseId);
    console.log('enrollmentDate:', this.enrollmentDate);
  }
}