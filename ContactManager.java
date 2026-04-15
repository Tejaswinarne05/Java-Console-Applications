import java.io.*;
import java.util.*;

class Contact {
    String name;
    String phone;

    Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + "," + phone;
    }
}

public class ContactManager {

    static final String FILE_NAME = "contacts.txt";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("\n--- Contact Management System ---");
                System.out.println("1. Add Contact");
                System.out.println("2. Delete Contact");
                System.out.println("3. Search Contact");
                System.out.println("4. Display All Contacts");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addContact();
                        break;
                    case 2:
                        deleteContact();
                        break;
                    case 3:
                        searchContact();
                        break;
                    case 4:
                        displayContacts();
                        break;
                    case 5:
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

    // Add contact
    static void addContact() {
        try {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
            bw.write(name + "," + phone);
            bw.newLine();
            bw.close();

            System.out.println("Contact added successfully!");

        } catch (IOException e) {
            System.out.println("Error saving contact!");
        }
    }

    // Display all contacts
    static void displayContacts() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("No contacts found.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            System.out.println("\n--- Contacts ---");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println("Name: " + data[0] + " | Phone: " + data[1]);
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error reading file!");
        }
    }

    // Search contact
    static void searchContact() {
        try {
            System.out.print("Enter Name to search: ");
            String search = sc.nextLine().toLowerCase();

            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data[0].toLowerCase().contains(search)) {
                    System.out.println("Found: " + data[0] + " - " + data[1]);
                    found = true;
                }
            }

            br.close();

            if (!found) {
                System.out.println("Contact not found!");
            }

        } catch (IOException e) {
            System.out.println("Error searching file!");
        }
    }

    // Delete contact
    static void deleteContact() {
        try {
            System.out.print("Enter Name to delete: ");
            String deleteName = sc.nextLine().toLowerCase();

            File inputFile = new File(FILE_NAME);
            File tempFile = new File("temp.txt");

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean deleted = false;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data[0].toLowerCase().contains(deleteName)) {
                    deleted = true;
                    continue;
                }

                bw.write(line);
                bw.newLine();
            }

            br.close();
            bw.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (deleted) {
                System.out.println("Contact deleted!");
            } else {
                System.out.println("Contact not found!");
            }

        } catch (IOException e) {
            System.out.println("Error deleting contact!");
        }
    }
}
