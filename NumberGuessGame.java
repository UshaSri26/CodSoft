import java.util.Scanner;
import java.util.Random;

public class NumberGuessGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int roundsWon = 0;
        
        while (playAgain) {
            int target = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 10;
            boolean guessedCorrectly = false;
            
            System.out.println("Guess the number between 1 and 100. You have " + maxAttempts + " attempts.");
            
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.next();
                    continue;
                }
                int guess = scanner.nextInt();
                attempts++;
                
                if (guess == target) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    roundsWon++;
                    guessedCorrectly = true;
                    break;
                } else if (guess < target) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("You've run out of attempts! The number was: " + target);
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }
        
        System.out.println("Game Over! Rounds won: " + roundsWon);
        scanner.close();
    }
}
