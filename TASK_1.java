                            //Task:1-ONLINE RESERVATION SYSTEM 
import java.util.Scanner;
/*This class represents a user in the system. It has two private fields,username and 
password,and a constructor to initialize these fields. It also has a method authenticate
to check if the provided username and password match the stored credentials.*/
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
/*This method prompts the user to input details such as train number, class type, date of 
journey,source station, and destination station. After inputting the details, it 
simulates inserting the reservation into database by printing out the reservation details.*/
class ReservationSystem {
    public void makeReservation() {
    	System.out.println("Making Reservation:");
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter train number: ");
        String trainNumber = sc.nextLine();
        System.out.print("Enter class type: ");
        String classType = sc.nextLine();
        System.out.print("Enter date of journey (YYYY-MM-DD): ");
        String dateOfJourney = sc.nextLine();
        System.out.print("Enter source station: ");
        String sourceStation = sc.nextLine();
        System.out.print("Enter destination station: ");
        String destinationStation = sc.nextLine();
        //Printing the reservation details
        System.out.println("Reservation Details:");
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Class Type: " + classType);
        System.out.println("Date of Journey: " + dateOfJourney);
        System.out.println("Source Station: " + sourceStation);
        System.out.println("Destination Station: " + destinationStation);
        System.out.println("Reservation made successfully!");}
/*This method prompts the user to input a PNR (Passenger Name Record) number to cancel 
 the reservation. It then calls the private method cancelReservationInDatabase() to 
 simulate canceling the reservation in the database.If the pnr no is found then the 
 reservation with cancelled */
    public void cancelReservation() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Cancel Reservation:");
        System.out.print("Enter PNR number to cancel the reservation: ");
        String pnr = scanner.nextLine();
        boolean reservationCancelled = cancelReservationInDatabase(pnr);
        if (reservationCancelled) {
            System.out.println("Reservation with PNR " + pnr + " cancelled successfully.");
        } else {
            System.out.println("Reservation with PNR " + pnr + " not found. Please check the PNR number.");
        }
    }
    private boolean cancelReservationInDatabase(String pnr) {
        return true;
    }
}
public class TASK_1 {
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User admin = new User("admin", "admin123");

        System.out.println("Welcome to Online Reservation System");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
/*It checks if the provided username and password match the admin's credentials using 
the authenticate() method of User class.If the authentication is successful,it displays
a welcome message and calls the showMenu() method to display the main menu.If the
 authentication fails, it displays an error message and exits the program.*/
        if (admin.authenticate(username, password)) {
            currentUser = admin;
            System.out.println("Login successful!");
            showMenu();
        } else {
            System.out.println("Invalid username or password. Exiting...");
        }
    }
/*This method displays a menu to the user with options to make a reservation, cancel a 
reservation, or logout. It prompts the user to input their choice.Based on the user's 
choice, it calls the corresponding method from the ReservationSystem class 
(makeReservation() or cancelReservation()).If the user chooses to logout, it exits the
 method and returns to the main() method.*/
    private static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        ReservationSystem reservationSystem = new ReservationSystem();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reservationSystem.makeReservation();
                    
                    break;
                case 2:
                    reservationSystem.cancelReservation();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}