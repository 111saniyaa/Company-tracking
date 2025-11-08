/******************************************************************************

CSC 205:20248
Meeting days : Monday (8:00 - 9:40 );
Program 1
Author(s): Saniya Sharma  & <studentID> : 37163041 
Description: this represents Company Employee Tracking System

*******************************************************************************/



package package1;








public class AdministrativeAssistant extends Employee implements Hourly {
    private int hoursPerWeek;

    public AdministrativeAssistant(String name, double hourlyWage, int hoursPerWeek) {
        super(name, hourlyWage); 
        this.hoursPerWeek = hoursPerWeek;
    }


    public int getHours() {
        return hoursPerWeek;
    }

 
    public void setHours(int hours) {
        this.hoursPerWeek = hours;
    }

   
    public void giveRaise(double amount) {
        this.salary += amount; 
    }

    
    public void getPaid() {
       
        this.cash += this.salary * this.hoursPerWeek * 2;
    }


    public String toString() {
        return name + " Hourly Wage: " + salary + " Cash: " + cash + " Administrative Assistant";
    }
}
