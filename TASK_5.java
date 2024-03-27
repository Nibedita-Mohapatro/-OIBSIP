import java.util.*;
/* Book class to represent each book in the library.Then declares a private instance variable
title and author of String type,id of int type,isIssued of boolean type .Then the constructor
of the Book class initializes the title, author, and id of the book. Assigns the value of
 different parameter to the instance variable Initializes the isIssued variable to false by 
 default when a new book object is created.*/
class Book {
    private String title;
    private String author;
    private int id;
    private boolean isIssued;
    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.isIssued = false;
    }
// Getters and setters methods for variables to provide access to private instance variables.
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getId() {
        return id;
    }
    public boolean isIssued() {
        return isIssued;
    }
    public void setIssued(boolean issued) {
        isIssued = issued;
    }
}
/* Library class to manage books and users.It declares private instance variables for managing 
books, members, and fines.Constructor for the Library class. Initializes books, members,and 
fines collections.The class defines various methods to perform actions such as adding books, 
browsing books, adding members, issuing books, returning books, generating fines, advance
booking, generating reports, and finding a book by ID.
*/
class Library {
    private List<Book> books;
    private Map<Integer, String> members; // Assuming members are identified by IDs
    private Map<Integer, Integer> fines; // Mapping book ID to fine amount

    public Library() {
        books = new ArrayList<>();
        members = new HashMap<>();
        fines = new HashMap<>();
    }

    // Admin functionality to add a new book
    public void addBook(String title, String author, int id) {
        Book newBook = new Book(title, author, id);
        books.add(newBook);
        System.out.println("Book added successfully!");
    }

    // User functionality to browse available books
    public void browseBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " [ID: " + book.getId() + "]");
        }
    }

    // Admin functionality to add a new member
    public void addMember(int memberId, String memberName) {
        members.put(memberId, memberName);
        System.out.println("Member added successfully!");
    }

    // User functionality to issue a book
    public void issueBook(int bookId, int memberId) {
        Book bookToIssue = findBookById(bookId);
        if (bookToIssue != null && !bookToIssue.isIssued()) {
            bookToIssue.setIssued(true);
            System.out.println("Book issued successfully to member " + members.get(memberId));
        } else {
            System.out.println("Book not available or already issued!");
        }
    }

    // User functionality to return a book
    public void returnBook(int bookId) {
        Book bookToReturn = findBookById(bookId);
        if (bookToReturn != null && bookToReturn.isIssued()) {
            bookToReturn.setIssued(false);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book not issued or invalid book ID!");
        }
    }

    // Functionality to generate fine for late return
    public void generateFine(int bookId, int daysLate) {
        // Basic fine calculation (for demonstration)
        double fineAmount = daysLate * 1.5; // $1.5 per day late
        fines.put(bookId, (int) fineAmount);
        System.out.println("Fine generated: $" + fineAmount);
    }

    // Functionality for advance booking
    public void advanceBooking(int bookId, int memberId) {
        // Implement advance booking logic here
        System.out.println("Advance booking successful!");
    }

    // Functionality to generate a report
    public void generateReport() {
        // Implement report generation logic here
        System.out.println("Report generated successfully!");
    }

    // Helper method to find a book by ID
    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }
}
public class TASK_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Creates an instance of the Library class named library.
        Library library = new Library();
        System.out.println("Welcome to the Digital Library System");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Book");
            System.out.println("2. Browse Books");
            System.out.println("3. Add Member");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Generate Fine");
            System.out.println("7. Advance Booking");
            System.out.println("8. Generate Report");
            System.out.println("9. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    library.addBook(title, author, bookId);
                    break;
                case 2:
                    library.browseBooks();
                    break;
                case 3:
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();
                    library.addMember(memberId, memberName);
                    break;
                case 4:
                    System.out.print("Enter book ID to issue: ");
                    int issueBookId = scanner.nextInt();
                    System.out.print("Enter member ID: ");
                    int issueMemberId = scanner.nextInt();
                    library.issueBook(issueBookId, issueMemberId);
                    break;
                case 5:
                    System.out.print("Enter book ID to return: ");
                    int returnBookId = scanner.nextInt();
                    library.returnBook(returnBookId);
                    break;
                case 6:
                    System.out.print("Enter book ID: ");
                    int fineBookId = scanner.nextInt();
                    System.out.print("Enter number of days late: ");
                    int daysLate = scanner.nextInt();
                    library.generateFine(fineBookId, daysLate);
                    break;
                case 7:
                    System.out.print("Enter book ID for advance booking: ");
                    int advanceBookId = scanner.nextInt();
                    System.out.print("Enter member ID: ");
                    int advanceMemberId = scanner.nextInt();
                    library.advanceBooking(advanceBookId, advanceMemberId);
                    break;
                case 8:
                    library.generateReport();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please choose a valid option.");
            }
        }
    }
}