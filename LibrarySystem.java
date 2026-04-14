import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    void display() {
        System.out.println("\nBook ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Status: " + (isIssued ? "Issued" : "Available"));
    }
}

public class LibrarySystem {

    static List<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("\n--- Library Management System ---");
                System.out.println("1. Add Book");
                System.out.println("2. Issue Book");
                System.out.println("3. Return Book");
                System.out.println("4. Display Available Books");
                System.out.println("5. Search Book");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        issueBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        displayAvailable();
                        break;
                    case 5:
                        searchBook();
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

    static Book findBook(int id) {
        for (Book b : books) {
            if (b.id == id)
                return b;
        }
        return null;
    }

    static void addBook() {
        try {
            System.out.print("Enter Book ID: ");
            int id = Integer.parseInt(sc.nextLine());

            if (findBook(id) != null) {
                System.out.println("Book ID already exists!");
                return;
            }

            System.out.print("Enter Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author: ");
            String author = sc.nextLine();

            books.add(new Book(id, title, author));
            System.out.println("Book added successfully!");

        } catch (Exception e) {
            System.out.println("Error in input!");
        }
    }

    static void issueBook() {
        try {
            System.out.print("Enter Book ID: ");
            int id = Integer.parseInt(sc.nextLine());

            Book b = findBook(id);
            if (b == null) {
                System.out.println("Book not found!");
                return;
            }

            if (b.isIssued) {
                System.out.println("Book already issued!");
                return;
            }

            b.isIssued = true;
            System.out.println("Book issued successfully!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void returnBook() {
        try {
            System.out.print("Enter Book ID: ");
            int id = Integer.parseInt(sc.nextLine());

            Book b = findBook(id);
            if (b == null) {
                System.out.println("Book not found!");
                return;
            }

            if (!b.isIssued) {
                System.out.println("Book is already available!");
                return;
            }

            b.isIssued = false;
            System.out.println("Book returned successfully!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void displayAvailable() {
        boolean found = false;

        for (Book b : books) {
            if (!b.isIssued) {
                b.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available books.");
        }
    }

    static void searchBook() {
        System.out.print("Enter Title or Author: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;

        for (Book b : books) {
            if (b.title.toLowerCase().contains(keyword) ||
                    b.author.toLowerCase().contains(keyword)) {
                b.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }
}