/******************************************************************************

CSC 205:20248
Meeting days : Monday (8:00 - 9:40 );
Program 1
Author(s): Saniya Sharma  & <studentID> : 37163041 
Description: this represents Company Employee Tracking System

*******************************************************************************/




package package1;





public class SoftwareEngineer extends Employee {

    public SoftwareEngineer(String name, double salary) {
        super(name, salary);
    }

    
    public void giveRaise(double amount) {
        
        this.salary += this.salary * (amount / 100.0);
    }

    
    public String toString() {
        return name + " Salary: " + salary + " Cash: " + cash + " Software Engineer";
    }
}
