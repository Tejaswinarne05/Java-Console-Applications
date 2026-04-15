import java.util.*;

class Room {
    int roomNumber;
    String category; // AC / Non-AC
    boolean isBooked;
    String customerName;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
        this.customerName = "";
    }

    void display() {
        System.out.println("Room No: " + roomNumber +
                " | Type: " + category +
                " | Status: " + (isBooked ? "Booked" : "Available"));
    }
}

public class HotelBookingSystem {

    static List<Room> rooms = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        initializeRooms();

        while (true) {
            try {
                System.out.println("\n--- Hotel Room Booking System ---");
                System.out.println("1. Show Available Rooms");
                System.out.println("2. Book Room");
                System.out.println("3. Cancel Booking");
                System.out.println("4. View All Rooms");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        showAvailableRooms();
                        break;
                    case 2:
                        bookRoom();
                        break;
                    case 3:
                        cancelBooking();
                        break;
                    case 4:
                        viewAllRooms();
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

    // Initialize rooms
    static void initializeRooms() {
        for (int i = 1; i <= 5; i++) {
            rooms.add(new Room(i, "AC"));
        }
        for (int i = 6; i <= 10; i++) {
            rooms.add(new Room(i, "Non-AC"));
        }
    }

    static void showAvailableRooms() {
        boolean found = false;

        for (Room r : rooms) {
            if (!r.isBooked) {
                r.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available!");
        }
    }

    static Room findRoom(int roomNo) {
        for (Room r : rooms) {
            if (r.roomNumber == roomNo) return r;
        }
        return null;
    }

    static void bookRoom() {
        try {
            System.out.print("Enter Room Number: ");
            int roomNo = Integer.parseInt(sc.nextLine());

            Room r = findRoom(roomNo);

            if (r == null) {
                System.out.println("Room not found!");
                return;
            }

            if (r.isBooked) {
                System.out.println("Room already booked!");
                return;
            }

            System.out.print("Enter Customer Name: ");
            String name = sc.nextLine();

            r.isBooked = true;
            r.customerName = name;

            System.out.println("Room booked successfully!");

        } catch (Exception e) {
            System.out.println("Error in booking!");
        }
    }

    static void cancelBooking() {
        try {
            System.out.print("Enter Room Number: ");
            int roomNo = Integer.parseInt(sc.nextLine());

            Room r = findRoom(roomNo);

            if (r == null) {
                System.out.println("Room not found!");
                return;
            }

            if (!r.isBooked) {
                System.out.println("Room is already available!");
                return;
            }

            r.isBooked = false;
            r.customerName = "";

            System.out.println("Booking canceled!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    static void viewAllRooms() {
        for (Room r : rooms) {
            r.display();
        }
    }
}
