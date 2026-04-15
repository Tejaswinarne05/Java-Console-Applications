import java.util.*;

class Bus {
    int busId;
    String busName;
    int totalSeats;
    boolean[] seats; // true = booked

    Bus(int busId, String busName, int totalSeats) {
        this.busId = busId;
        this.busName = busName;
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats];
    }

    void displaySeats() {
        System.out.println("\nBus: " + busName + " (ID: " + busId + ")");
        for (int i = 0; i < seats.length; i++) {
            System.out.print("Seat " + (i + 1) + ": " + (seats[i] ? "Booked" : "Available") + " | ");
            if ((i + 1) % 5 == 0) System.out.println();
        }
        System.out.println();
    }
}

public class BusReservationSystem {

    static List<Bus> buses = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadBuses();

        while (true) {
            try {
                System.out.println("\n--- Bus Reservation System ---");
                System.out.println("1. View Buses");
                System.out.println("2. View Seat Status");
                System.out.println("3. Book Seat");
                System.out.println("4. Cancel Seat");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        showBuses();
                        break;
                    case 2:
                        viewSeats();
                        break;
                    case 3:
                        bookSeat();
                        break;
                    case 4:
                        cancelSeat();
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

    static void loadBuses() {
        buses.add(new Bus(1, "Express Line", 20));
        buses.add(new Bus(2, "City Rider", 15));
    }

    static Bus findBus(int id) {
        for (Bus b : buses) {
            if (b.busId == id) return b;
        }
        return null;
    }

    static void showBuses() {
        for (Bus b : buses) {
            System.out.println("Bus ID: " + b.busId + " | Name: " + b.busName);
        }
    }

    static void viewSeats() {
        System.out.print("Enter Bus ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Bus b = findBus(id);
        if (b == null) {
            System.out.println("Bus not found!");
            return;
        }

        b.displaySeats();
    }

    static void bookSeat() {
        try {
            System.out.print("Enter Bus ID: ");
            int id = Integer.parseInt(sc.nextLine());

            Bus b = findBus(id);
            if (b == null) {
                System.out.println("Bus not found!");
                return;
            }

            b.displaySeats();

            System.out.print("Enter Seat Number: ");
            int seatNo = Integer.parseInt(sc.nextLine());

            if (seatNo < 1 || seatNo > b.totalSeats) {
                System.out.println("Invalid seat number!");
                return;
            }

            if (b.seats[seatNo - 1]) {
                System.out.println("Seat already booked!");
                return;
            }

            b.seats[seatNo - 1] = true;
            System.out.println("Seat booked successfully!");

        } catch (Exception e) {
            System.out.println("Error in booking!");
        }
    }

    static void cancelSeat() {
        try {
            System.out.print("Enter Bus ID: ");
            int id = Integer.parseInt(sc.nextLine());

            Bus b = findBus(id);
            if (b == null) {
                System.out.println("Bus not found!");
                return;
            }

            System.out.print("Enter Seat Number: ");
            int seatNo = Integer.parseInt(sc.nextLine());

            if (seatNo < 1 || seatNo > b.totalSeats) {
                System.out.println("Invalid seat number!");
                return;
            }

            if (!b.seats[seatNo - 1]) {
                System.out.println("Seat is already available!");
                return;
            }

            b.seats[seatNo - 1] = false;
            System.out.println("Seat cancelled successfully!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }
}
