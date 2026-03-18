export class Student {
  constructor(
    public studentId: number = 0,
    public fullName: string = '',
    public dateOfBirth: Date | null = null,
    public contactNumber: string = '',
    public email: string = '',
    public address: string = ''
  ) {}

  [key: string]: any;
}