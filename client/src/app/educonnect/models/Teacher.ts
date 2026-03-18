export class Teacher {
  teacherId: number;
  fullName: string;
  contactNumber: string;
  email: string;
  subject: string;
  yearsOfExperience: number;
  [key: string]: any;

  constructor(
    teacherId: number,
    fullName: string,
    contactNumber: string,
    email: string,
    subject: string,
    yearsOfExperience: number
  ) {
    this.teacherId = teacherId;
    this.fullName = fullName;
    this.contactNumber = contactNumber;
    this.email = email;
    this.subject = subject;
    this.yearsOfExperience = yearsOfExperience;
  }
}