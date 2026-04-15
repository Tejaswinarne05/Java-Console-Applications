import java.util.*;

class Voter {
    int id;
    String name;
    boolean hasVoted;

    Voter(int id, String name) {
        this.id = id;
        this.name = name;
        this.hasVoted = false;
    }
}

class Candidate {
    int id;
    String name;
    int votes;

    Candidate(int id, String name) {
        this.id = id;
        this.name = name;
        this.votes = 0;
    }
}

public class VotingSystem {

    static List<Voter> voters = new ArrayList<>();
    static List<Candidate> candidates = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadCandidates();

        while (true) {
            try {
                System.out.println("\n--- Voting System ---");
                System.out.println("1. Register Voter");
                System.out.println("2. Cast Vote");
                System.out.println("3. Show Results");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        registerVoter();
                        break;
                    case 2:
                        castVote();
                        break;
                    case 3:
                        showResults();
                        break;
                    case 4:
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

    static void loadCandidates() {
        candidates.add(new Candidate(1, "Alice"));
        candidates.add(new Candidate(2, "Bob"));
        candidates.add(new Candidate(3, "Charlie"));
    }

    static Voter findVoter(int id) {
        for (Voter v : voters) {
            if (v.id == id) return v;
        }
        return null;
    }

    static Candidate findCandidate(int id) {
        for (Candidate c : candidates) {
            if (c.id == id) return c;
        }
        return null;
    }

    static void registerVoter() {
        try {
            System.out.print("Enter Voter ID: ");
            int id = Integer.parseInt(sc.nextLine());

            if (findVoter(id) != null) {
                System.out.println("Voter already registered!");
                return;
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            voters.add(new Voter(id, name));
            System.out.println("Voter registered successfully!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void castVote() {
        try {
            System.out.print("Enter Voter ID: ");
            int voterId = Integer.parseInt(sc.nextLine());

            Voter v = findVoter(voterId);

            if (v == null) {
                System.out.println("Voter not found!");
                return;
            }

            if (v.hasVoted) {
                System.out.println("You have already voted!");
                return;
            }

            System.out.println("\n--- Candidates ---");
            for (Candidate c : candidates) {
                System.out.println(c.id + ". " + c.name);
            }

            System.out.print("Choose Candidate ID: ");
            int choice = Integer.parseInt(sc.nextLine());

            Candidate c = findCandidate(choice);

            if (c == null) {
                System.out.println("Invalid candidate!");
                return;
            }

            c.votes++;
            v.hasVoted = true;

            System.out.println("Vote cast successfully!");

        } catch (Exception e) {
            System.out.println("Error in voting!");
        }
    }

    static void showResults() {
        System.out.println("\n--- Voting Results ---");

        int maxVotes = -1;
        String winner = "";

        for (Candidate c : candidates) {
            System.out.println(c.name + " - " + c.votes + " votes");

            if (c.votes > maxVotes) {
                maxVotes = c.votes;
                winner = c.name;
            }
        }

        System.out.println("\nWinner: " + winner);
    }
}
