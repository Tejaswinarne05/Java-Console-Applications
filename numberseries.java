import java.util.Random;
import java.util.Scanner;

public class numberseries {
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("🤣 Welcome to the FUNNY Number Series Game 🤣");
        System.out.println("Choose your comedy level:");
        System.out.println("1. Easy Peasy Lemon Squeezy 🍋");
        System.out.println("2. Medium Spicy 🌶️");
        System.out.println("3. Hard Like an Exams 📚");
        System.out.print("Enter your choice (1/2/3): ");

        int choice = sc.nextInt();

        switch (choice) {
    case 1:
        playEasy();
        break;
    case 2:
        playMedium();
        break;
    case 3:
        playHard();
        break;
    default:
        System.out.println("Invalid choice. Please restart and enter 1, 2, or 3.");
}

}

    private static void playEasy() {
        int score = 0;
        for (int q = 1; q <= 10; q++) {
            int start = rand.nextInt(10) + 1;
            int diff = rand.nextInt(5) + 1;
            System.out.println("\nQuestion " + q + ": Identify the next number in the series.");
            int temp = start;
            for (int i = 0; i < 5; i++) {
                System.out.print(temp + " ");
                temp += diff;
            }
            System.out.print("?\nYour Answer: ");
            int ans = sc.nextInt();
            if (ans == temp) {
                System.out.println("👏 Correct!");
                score++;
            } else {
                System.out.println("😜 Wrong! The correct answer was " + temp);
            }
        }
        System.out.println("\n🎯 Final Score (Easy Level): " + score + "/10");
    }

    private static void playMedium() {
        int score = 0;
        for (int q = 1; q <= 10; q++) {
            int start = rand.nextInt(5) + 1;
            int ratio = rand.nextInt(3) + 2;
            System.out.println("\nQuestion " + q + ": Identify the next number in the series.");
            int temp = start;
            for (int i = 0; i < 5; i++) {
                System.out.print(temp + " ");
                temp *= ratio;
            }
            System.out.print("?\nYour Answer: ");
            int ans = sc.nextInt();
            if (ans == temp) {
                System.out.println("👏 Correct!");
                score++;
            } else {
                System.out.println("😜 Wrong! The correct answer was " + temp);
            }
        }
        System.out.println("\n🎯 Final Score (Medium Level): " + score + "/10");
    }

    private static void playHard() {
        int score = 0;
        for (int q = 1; q <= 10; q++) {
            int a = rand.nextInt(5) + 1;
            int b = rand.nextInt(5) + 1;
            System.out.println("\nQuestion " + q + ": Identify the next number in the series.");
            System.out.print(a + " " + b + " ");
            int next; // no need to initialize
            for (int i = 0; i < 3; i++) {
                next = a + b;
                System.out.print(next + " ");
                a = b;
                b = next;
            }
            System.out.print("?\nYour Answer: ");
            int ans = sc.nextInt();
            next = a + b;
            if (ans == next) {
                System.out.println("👏 Correct!");
                score++;
            } else {
                System.out.println("😜 Wrong! The correct answer was " + next);
            }
        }
        System.out.println("\n🎯 Final Score (Hard Level): " + score + "/10");
    }
}
