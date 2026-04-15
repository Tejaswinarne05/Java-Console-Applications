import java.util.*;

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    void display() {
        System.out.println(id + ". " + name + " - ₹" + price);
    }
}

class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getTotal() {
        return product.price * quantity;
    }
}

public class ECommerceCart {

    static List<Product> catalog = new ArrayList<>();
    static List<CartItem> cart = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadProducts();

        while (true) {
            try {
                System.out.println("\n--- E-Commerce Cart System ---");
                System.out.println("1. View Products");
                System.out.println("2. Add to Cart");
                System.out.println("3. Remove from Cart");
                System.out.println("4. View Cart");
                System.out.println("5. Checkout");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        showProducts();
                        break;
                    case 2:
                        addToCart();
                        break;
                    case 3:
                        removeFromCart();
                        break;
                    case 4:
                        viewCart();
                        break;
                    case 5:
                        checkout();
                        break;
                    case 6:
                        System.out.println("Thank you for shopping!");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }

    static void loadProducts() {
        catalog.add(new Product(1, "Laptop", 50000));
        catalog.add(new Product(2, "Phone", 20000));
        catalog.add(new Product(3, "Headphones", 2000));
        catalog.add(new Product(4, "Keyboard", 1500));
    }

    static Product findProduct(int id) {
        for (Product p : catalog) {
            if (p.id == id) return p;
        }
        return null;
    }

    static void showProducts() {
        System.out.println("\n--- Product Catalog ---");
        for (Product p : catalog) {
            p.display();
        }
    }

    static void addToCart() {
        try {
            System.out.print("Enter Product ID: ");
            int id = Integer.parseInt(sc.nextLine());

            Product p = findProduct(id);
            if (p == null) {
                System.out.println("Product not found!");
                return;
            }

            System.out.print("Enter Quantity: ");
            int qty = Integer.parseInt(sc.nextLine());

            for (CartItem item : cart) {
                if (item.product.id == id) {
                    item.quantity += qty;
                    System.out.println("Quantity updated in cart!");
                    return;
                }
            }

            cart.add(new CartItem(p, qty));
            System.out.println("Added to cart!");

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    static void removeFromCart() {
        try {
            System.out.print("Enter Product ID: ");
            int id = Integer.parseInt(sc.nextLine());

            Iterator<CartItem> it = cart.iterator();
            while (it.hasNext()) {
                if (it.next().product.id == id) {
                    it.remove();
                    System.out.println("Item removed!");
                    return;
                }
            }

            System.out.println("Item not found in cart!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        double total = 0;

        System.out.println("\n--- Your Cart ---");
        for (CartItem item : cart) {
            System.out.println(item.product.name +
                    " x " + item.quantity +
                    " = ₹" + item.getTotal());
            total += item.getTotal();
        }

        System.out.println("Total: ₹" + total);
    }

    static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        double total = 0;

        for (CartItem item : cart) {
            total += item.getTotal();
        }

        double discount = 0;

        if (total > 50000) discount = total * 0.15;
        else if (total > 20000) discount = total * 0.10;
        else if (total > 10000) discount = total * 0.05;

        double finalAmount = total - discount;

        System.out.println("\n--- Checkout ---");
        System.out.println("Total Amount: ₹" + total);
        System.out.println("Discount: ₹" + discount);
        System.out.println("Final Amount: ₹" + finalAmount);

        System.out.println("Order placed successfully!");

        cart.clear(); // empty cart after checkout
    }
}
