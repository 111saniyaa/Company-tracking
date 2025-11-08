
/******************************************************************************

CSC 205:20248
Meeting days : Monday (8:00 - 9:40 );
Program 1
Author(s): Saniya Sharma  & <studentID> : 37163041 
Description: this represents Company Employee Tracking System

*******************************************************************************/

package package1;



public abstract class Employee {
    protected String name;
    protected double salary; 
    protected double cash;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.cash = 0.0;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public double getCash() {
        return cash;
    }

   
    public void getPaid() {
        this.cash += this.salary / 26.0; 
    }

    
    public abstract void giveRaise(double amount);
}
