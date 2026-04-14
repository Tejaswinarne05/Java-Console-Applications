
import java.util.*;

class Account {
    int accNo;
    String name;
    double balance;
    List<String> history;

    Account(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
        this.history = new ArrayList<>();
        history.add("Account created with balance: " + balance);
    }

    void deposit(double amount) {
        balance += amount;
        history.add("Deposited: " + amount + " | Balance: " + balance);
    }

    boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        history.add("Withdrawn: " + amount + " | Balance: " + balance);
        return true;
    }

    void addHistory(String msg) {
        history.add(msg);
    }

    void display() {
        System.out.println("\nAccount No: " + accNo);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
    }

    void showHistory() {
        System.out.println("\nTransaction History:");
        for (String h : history) {
            System.out.println(h);
        }
    }
}

public class BankSystem {

    static List<Account> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("\n--- Bank System ---");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. View Account");
                System.out.println("5. Transfer Money");
                System.out.println("6. View Transaction History");
                System.out.println("7. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        viewAccount();
                        break;
                    case 5:
                        transfer();
                        break;
                    case 6:
                        showHistory();
                        break;
                    case 7:
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

    static Account findAccount(int accNo) {
        for (Account a : accounts) {
            if (a.accNo == accNo)
                return a;
        }
        return null;
    }

    static void createAccount() {
        try {
            System.out.print("Enter Account Number: ");
            int accNo = Integer.parseInt(sc.nextLine());

            if (findAccount(accNo) != null) {
                System.out.println("Account already exists!");
                return;
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Balance: ");
            double balance = Double.parseDouble(sc.nextLine());

            accounts.add(new Account(accNo, name, balance));
            System.out.println("Account created successfully!");

        } catch (Exception e) {
            System.out.println("Error in input!");
        }
    }

    static void deposit() {
        try {
            System.out.print("Enter Account Number: ");
            int accNo = Integer.parseInt(sc.nextLine());

            Account a = findAccount(accNo);
            if (a == null) {
                System.out.println("Account not found!");
                return;
            }

            System.out.print("Enter Amount: ");
            double amt = Double.parseDouble(sc.nextLine());

            a.deposit(amt);
            System.out.println("Deposit successful!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void withdraw() {
        try {
            System.out.print("Enter Account Number: ");
            int accNo = Integer.parseInt(sc.nextLine());

            Account a = findAccount(accNo);
            if (a == null) {
                System.out.println("Account not found!");
                return;
            }

            System.out.print("Enter Amount: ");
            double amt = Double.parseDouble(sc.nextLine());

            if (a.withdraw(amt)) {
                System.out.println("Withdrawal successful!");
            } else {
                System.out.println("Insufficient balance!");
            }

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void viewAccount() {
        try {
            System.out.print("Enter Account Number: ");
            int accNo = Integer.parseInt(sc.nextLine());

            Account a = findAccount(accNo);
            if (a == null) {
                System.out.println("Account not found!");
                return;
            }

            a.display();

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void transfer() {
        try {
            System.out.print("From Account: ");
            int fromAcc = Integer.parseInt(sc.nextLine());

            System.out.print("To Account: ");
            int toAcc = Integer.parseInt(sc.nextLine());

            Account from = findAccount(fromAcc);
            Account to = findAccount(toAcc);

            if (from == null || to == null) {
                System.out.println("Invalid account(s)!");
                return;
            }

            System.out.print("Enter Amount: ");
            double amt = Double.parseDouble(sc.nextLine());

            if (!from.withdraw(amt)) {
                System.out.println("Insufficient balance!");
                return;
            }

            to.deposit(amt);

            from.addHistory("Transferred " + amt + " to Account " + toAcc);
            to.addHistory("Received " + amt + " from Account " + fromAcc);

            System.out.println("Transfer successful!");

        } catch (Exception e) {
            System.out.println("Error in transfer!");
        }
    }

    static void showHistory() {
        try {
            System.out.print("Enter Account Number: ");
            int accNo = Integer.parseInt(sc.nextLine());

            Account a = findAccount(accNo);
            if (a == null) {
                System.out.println("Account not found!");
                return;
            }

            a.showHistory();

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }
}