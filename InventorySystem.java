import java.util.*;

class Product {
    int id;
    String name;
    int quantity;
    double price;

    Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    double getTotalValue() {
        return quantity * price;
    }

    void display() {
        System.out.println("\nID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: " + price);
        System.out.println("Value: " + getTotalValue());

        if (quantity < 5) {
            System.out.println("⚠ LOW STOCK ALERT!");
        }
    }
}

public class InventorySystem {

    static List<Product> products = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("\n--- Inventory Management System ---");
                System.out.println("1. Add Product");
                System.out.println("2. Update Stock");
                System.out.println("3. Remove Product");
                System.out.println("4. Display Inventory");
                System.out.println("5. Total Inventory Value");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        updateStock();
                        break;
                    case 3:
                        removeProduct();
                        break;
                    case 4:
                        displayInventory();
                        break;
                    case 5:
                        totalValue();
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

    static Product findProduct(int id) {
        for (Product p : products) {
            if (p.id == id) return p;
        }
        return null;
    }

    static void addProduct() {
        try {
            System.out.print("Enter Product ID: ");
            int id = Integer.parseInt(sc.nextLine());

            if (findProduct(id) != null) {
                System.out.println("Product already exists!");
                return;
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Quantity: ");
            int qty = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Price: ");
            double price = Double.parseDouble(sc.nextLine());

            products.add(new Product(id, name, qty, price));
            System.out.println("Product added successfully!");

        } catch (Exception e) {
            System.out.println("Error in input!");
        }
    }

    static void updateStock() {
        try {
            System.out.print("Enter Product ID: ");
            int id = Integer.parseInt(sc.nextLine());

            Product p = findProduct(id);
            if (p == null) {
                System.out.println("Product not found!");
                return;
            }

            System.out.print("Enter new quantity: ");
            int qty = Integer.parseInt(sc.nextLine());

            p.quantity = qty;
            System.out.println("Stock updated!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void removeProduct() {
        try {
            System.out.print("Enter Product ID: ");
            int id = Integer.parseInt(sc.nextLine());

            Iterator<Product> it = products.iterator();
            while (it.hasNext()) {
                if (it.next().id == id) {
                    it.remove();
                    System.out.println("Product removed!");
                    return;
                }
            }

            System.out.println("Product not found!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void displayInventory() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        for (Product p : products) {
            p.display();
        }
    }

    static void totalValue() {
        double total = 0;

        for (Product p : products) {
            total += p.getTotalValue();
        }

        System.out.println("\nTotal Inventory Value: " + total);
    }
}
