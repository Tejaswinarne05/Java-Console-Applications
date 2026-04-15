import java.util.*;

public class NumberSystemConverter {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("\n--- Number System Converter ---");
                System.out.println("1. Decimal to Binary/Octal/Hex");
                System.out.println("2. Binary to Decimal");
                System.out.println("3. Octal to Decimal");
                System.out.println("4. Hexadecimal to Decimal");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        decimalConversions();
                        break;
                    case 2:
                        binaryToDecimal();
                        break;
                    case 3:
                        octalToDecimal();
                        break;
                    case 4:
                        hexToDecimal();
                        break;
                    case 5:
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

    // Decimal conversions
    static void decimalConversions() {
        try {
            System.out.print("Enter Decimal Number: ");
            int num = Integer.parseInt(sc.nextLine());

            System.out.println("Binary: " + Integer.toBinaryString(num));
            System.out.println("Octal: " + Integer.toOctalString(num));
            System.out.println("Hexadecimal: " + Integer.toHexString(num).toUpperCase());

        } catch (Exception e) {
            System.out.println("Invalid decimal input!");
        }
    }

    // Binary to Decimal
    static void binaryToDecimal() {
        System.out.print("Enter Binary Number: ");
        String binary = sc.nextLine();

        if (!binary.matches("[01]+")) {
            System.out.println("Invalid binary number!");
            return;
        }

        int decimal = Integer.parseInt(binary, 2);
        System.out.println("Decimal: " + decimal);
    }

    // Octal to Decimal
    static void octalToDecimal() {
        System.out.print("Enter Octal Number: ");
        String octal = sc.nextLine();

        if (!octal.matches("[0-7]+")) {
            System.out.println("Invalid octal number!");
            return;
        }

        int decimal = Integer.parseInt(octal, 8);
        System.out.println("Decimal: " + decimal);
    }

    // Hexadecimal to Decimal
    static void hexToDecimal() {
        System.out.print("Enter Hexadecimal Number: ");
        String hex = sc.nextLine();

        if (!hex.matches("[0-9A-Fa-f]+")) {
            System.out.println("Invalid hexadecimal number!");
            return;
        }

        int decimal = Integer.parseInt(hex, 16);
        System.out.println("Decimal: " + decimal);
    }
}
