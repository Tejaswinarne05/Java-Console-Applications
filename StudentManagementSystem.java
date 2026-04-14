import java.util.*;

class Student {
    int id;
    String name;
    List<Integer> marks;

    Student(int id, String name, List<Integer> marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    double getAverage() {
        int sum = 0;
        for (int m : marks)
            sum += m;
        return (double) sum / marks.size();
    }

    String getGrade() {
        double avg = getAverage();
        if (avg >= 90)
            return "A";
        else if (avg >= 75)
            return "B";
        else if (avg >= 60)
            return "C";
        else if (avg >= 50)
            return "D";
        else
            return "F";
    }

    void display() {
        System.out.println("\nID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
        System.out.println("Average: " + getAverage());
        System.out.println("Grade: " + getGrade());
    }
}

public class StudentManagementSystem {

    static List<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("\n--- Student Management System ---");
                System.out.println("1. Add Student");
                System.out.println("2. Display All Students");
                System.out.println("3. Search Student");
                System.out.println("4. Update Student");
                System.out.println("5. Delete Student");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        displayAll();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deleteStudent();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
            }
        }
    }

    static void addStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine());

            for (Student s : students) {
                if (s.id == id) {
                    System.out.println("ID already exists!");
                    return;
                }
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter number of subjects: ");
            int n = Integer.parseInt(sc.nextLine());

            List<Integer> marks = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                System.out.print("Enter mark " + (i + 1) + ": ");
                marks.add(Integer.parseInt(sc.nextLine()));
            }

            students.add(new Student(id, name, marks));
            System.out.println("Student added successfully!");

        } catch (Exception e) {
            System.out.println("Error in input!");
        }
    }

    static void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }

    static void searchStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine());

            for (Student s : students) {
                if (s.id == id) {
                    s.display();
                    return;
                }
            }

            System.out.println("Student not found!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void updateStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine());

            for (Student s : students) {
                if (s.id == id) {

                    System.out.print("Enter new name: ");
                    s.name = sc.nextLine();

                    System.out.print("Enter number of subjects: ");
                    int n = Integer.parseInt(sc.nextLine());

                    s.marks.clear();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Enter new mark " + (i + 1) + ": ");
                        s.marks.add(Integer.parseInt(sc.nextLine()));
                    }

                    System.out.println("Updated successfully!");
                    return;
                }
            }

            System.out.println("Student not found!");

        } catch (Exception e) {
            System.out.println("Error in input!");
        }
    }

    static void deleteStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine());

            Iterator<Student> it = students.iterator();
            while (it.hasNext()) {
                if (it.next().id == id) {
                    it.remove();
                    System.out.println("Deleted successfully!");
                    return;
                }
            }

            System.out.println("Student not found!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }
}