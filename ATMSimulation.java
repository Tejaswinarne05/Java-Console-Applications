import java.util.*;

class Account {
    int accNo;
    int pin;
    double balance;
    double dailyWithdrawn;
    List<String> history;

    Account(int accNo, int pin, double balance) {
        this.accNo = accNo;
        this.pin = pin;
        this.balance = balance;
        this.dailyWithdrawn = 0;
        this.history = new ArrayList<>();
        history.add("Account created with balance: " + balance);
    }

    void deposit(double amount) {
        balance += amount;
        history.add("Deposited: " + amount + " | Balance: " + balance);
    }

    boolean withdraw(double amount) {
        if (amount > balance) return false;
        if (dailyWithdrawn + amount > 10000) return false; // daily limit

        balance -= amount;
        dailyWithdrawn += amount;
        history.add("Withdrawn: " + amount + " | Balance: " + balance);
        return true;
    }

    void showBalance() {
        System.out.println("Current Balance: " + balance);
    }

    void miniStatement() {
        System.out.println("\n--- Mini Statement ---");
        for (String h : history) {
            System.out.println(h);
        }
    }
}

public class ATMSimulation {

    static Scanner sc = new Scanner(System.in);
    static Account account = new Account(12345, 1234, 5000); // demo account

    public static void main(String[] args) {

        System.out.println("===== ATM SYSTEM =====");

        // PIN Authentication
        int attempts = 0;
        boolean authenticated = false;

        while (attempts < 3) {
            System.out.print("Enter PIN: ");
            int enteredPin = Integer.parseInt(sc.nextLine());

            if (enteredPin == account.pin) {
                authenticated = true;
                break;
            } else {
                attempts++;
                System.out.println("Incorrect PIN! Attempts left: " + (3 - attempts));
            }
        }

        if (!authenticated) {
            System.out.println("Card blocked! Too many attempts.");
            return;
        }

        // ATM Menu
        while (true) {
            try {
                System.out.println("\n--- ATM Menu ---");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Mini Statement");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        account.showBalance();
                        break;

                    case 2:
                        System.out.print("Enter amount: ");
                        double dep = Double.parseDouble(sc.nextLine());
                        account.deposit(dep);
                        System.out.println("Deposit successful!");
                        break;

                    case 3:
                        System.out.print("Enter amount: ");
                        double amt = Double.parseDouble(sc.nextLine());

                        if (account.withdraw(amt)) {
                            System.out.println("Withdrawal successful!");
                        } else {
                            System.out.println("Transaction failed! Check balance or daily limit (10,000).");
                        }
                        break;

                    case 4:
                        account.miniStatement();
                        break;

                    case 5:
                        System.out.println("Thank you! Visit again.");
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }
}
