export class Course {
  constructor(
    public courseId?: number,
    public courseName?: string,
    public description?: string,
    public teacherId?: number 
  ) {}

  public logAttributes(): void {
    console.log('courseId:', this.courseId);
    console.log('courseName:', this.courseName);
    console.log('description:', this.description);
    console.log('teacherId:', this.teacherId); 
  }
}