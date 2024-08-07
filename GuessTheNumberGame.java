

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 10; // Limit the number of attempts
        int rounds = 0;
        int totalScore = 0;
        
        while (true) {
            rounds++;
            int numberToGuess = random.nextInt(100) + 1; // Generate a number between 1 and 100
            int attemptsLeft = maxAttempts;
            boolean hasGuessedCorrectly = false;
            
            System.out.println("Round " + rounds);
            System.out.println("Guess the number between 1 and 100:");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the number correctly.");
                    hasGuessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                	System.out.println();
                    System.out.println("Too low! Try again.");
                } else {
                	System.out.println();
                    System.out.println("Too high! Try again.");
                }

                if (attemptsLeft > 0) {
                    System.out.println("Attempts left: " + attemptsLeft);
                    System.out.println();
                } else {
                    System.out.println("No attempts left! The correct number was " + numberToGuess);
                }
            }

            // Update score based on attempts
            if (hasGuessedCorrectly) {
                int score = attemptsLeft + 1; // Points based on remaining attempts
                totalScore += score;
                System.out.println("Your score for this round: " + score);
            } else {
                System.out.println("Better luck next time!");
            }

            System.out.println("Total score so far: " + totalScore);
            System.out.println();
            System.out.println("Do you want to play another round? (yes/no)");
            scanner.nextLine(); // Consume newline
            String playAgain = scanner.nextLine();

            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Game over! Your final score is: " + totalScore);
        
    }
}

