export class Teacher {
  constructor(
    public teacherId?: number,
    public fullName?: string,
    public contactNumber?: string,
    public email?: string,
    public subject?: string,
    public yearsOfExperience?: number
  ) {}

  public logAttributes(): void {
    console.log('teacherId:', this.teacherId);
    console.log('fullName:', this.fullName);
    console.log('contactNumber:', this.contactNumber);
    console.log('email:', this.email);
    console.log('subject:', this.subject);
    console.log('yearsOfExperience:', this.yearsOfExperience);
  }
}