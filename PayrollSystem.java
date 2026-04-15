import java.util.*;

abstract class Employee {
    int id;
    String name;
    double baseSalary;

    Employee(int id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    abstract double calculateAllowance();

    double calculateGrossSalary() {
        return baseSalary + calculateAllowance();
    }

    double calculateTax(double gross) {
        if (gross > 100000) return gross * 0.20;
        else if (gross > 50000) return gross * 0.10;
        else return gross * 0.05;
    }

    void generateSlip() {
        double allowance = calculateAllowance();
        double gross = calculateGrossSalary();
        double tax = calculateTax(gross);
        double net = gross - tax;

        System.out.println("\n===== SALARY SLIP =====");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Allowance: " + allowance);
        System.out.println("Gross Salary: " + gross);
        System.out.println("Tax Deduction: " + tax);
        System.out.println("Net Salary: " + net);
    }
}

// Manager Class
class Manager extends Employee {

    Manager(int id, String name, double salary) {
        super(id, name, salary);
    }

    double calculateAllowance() {
        return baseSalary * 0.20; // 20% allowance
    }
}

// Developer Class
class Developer extends Employee {

    Developer(int id, String name, double salary) {
        super(id, name, salary);
    }

    double calculateAllowance() {
        return baseSalary * 0.10; // 10% allowance
    }
}

public class PayrollSystem {

    static List<Employee> employees = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("\n--- Employee Payroll System ---");
                System.out.println("1. Add Employee");
                System.out.println("2. Generate Salary Slip");
                System.out.println("3. Display All Employees");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        generateSlip();
                        break;
                    case 3:
                        displayEmployees();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }

    static void addEmployee() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine());

            for (Employee e : employees) {
                if (e.id == id) {
                    System.out.println("ID already exists!");
                    return;
                }
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Base Salary: ");
            double salary = Double.parseDouble(sc.nextLine());

            System.out.println("Select Type: 1.Manager  2.Developer");
            int type = Integer.parseInt(sc.nextLine());

            Employee emp;

            if (type == 1) {
                emp = new Manager(id, name, salary);
            } else if (type == 2) {
                emp = new Developer(id, name, salary);
            } else {
                System.out.println("Invalid type!");
                return;
            }

            employees.add(emp);
            System.out.println("Employee added successfully!");

        } catch (Exception e) {
            System.out.println("Error in input!");
        }
    }

    static void generateSlip() {
        try {
            System.out.print("Enter Employee ID: ");
            int id = Integer.parseInt(sc.nextLine());

            for (Employee e : employees) {
                if (e.id == id) {
                    e.generateSlip();
                    return;
                }
            }

            System.out.println("Employee not found!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (Employee e : employees) {
            System.out.println("\nID: " + e.id + " | Name: " + e.name + " | Base Salary: " + e.baseSalary);
        }
    }
}
