import java.util.*;
import java.util.concurrent.*;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    void display() {
        System.out.println("\n" + question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
}

public class QuizApp {

    static List<Question> questions = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadQuestions();

        System.out.println("===== ONLINE QUIZ WITH TIMER =====");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        int score = 0;
        List<Integer> userAnswers = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            q.display();

            int answer = getAnswerWithTimer(10); // 10 seconds timer
            userAnswers.add(answer);

            if (answer == q.correctAnswer) {
                score++;
            }
        }

        // Final Result
        System.out.println("\n===== FINAL RESULT =====");
        System.out.println("Name: " + name);
        System.out.println("Score: " + score + "/" + questions.size());

        double percentage = (score * 100.0) / questions.size();
        System.out.println("Percentage: " + percentage + "%");

        // Show answers
        System.out.println("\n===== ANSWER REVIEW =====");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);

            System.out.println("\nQ" + (i + 1) + ": " + q.question);

            if (userAnswers.get(i) >= 0 && userAnswers.get(i) < 4) {
                System.out.println("Your Answer: " + q.options[userAnswers.get(i)]);
            } else {
                System.out.println("Your Answer: Not Attempted");
            }

            System.out.println("Correct Answer: " + q.options[q.correctAnswer]);
        }

        System.out.println("\nQuiz Completed!");
    }

    // TIMER FUNCTION
    static int getAnswerWithTimer(int seconds) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> future = executor.submit(() -> {
            try {
                System.out.print("Enter your answer (1-4): ");
                return Integer.parseInt(sc.nextLine()) - 1;
            } catch (Exception e) {
                return -1;
            }
        });

        try {
            return future.get(seconds, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("\n⏰ Time's up! Moving to next question...");
            return -1;
        } catch (Exception e) {
            return -1;
        } finally {
            executor.shutdownNow();
        }
    }

    static void loadQuestions() {
        questions.add(new Question(
                "Which language is used for Android development?",
                new String[]{"Java", "Python", "C++", "Ruby"},
                0));

        questions.add(new Question(
                "Which database is used with PHP?",
                new String[]{"MySQL", "MongoDB", "Oracle", "SQLite"},
                0));

        questions.add(new Question(
                "What does HTML stand for?",
                new String[]{
                        "Hyper Text Markup Language",
                        "High Text Machine Language",
                        "Hyper Tool Multi Language",
                        "None"
                },
                0));

        questions.add(new Question(
                "Which is not a programming language?",
                new String[]{"Java", "Python", "HTML", "C"},
                2));
    }
}
