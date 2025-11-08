/******************************************************************************

CSC 205:20248
Meeting days : Monday (8:00 - 9:40 );
Program 1
Author(s): Saniya Sharma  & <studentID> : 37163041 
Description: this represents Company Employee Tracking System

*******************************************************************************/




package package1;



import java.util.Scanner;

public class Company {
    private static final int MAX_EMPLOYEES = 5;
    private Employee[] employees = new Employee[MAX_EMPLOYEES];
    private Hourly[] hourlyEmployees = new Hourly[MAX_EMPLOYEES];
    private int employeeCount = 0;
    private int hourlyCount = 0;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Company company = new Company();
        company.run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            char choice = mainMenu();
            switch (choice) {
                case 'A':
                    addEmployee();
                    break;
                case 'B':
                    listEmployees();
                    break;
                case 'C':
                    giveEmployeeRaise();
                    break;
                case 'D':
                    givePaychecks();
                    break;
                case 'E':
                    changeHours();
                    break;
                case 'F':
                    System.out.println("Bye!");
                    running = false;
                    break;
                default:
                    
                    System.out.println("Invalid option");
            }
        }
    }

    private char mainMenu() {
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("A. Add an Employee");
            System.out.println("B. List all Employees");
            System.out.println("C. Give an Employee a Raise");
            System.out.println("D. Give Paychecks");
            System.out.println("E. Change someones hours");
            System.out.println("F. Quit");
            String input = scanner.nextLine().trim();
            if (input.length() == 0) {
                System.out.println("Invalid option");
                continue;
            }
            char c = Character.toUpperCase(input.charAt(0));
            if (c >= 'A' && c <= 'F') {
                return c;
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    private void addEmployee() {
        if (employeeCount >= MAX_EMPLOYEES) {
            System.out.println("Cannot hire: company is full!");
            return;
        }

        System.out.println("What is their name?");
        String name = scanner.nextLine().trim();

        System.out.println("What is their salary (yearly or hourly)?");
        double salary;
        try {
            String s = scanner.nextLine().trim();
            salary = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary input. Hiring aborted.");
            return;
        }

        System.out.println("Are they an hourly worker? (Y/N)");
        String hourlyResp = scanner.nextLine().trim();
        boolean isHourly = hourlyResp.length() > 0 && Character.toUpperCase(hourlyResp.charAt(0)) == 'Y';

        if (isHourly) {
            System.out.println("How many hours per week do they work?");
            int hours;
            try {
                String hs = scanner.nextLine().trim();
                hours = Integer.parseInt(hs);
            } catch (NumberFormatException e) {
                System.out.println("Invalid hours input. Hiring aborted.");
                return;
            }
            AdministrativeAssistant aa = new AdministrativeAssistant(name, salary, hours);
            employees[employeeCount++] = aa;
            hourlyEmployees[hourlyCount++] = aa;
            System.out.println(name + " was hired!");
        } else {
            SoftwareEngineer se = new SoftwareEngineer(name, salary);
            employees[employeeCount++] = se;
            System.out.println(name + " was hired!");
        }
    }

    private void listEmployees() {
        if (employeeCount == 0) {
            System.out.println("Nobody works here!");
            return;
        }
        for (int i = 0; i < employeeCount; i++) {
            Employee e = employees[i];
            
            if (e instanceof AdministrativeAssistant) {
                
                AdministrativeAssistant aa = (AdministrativeAssistant) e;
                System.out.println(aa.getName() + " Hourly Wage: " + aa.getSalary() + " Cash: " + aa.getCash() + " Administrative Assistant");
            } else if (e instanceof SoftwareEngineer) {
                SoftwareEngineer se = (SoftwareEngineer) e;
                System.out.println(se.getName() + " Salary: " + se.getSalary() + " Cash: " + se.getCash() + " Software Engineer");
            } else {
               
                System.out.println(e.getName() + " Salary: " + e.getSalary() + " Cash: " + e.getCash());
            }
        }
    }

    private void giveEmployeeRaise() {
        if (employeeCount == 0) {
            System.out.println("Nobody works here!");
            return;
        }
        System.out.println("Who do you want to give a raise to?");
        String name = scanner.nextLine().trim();

        Employee target = findEmployeeByName(name);
        if (target == null) {
            System.out.println("That employee does not exist.");
            return;
        }

        System.out.println("What raise do you want to give them?");
        String s = scanner.nextLine().trim();
        double amount;
        try {
            amount = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            System.out.println("Invalid raise amount.");
            return;
        }

        
        target.giveRaise(amount);
        System.out.println(target.getName() + " is happy!");
    }

    private void givePaychecks() {
        if (employeeCount == 0) {
            System.out.println("Nobody works here!");
            return;
        }
        for (int i = 0; i < employeeCount; i++) {
            employees[i].getPaid();
        }
        System.out.println("Hooray for money!");
    }

    private void changeHours() {
        if (employeeCount == 0) {
            System.out.println("Nobody works here!");
            return;
        }
        System.out.println("Change hours for who?");
        String name = scanner.nextLine().trim();
        Employee target = findEmployeeByName(name);
        if (target == null) {
            System.out.println("That employee does not exist.");
            return;
        }
        if (!(target instanceof Hourly)) {
            System.out.println(target.getName() + " is not an hourly worker.");
            return;
        }
        Hourly h = (Hourly) target;
        System.out.println(target.getName() + " currently works " + h.getHours() + " hours per week. What would you like to change it to?");
        String s = scanner.nextLine().trim();
        int newHours;
        try {
            newHours = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Invalid hours input.");
            return;
        }
        h.setHours(newHours);
        System.out.println(target.getName() + " will now work " + newHours + " hours per week");
    }

    private Employee findEmployeeByName(String name) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getName().equalsIgnoreCase(name)) {
                return employees[i];
            }
        }
        return null;
    }
}
