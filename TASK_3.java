                                         //TASK_3:ATM INTERFACE                     
import java.util.Scanner;
class Transaction {
// Declares private member variables type (String) and amount (double) to represent the type and amount of a transaction.
    private String type;
    private double amount;
//Constructor for the Transaction class, initializes the type and amount of the transaction.
    public Transaction(String type, double amount) {
//Assigns the values passed to the constructor to the respective member variables.
        this.type = type;
        this.amount = amount;
    }
/*Overrides the toString() method inherited from the Object class.and then returns a string representation of the transaction, 
 including its type and amount.*/
@Override
    public String toString() {
        return "Type: " + type + ", Amount: $" + amount;
    }
}
class User_1 {
/*Declares private member variables userID, userPIN, transactionHistory, transactionCount,and balance to represent user 
	information and transaction history.*/
    private String userID;
    private String userPIN;
    private Transaction[] transactionHistory;
    private int transactionCount;
    private double balance;
/*Constructor for the User_1 class, initializes user ID and PIN, as well as the transaction history array, transaction count,
 and balance and then assigns the values passed to the constructor to the respective member variables.*/
    public User_1(String userID, String userPIN) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.transactionHistory = new Transaction[100]; // Limiting to 100 transactions
        this.transactionCount = 0;
        this.balance = 0; // Initialize balance to 0
    }
//Getter method to retrieve the User's ID and User's Pin
    public String getUserID() {
        return userID;
    }
    public String getUserPIN() {
        return userPIN;
    }
//Getter method to retrieve the user's transaction history and user's balance.
    public Transaction[] getTransactionHistory() {
        return transactionHistory;
    }
    public double getBalance() {
        return balance;
    }
/*This line defines a method named addTransaction that takes a Transaction object as a parameter. The purpose of this method 
 is to add a transaction to the transaction history of the user.Using if condition it checks the current number of transactions
 (transactionCount) is less than the length of the transactionHistory array. It ensures that there is enough space in the 
 transaction history array to add another transaction.If there is space available in the transaction history array,it assigns 
the transaction parameter to the next available slot in the array, determined by the current value of transactionCount.After 
adding the transaction to the transaction history array,it increments the transactionCount variable by 1. This keeps track of 
how many transactions have been added to the array. If there is no space available in the transaction history array,it indicates
 the start of the code block for the else statement.Then it  prints a message to notify the user that the transaction history is
full and no more transactions can be added.*/
    public void addTransaction(Transaction transaction) {
        if (transactionCount < transactionHistory.length) {
            transactionHistory[transactionCount] = transaction;
            transactionCount++;
        } else {
            System.out.println("Transaction history full. Cannot add more transactions.");
        }
    }
//Method to update the user's balance by a given amount.
    public void updateBalance(double amount) {
        balance += amount;
    }
}
// Defines an interface named ATMOperations with abstract methods for ATM operations.
interface ATMOperations {
    void showTransactionHistory(User_1 user);
    void withdraw(User_1 user, double amount);
    void deposit(User_1 user, double amount);
    void transfer(User_1 user, String recipientID, double amount);
}

/* Implements the ATMOperations interface, providing concrete implementations for the ATM operations.This defines a class
 named ATMImplementation that implements the ATMOperations interface. It means that the ATMImplementation class must provide
 concrete implementations for all methods defined in the ATMOperations interface.This class declares a method named 
 showTransactionHistory that takes a User_1 object as a parameter. This method is responsible for displaying the transaction
 history of the given user.Then prints a message indicating that the transaction history is being displayed for the user with
 the specified user ID.Then it retrieves the transaction history of the user by calling the getTransactionHistory method of the
 User_1 class and assigns it to an array of Transaction objects named transactions.Then the for loop that iterates over each 
 Transaction object in the transactions array.It checks if  the current transaction object is not null. This check is necessary
 because the transaction history array may have null values for slots that haven't been filled with transactions yet.It prints 
 details of the current transaction by implicitly calling its toString method, which was overridden in the Transaction class to
 provide a string representation of the transaction.And then prints the current balance of the user by calling the getBalance
 method of the User_1 class.*/
class ATMImplementation implements ATMOperations {
    @Override
    public void showTransactionHistory(User_1 user) {
        System.out.println("Transaction History for User: " + user.getUserID());
        Transaction[] transactions = user.getTransactionHistory();
        for (Transaction transaction : transactions) {
            if (transaction != null) {
                System.out.println(transaction);
            }
        }
        System.out.println("Current Balance: $" + user.getBalance());
    }
/*A method named withdraw that takes a User_1 object and a double value representing the withdrawal amount as parameters.This
method is responsible for processing withdrawal transactions for the given user.Now it checks if the withdrawal amount is greater
than the current balance of the user.If it is true it prints a message that the user has insufficient funds if the withdrawal
amount exceeds the current balance.Return line exits the method early if the withdrawal amount exceeds the user's balance, as 
the withdrawal cannot be completed.*/
    @Override
    public void withdraw(User_1 user, double amount) {
        if (amount > user.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }
/*This line creates a new Transaction object representing the withdrawal and adds it to the user's transaction history by calling
the addTransaction method of the User_1 class.Then it updates the user's balance by subtracting the withdrawal amount from it
using the updateBalance method of the User_1 and then it prints a message confirming that the withdrawal was successful, 
including the withdrawn amount.Then prints the current balance of the user after the withdrawal operation.*/
        user.addTransaction(new Transaction("Withdraw", amount));
        user.updateBalance(-amount); // Deduct amount from balance
        System.out.println("$" + amount + " withdrawn successfully.");
        System.out.println("Current Balance: $" + user.getBalance());
    }

    @Override
/*A method named deposit that takes two parameters: user of type User_1 and amount of type double. This method is responsible for
depositing money into the user's account.It creates a new Transaction object representing the deposit operation with a type of 
"Deposit" and the specified amount. It then adds this transaction to the user's transaction history.Then it increases the user's
balance by the deposited amount. The updateBalance method is called with the amount parameter, effectively adding the deposited
amount to the user's balance.Then it prints a message confirming that the deposit was successful, including the deposited amount.
And then prints the current balance of the user after the deposit operation. It retrieves the updated balance using the 
getBalance method of the User_1 class and includes it in the message to inform the user of their current balance.*/
      public void deposit(User_1 user, double amount) {
        user.addTransaction(new Transaction("Deposit", amount));
        user.updateBalance(amount); // Add amount to balance
        System.out.println("$" + amount + " deposited successfully.");
        System.out.println("Current Balance: $" + user.getBalance());
    }

    @Override
/*This method named transfer that takes three parameters: user of type User_1, recipientID of type String, and amount of type 
double. This method is responsible for transferring money from the user's account to another account.It checks if the amount to
be transferred is greater than current balance of the user.If the condition is true, it means the user doesn't have sufficient
funds for the transfer,it prints a message indicating that user has insufficient funds for the transfer.After printing the 
message, the method exits early using the return statement, as the transfer cannot proceed due to insufficient funds.*/
    public void transfer(User_1 user, String recipientID, double amount) {
    	if (amount > user.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }
/*If the user has sufficient funds it creates a new Transaction object representing the transfer and adds it to the user's 
transaction history. The transaction type is "Transfer to [recipientID]", where recipientID is the ID of the recipient.Now 
subtracts the amount being transferred from the user's balance. This effectively deducts the transferred amount from the user's 
account.After it prints a message confirming that the transfer was successful, indicating the transferred amount and the 
recipient's ID.And then prints the current balance of the user after the transfer operation. This ensures the user is informed 
of their updated balance after the transfer.*/
        user.addTransaction(new Transaction("Transfer to " + recipientID, amount));
        user.updateBalance(-amount); // Deduct amount from balance
        System.out.println("$" + amount + " transferred successfully to " + recipientID + ".");
        System.out.println("Current Balance: $" + user.getBalance());
    }
}
public class TASK_3{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
/*Creates an instance of the ATMImplementation class to perform ATM operations.And then creates a user object with a specific 
 user ID and PIN.Declares a boolean variable to control whether the ATM program should continue running.//Starts a while loop 
to repeatedly display the ATM menu and perform operations until the user chooses to quit.*/
        ATMImplementation atm = new ATMImplementation();
        User_1 user = new User_1("123456", "7890");
        boolean continueATM = true;
        System.out.println("Welcome to the ATM!");
        while (continueATM) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Show Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
           int choice = scanner.nextInt();
          //Switch statement to execute different code blocks based on the user's choice of operation.
            switch (choice) {
         //Calls the showTransactionHistory method of the ATMImplementation class to display the user's transaction history.
                case 1:
                    atm.showTransactionHistory(user);
                    break;
             //Calls the withdraw method of the ATMImplementation class to withdraw money from the user's account.
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(user, withdrawAmount);
                    break;
                    //Calls the deposit method of the ATMImplementation class to deposit money into the user's account.
                case 3:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(user, depositAmount);
                    break;
       //Calls the transfer method of the ATMImplementation class to transfer money from the user's account to another account.
                case 4:
                    System.out.print("Enter recipient ID: ");
                    String recipientID = scanner.next();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    atm.transfer(user, recipientID, transferAmount);
                    break;
          //Sets continueATM to false to exit the while loop and displays a goodbye message.
                case 5:
                    continueATM = false;
                    System.out.println("Thank you for using the ATM");
                    break;
        //Displays a message if the user enters an invalid option.
                default:
                    System.out.println("Invalid option.Choose a valid option.");
            }
        }
    }
}