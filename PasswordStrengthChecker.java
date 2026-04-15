import java.util.*;

public class PasswordStrengthChecker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Password Strength Checker ===");
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        checkStrength(password);
    }

    static void checkStrength(String password) {

        int score = 0;
        List<String> suggestions = new ArrayList<>();

        // Length check
        if (password.length() >= 8) {
            score++;
        } else {
            suggestions.add("Use at least 8 characters");
        }

        // Uppercase check
        if (password.matches(".*[A-Z].*")) {
            score++;
        } else {
            suggestions.add("Add uppercase letters (A-Z)");
        }

        // Lowercase check
        if (password.matches(".*[a-z].*")) {
            score++;
        } else {
            suggestions.add("Add lowercase letters (a-z)");
        }

        // Digit check
        if (password.matches(".*\\d.*")) {
            score++;
        } else {
            suggestions.add("Include numbers (0-9)");
        }

        // Special character check
        if (password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            score++;
        } else {
            suggestions.add("Add special characters (!@#$...)");
        }

        // Strength result
        System.out.println("\n--- Result ---");

        if (score <= 2) {
            System.out.println("Strength: WEAK ❌");
        } else if (score <= 4) {
            System.out.println("Strength: MEDIUM ⚠");
        } else {
            System.out.println("Strength: STRONG ✅");
        }

        // Suggestions
        if (!suggestions.isEmpty()) {
            System.out.println("\nSuggestions to improve:");
            for (String s : suggestions) {
                System.out.println("- " + s);
            }
        } else {
            System.out.println("Great! Your password is strong 💪");
        }
    }
}
