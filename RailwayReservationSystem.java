import java.util.*;

class Ticket {
    int ticketId;
    String passengerName;
    int seatsBooked;

    Ticket(int ticketId, String passengerName, int seatsBooked) {
        this.ticketId = ticketId;
        this.passengerName = passengerName;
        this.seatsBooked = seatsBooked;
    }

    void display() {
        System.out.println("\nTicket ID: " + ticketId);
        System.out.println("Passenger: " + passengerName);
        System.out.println("Seats Booked: " + seatsBooked);
    }
}

public class RailwayReservationSystem {

    static final int TOTAL_SEATS = 50;
    static int availableSeats = TOTAL_SEATS;
    static int ticketCounter = 1001;

    static List<Ticket> tickets = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("\n--- Railway Reservation System ---");
                System.out.println("1. Book Ticket");
                System.out.println("2. Cancel Ticket");
                System.out.println("3. View Available Seats");
                System.out.println("4. View All Tickets");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        bookTicket();
                        break;
                    case 2:
                        cancelTicket();
                        break;
                    case 3:
                        System.out.println("Available Seats: " + availableSeats);
                        break;
                    case 4:
                        viewTickets();
                        break;
                    case 5:
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

    static void bookTicket() {
        try {
            System.out.print("Enter Passenger Name: ");
            String name = sc.nextLine();

            System.out.print("Enter number of seats: ");
            int seats = Integer.parseInt(sc.nextLine());

            if (seats > availableSeats) {
                System.out.println("Not enough seats available!");
                return;
            }

            int ticketId = ticketCounter++;
            Ticket t = new Ticket(ticketId, name, seats);

            tickets.add(t);
            availableSeats -= seats;

            System.out.println("Booking successful!");
            t.display();

        } catch (Exception e) {
            System.out.println("Error in booking!");
        }
    }

    static void cancelTicket() {
        try {
            System.out.print("Enter Ticket ID: ");
            int id = Integer.parseInt(sc.nextLine());

            Iterator<Ticket> it = tickets.iterator();

            while (it.hasNext()) {
                Ticket t = it.next();

                if (t.ticketId == id) {
                    availableSeats += t.seatsBooked;
                    it.remove();

                    System.out.println("Ticket cancelled successfully!");
                    return;
                }
            }

            System.out.println("Ticket not found!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void viewTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Ticket t : tickets) {
            t.display();
        }
    }
}
