import java.util.Scanner;
public class TASK_2{
    public static void main(String[] args) {
/*MAX_NUMBER represents the maximum value for the random number to be generated.MAX_ATTEMPTS represents the maximum 
number of attempts allowed for guessing the number.final keyword indicates that these variables cannot be reassigned
The integer variable score will keep track of the user's score throughout the game.and is initialised to 0 at start
Then we print a message indicating the start of the Number Guessing Game. */
        final int MAX_NUMBER = 100;
        final int MAX_ATTEMPTS = 5;
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        System.out.println("Number Guessing Game!");
/*This infinite loop will continue until explicitly broken out of using a break statement.randomNumber generates a 
random number between 1 and MAX_NUMBER (inclusive) using the Math.random() method. The (int) casting converts the
result to an integer, and + 1 ensures that the generated number is within the range [1, MAX_NUMBER].attempts is 
initialized to 0 to keep track of the number of attempts made by the user to guess the random number.We will print
messages to inform the user that a random number has been generated and indicating the number of attempts available
to guess the number.*/
          while (true) {
            int randomNumber = (int) (Math.random() * MAX_NUMBER) + 1;
            int attempts = 0;
            System.out.println("A random number has been generated between 1 and 100.");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");
/*nested while loop will continue until the no. of attempts reaches the maximum allowed attempts.The printing lines
prompt the user to enter a guess for the random number and read the input using the Scanner object.The user's guess
is stored in the variable guess,and the attempts counter is incremented.*/            
           while (attempts < MAX_ATTEMPTS) {
                System.out.print("Guess a number: ");
                int guess = scanner.nextInt();
                attempts++;
/*The if else-if condition check if the user's guess matches the random number.If the guess is correct,a 
congratulatory message is printed, and the user earns points based on the number of attempts remaining. If the guess
is incorrect, the program provides feedback to the user, indicating whether the guess was lower or higher than the 
random no. generated */
                if (guess == randomNumber) {
                    System.out.println(" You've guessed the correct number.");
                    int points = MAX_ATTEMPTS - attempts + 1; // More points for fewer attempts
                    score += points;
                    System.out.println("You earned " + points + " points for this round.");
                    break;
                } else if (guess < randomNumber) {
                    System.out.println("Lower than the random no");
                } else {
                    System.out.println("Higher than the random 	no");
                }}
/*The if loop  checks if the user has used all their attempts without guessing the correct number.If so,it informs
 the user that all attempts have been used and displays of the correct number.        */
            if (attempts == MAX_ATTEMPTS) {
                System.out.println("Used all your attempts.The correct number was " + randomNumber);
            }
/*These lines prompt the user to decide whether to play the game again.If the user enters anything other than "yes," 
the outer loop is exited, and the game ends.         */
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            } 
          }
/*This line prints the user's final score after the game has ended.*/
        System.out.println("Your final score: " + score);   
    }}