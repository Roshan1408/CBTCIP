import java.util.Scanner;

public class OnlineExaminationSystem {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // User data storage using arrays
        String[] usernames = new String[100];
        String[] passwords = new String[100];
        int userCount = 1;
        usernames[0] = "user";
        passwords[0] = "pass"; // Predefined user for testing

        String loggedInUser = null;
        boolean isLoggedIn = false;

        while (true) {
            // Main menu
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Update Username and Password");
            System.out.println("4. Take MCQs");
            System.out.println("5. Exit");
            System.out.println();

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) { // Login
                if (isLoggedIn) {
                    System.out.println("Already logged in.");
                    continue;
                }

                System.out.print("Enter username: ");
                String loginUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                String loginPassword = scanner.nextLine();

                boolean found = false;
                for (int i = 0; i < userCount; i++) {
                    if (usernames[i].equals(loginUsername) && passwords[i].equals(loginPassword)) {
                        loggedInUser = loginUsername;
                        isLoggedIn = true;
                        found = true;
                        System.out.println();
                        System.out.println("Login successful!");
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Invalid credentials!");
                }

            } else if (choice == 2) { // Register
                if (isLoggedIn) {
                    System.out.println("You need to log out first.");
                    continue;
                }

                System.out.print("Enter new username: ");
                String registerUsername = scanner.nextLine();
                System.out.print("Enter new password: ");
                String registerPassword = scanner.nextLine();

                boolean exists = false;
                for (int i = 0; i < userCount; i++) {
                    if (usernames[i].equals(registerUsername)) {
                        exists = true;
                        break;
                    }
                }
                if (exists) {
                    System.out.println("Username already exists!");
                } else {
                    usernames[userCount] = registerUsername;
                    passwords[userCount] = registerPassword;
                    userCount++;
                    System.out.println("Registration successful!");
                }

            } else if (choice == 3) { // Update Username and Password
                if (!isLoggedIn) {
                    System.out.println("You need to log in first.");
                    continue;
                }

                System.out.print("Enter new username: ");
                String newUsername = scanner.nextLine();
                System.out.print("Enter new password: ");
                String newPassword = scanner.nextLine();

                boolean updated = false;
                for (int i = 0; i < userCount; i++) {
                    if (usernames[i].equals(loggedInUser)) {
                        usernames[i] = newUsername;
                        passwords[i] = newPassword;
                        loggedInUser = newUsername;
                        updated = true;
                        System.out.println("Profile updated successfully!");
                        break;
                    }
                }
                if (!updated) {
                    System.out.println("Error updating profile!");
                }

            } else if (choice == 4) { // Take MCQs
                if (!isLoggedIn) {
                    System.out.println("You need to log in first.");
                    continue;
                }

                // Define MCQs
                String[][] questions = {
                    {"Who invented Java Programming?", "1. Guido van Rossum", "2. James Gosling",
                     "3. Dennis Ritchie", "4. Bjarne Stroustrup", "2"},
                    {"Which component is used to compile, debug and execute the java programs?", 
                    "1. JRE", "2. JDK", "3. JVM", "4. JIT", "2"},
                    {"Which of these cannot be used for a variable name in Java?", 
                    "1. identifier & keyword", "2. identifier", "3. keyword", "4. None of the above", "3"},
                    {"What is the extension of java code files?", "1. .java", "2. .class",
                     "3. .txt", "4. .js", "1"}
                };

                int numberOfQuestions = questions.length;
                int score = 0;

                // Start timer
                int secondsLeft = 20; // 10 seconds timer
                long endTime = System.currentTimeMillis() + secondsLeft * 1000;

                for (int i = 0; i < numberOfQuestions; i++) {
                    // Check timer
                    if (System.currentTimeMillis() > endTime) {
                        System.out.println("Time's up!");
                        break;
                    }

                    System.out.println("Question " + (i + 1) + ": " + questions[i][0]);
                    System.out.println(questions[i][1]);
                    System.out.println(questions[i][2]);
                    System.out.println(questions[i][3]);
                    System.out.println(questions[i][4]);
                    System.out.println();

                    System.out.print("Enter your answer (1-4): ");
                    int answer = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (answer == Integer.parseInt(questions[i][5])) {
                        System.out.println("Correct answer!");
                        System.out.println();
                        score++;
                    } else {
                        System.out.println("Wrong answer!");
                        System.out.println();
                    }
                }

                System.out.println("You scored " + score + " out of " + numberOfQuestions);
                System.out.println();

            } else if (choice == 5) { // Exit
                if (isLoggedIn) {
                    System.out.println("Logging out...");
                    loggedInUser = null;
                    isLoggedIn = false;
                }
                System.out.println("Exiting...");
                scanner.close();
                return;

            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
