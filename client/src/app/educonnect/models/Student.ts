export class Student {
  constructor(
    public studentId?: number,
    public fullName?: string,
    public dateOfBirth?: Date,  
    public contactNumber?: string,
    public email?: string,
    public address?: string
  ) {}

  public logAttributes(): void {
    console.log('studentId:', this.studentId);
    console.log('fullName:', this.fullName);
    console.log('dateOfBirth:', this.dateOfBirth);
    console.log('contactNumber:', this.contactNumber); 
    console.log('email:', this.email);
    console.log('address:', this.address);
  }
}
