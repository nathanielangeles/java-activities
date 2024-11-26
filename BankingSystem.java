/* STUDENT INFORMATION
 Name: Nathaniel Elle G. Angeles
 Section: BIT112-OBc
 Date Submited: 
*/

/* REFERENCES/GUIDES
HashMap: https://youtu.be/0dR-YAFFg6I?si=BEWk0tki6rbUW3rC & https://www.w3schools.com/java/java_hashmap.asp
NumberExceptionError: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/NumberFormatException.html
Java Exception/Error handling using try-catch: https://www.w3schools.com/java/java_try_catch.asp
Clear Terminal Screen: https://rootstack.com/en/blog/java-clear-screen
Thread.sleep() to sleep the system: https://www.geeksforgeeks.org/thread-sleep-method-in-java-with-examples/
CTRL+C Interruption handling: https://docs.oracle.com/javase/6/docs/api/java/lang/Runtime.html#addShutdownHook(java.lang.Thread) -- WIP
ChatGPT...The man. The myth. The legend.
*/

/* TESTED ON
Linux (Debian 12)
*/

/* SEQUENCE OF METHODS. EACH ARE SEPARATED BY A COMMENT.
clearConsole()
sleepConsole()
userRegister()
userLogin()
postLogin()
mainMenu()
Main Method(Entrance)
*/

/* TODOS
Test on Windows 10 and Windows 11. (Soon)
Remove redundant comments. (WIP) ] From 500+ lines of code
Fix comments. (WIP)              ] to 300+ lines of code
Update code. (WIP)
Sanitize user input. (Done)
*/

/* CHANGES & MODIFICATIONS 
1. userRegister() -- Checks first if the username is available or not.
2. userLogin() -- If the username doesn't exists, it will notify the user about it and proceed back to main menu because what's the purpose of providing a pin
                  for a username that doesn't exists?
*/

/* Q&A
Why clear terminal? -- To prevent messy terminal and to prevent users from getting distracted/confused.
Why sleep terminal? -- It allows users to slow down and carefully provide input.
*/

import java.io.IOException; // Auto-generated by VSCode for catch block.
import java.lang.Runtime; // For executing specific system commands.
import java.lang.Thread; // Sleep.
import java.util.Scanner; // For user inputs.
import java.util.HashMap; // Instead of using multiple arrays or variables, I am going to utilize HashMap which is kinda similiar to Python's dictionary (key -> value)(username -> pin)

public class BankingSystem {

//---------------------------------------------------------------------------------------------------------------------------------------------------------
    // Clears the terminal screen based on the OS. (Windows or Mac/Linux).
    static void clearConsole() {
        String os = System.getProperty("os.name");

        // For Windows systems.
        if (os.contains("Windows")) {
            try {
                Runtime.getRuntime().exec("cls");
            } catch (IOException e) {
                e.printStackTrace();
            }
        // For Mac/Linux systems.
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    // Pauses the program for 3 seconds, used after messages to give users time to read.
    static void sleepConsole() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------------
    /* Stores user information with the username as the key, and an Object array as the value, which contains:
    username (String)
    pin (Integer)
    account type (String: either "Savings" or "Current")
    balance (Integer)
    */
    static HashMap<String, Object[]> userDatabase = new HashMap<>();

//---------------------------------------------------------------------------------------------------------------------------------------------------------
    /* USER REGISTRATION
    Collect user information: username, pin, account type, and initial deposit.
    Validation checks for pin and initial deposit.
    */
    static void userRegister() {
        Scanner input = new Scanner(System.in);

        clearConsole(); // Clear terminal for a cleaner look.
        
        System.out.println("********************** REGISTRATION ********************\n");

        // Ask the user for a username
        System.out.print("[+] Enter a username: ");
        String userName = input.nextLine().trim();

        if (userDatabase.containsKey(userName)) {
            System.out.println("Username already exists. Try a different one.\n");

            sleepConsole(); // Sleep terminal for 3 seconds.
            userRegister(); // Register again.
        }

        /* Ask the user for a 4-digit pin code. While Loop and If Statement are used to validate the pin code.
         * While loop is used to repeat the process of asking the user for a 4-digit pin code. 
         * If Statement is used to check if the given pin code (temporary String) length is equal to 4 (4 digits).
         * Initialize a pin variable without a value.
        */
        int pin;
        while (true) {
            System.out.print("[+] Enter a 4-digit pin code: ");
            String pinString = input.nextLine().trim();

            if (pinString.length() == 4) {
                try {
                    pin = Integer.parseInt(pinString);
                    break; // Exit this loop if a valid pin is entered.
                } catch (NumberFormatException e) { // Indicates that the application has attempted to convert a string to one of the numeric types, but the string does not have the appropriate format.
                    System.out.println("Please enter only digits!\n");
                }
            } else {
                System.out.println("Please enter exactly a 4-digit pin code!\n");
            }
        }

        System.out.println("\nChoose account type:");
        System.out.println("1. Savings\n2. Current");
        System.out.print("[+] Enter your choice: ");
        int accChoice = input.nextInt();
        String accType = (accChoice == 1) ? "Savings" : "Current"; // Shortened if-else statement. variable = (condition) ? expressionTrue :  expressionFalse;

        int initDeposit = 0;
        if (accType.equals("Savings")) {
            while (true) {
                System.out.print("\n[+] Enter your initial deposit: ");
                initDeposit = input.nextInt();
                if (initDeposit < 1000) {
                    System.out.println("\nInvalid deposit. Minimum balance for a Savings account is 1000.");
                } else {
                    userDatabase.put(userName, new Object[]{userName, pin, accType, initDeposit});
                    System.out.println("\nRegistration successful!");
                    sleepConsole(); // Sleep terminal for 3 seconds.
                    mainMenu(); // Proceed to main menu.
                    break; // remove -----
                }
            }
        } else if (accType.equals("Current")) {
            while (true) {
                System.out.print("\n[+] Enter your initial deposit: ");
                initDeposit = input.nextInt();
                if (initDeposit < 5000) {
                    System.out.println("\nInvalid deposit. Minimum balance for a Current account is 5000.");
                    sleepConsole(); // Sleep terminal for 3 seconds.
                } else {
                    userDatabase.put(userName, new Object[]{userName, pin, accType, initDeposit});
                    System.out.println("\nRegistration successful!");
                    sleepConsole(); // Sleep terminal for 3 seconds.
                    mainMenu(); // Proceed to main menu.
                    break; // remove -----
                }
            }
        }
    }
        
//---------------------------------------------------------------------------------------------------------------------------------------------------------
    /* USER LOGIN
    Get the username and validate PIN.
    If successful, proceed to post-login menu.
    If not, lock the account after 3 failed attemps.
    */
    static void userLogin() {
        Scanner input = new Scanner(System.in);
        int loginAttempts = 3; // Login attempts.

        clearConsole(); // Clear terminal for a cleaner look.
        
        System.out.println("*********************** LOGIN **************************");

        System.out.print("\n[+] Enter your username: ");
        String userName = input.nextLine().trim();

        if (!userDatabase.containsKey(userName)) {
            System.out.print("Username does not exist.");

            sleepConsole(); // Sleep terminal for 3 seconds.
            userLogin(); // Back to user login.
        }

        while (loginAttempts > 0) {
            System.out.print("[+] Enter your PIN: ");
            int pin = input.nextInt();

            Object[] userInfo = userDatabase.get(userName); // Retrieve user data from HashMap and check PIN.
            int storedPin = (int) userInfo[1]; // Retrieve PIN as integer.

            if (storedPin == pin) {
                System.out.println("Login successful!");
                clearConsole(); // Clear terminal for a cleaner look.
                postLogin(userName, (String) userInfo[2], (int) userInfo[3]); // Proceed to post login menu.
                return;
            } else {
                loginAttempts--;
                System.out.println("Incorrect PIN.\n");
            }
        }
        System.out.println("Account locked due to multiple failed login attempts.");
        sleepConsole(); // Sleep terminal for 3 seconds.
        mainMenu(); // Back to main menu.
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------------
    /* POST LOGIN MENU
    Presents options to the logged-in user: check balance, deposit, withdraw, calculate interest, or logout.
    Depending on the user's choice, it calls the appropriate method.
    */
    static void postLogin(String userName, String accType, int userBalance) {
        Scanner input = new Scanner(System.in);
        boolean validChoice = false;
        
        while (!validChoice) {
            clearConsole(); // Clear terminal for a cleaner look.
            System.out.println("****************** POST LOGIN MENU *********************");
            
            System.out.println("\nWelcome, " + userName); // Passed parameter from successful login in userLogin method.
            
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Calculate Interest (Savings Only)");
            System.out.println("5. Logout");

            System.out.print("[+] Enter your choice: ");
            int userChoice = input.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.println("\nYour current balance is " + userBalance + ".");
                    sleepConsole(); // Sleep terminal for 3 seconds.
                    validChoice = true;
                    postLogin(userName, accType, userBalance);
                    break;
                case 2:
                    depositMoney(userName, accType, userBalance);
                    validChoice = true;
                    break;
                case 3:
                    withdrawMoney(userName, accType, userBalance);
                    validChoice = true;
                    break;
                case 4:
                    if (accType.equals("Savings")) {
                        calculateInterest(userName, accType, userBalance);
                    } else {
                        System.out.println("\nThis feature is not available for Current accounts.");
                        sleepConsole(); // Sleep terminal for 3 seconds.
                        postLogin(userName, accType, userBalance);
                    }
                    validChoice = true;
                    break;
                case 5:
                    System.out.println("\nLogging out...");
                    sleepConsole(); // Sleep terminal for 3 seconds.
                    mainMenu(); // Proceed to main menu.
                    validChoice = true;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a valid option.");
                    sleepConsole(); // Sleep terminal for 3 seconds.
                    break;
                }
            }
        }

    /* DEPOSIT MONEY
    User enters deposit amount.
    The amount is added to their balance if it's positive.
    */
    static void depositMoney(String userName, String accType, int userBalance) {
        Scanner input = new Scanner(System.in);
        System.out.print("\n[+] Enter the amount to despoit: ");
        int depositAmount = input.nextInt();

        if (depositAmount <= 0) {
            System.out.println("\nDeposit amount must be positive.");
            sleepConsole(); // Sleep terminal for 3 seconds.
        } else {
            userBalance += depositAmount;
            Object[] userInfo = userDatabase.get(userName);
            userInfo[3] = userBalance; // Update balance in the user data.
            System.out.println("\nDeposit successful! Your new balance is: " + userBalance + ".");
            sleepConsole(); // Sleep terminal for 3 seconds.
        }
        postLogin(userName, accType, userBalance); // Proceed back to post login menu.
    }

    /* WITHDRAW MONEY
    User enters withdrawal amount.
    Depending on the account type, it checks for withdrawal limits.
    */
    static void withdrawMoney(String userName, String accType, int userBalance) {
        Scanner input = new Scanner(System.in);
        System.out.print("\n[+] Enter the amount to withdraw: ");
        int withdrawAmount = input.nextInt();

        if (withdrawAmount <= 0) {
            System.out.println("\nWithdrawal amount must be positive.");
            sleepConsole(); // Sleep terminal for 3 seconds.
        } else {
            if (accType.equals("Savings")) {
                if (withdrawAmount > 20000) {
                    System.out.println("\nSavings accounts cannot exceed the daily limit or fall below the minimum balance.");
                    sleepConsole(); // Sleep terminal for 3 seconds.
                } else if (userBalance - withdrawAmount < 1000) {
                    System.out.println("\nCannot withdraw below the minimum balance of 1000 for a Savings account.");
                    sleepConsole(); // Sleep terminal for 3 seconds.
                } else {
                    userBalance -= withdrawAmount;
                    Object[] userInfo = userDatabase.get(userName);
                    userInfo[3] = userBalance; // Update balance
                    System.out.println("\nWithdrawal successful! Your new balance is " + userBalance + ".");
                    sleepConsole(); // Sleep terminal for 3 seconds.
                }
            } else if (accType.equals("Current")) {
                if (userBalance - withdrawAmount < 5000) {
                    System.out.println("\nCannot withdraw below the minimum balance of 5000 for a Current account.");
                    sleepConsole(); // Sleep terminal for 3 seconds.
                } else {
                    userBalance -= withdrawAmount;
                    Object[] userInfo = userDatabase.get(userName);
                    userInfo[3] = userBalance; // Update balance
                    System.out.println("\nWithdrawal succesful! Your new balance is " + userBalance + ".");
                    sleepConsole(); // Sleep terminal for 3 seconds.
                }
            }
        }
        postLogin(userName, accType, userBalance); // Proceed back to post login menu.
    }

    /* CALCULATE INTEREST
    Show interest earned.
    */
    static void calculateInterest(String userName, String accType, int userBalance) {
        double interestRate = 0.03; // 3% annual interest rate for Savings accounts.
        double interest = userBalance * interestRate; // Formula.
        System.out.println("\nInterest earned: " + interest);
        sleepConsole(); // Sleep terminal for 3 seconds.

        postLogin(userName, accType, userBalance); // Proceed back to post login menu.
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------------
    /* MAIN MENU
    Show main menu with options: Register, Login, or Exit.
    Based on the user's choice, it calls the appropriate method.
    */
    static void mainMenu() {
        Scanner input = new Scanner(System.in);

        clearConsole(); // Clear terminal for a cleaner look
    
        System.out.println("************************ MENU **************************");

        System.out.println("\nWelcome to the Multi-Tier Bank Account Management System");
        System.out.println("1. Register\n2. Login\n3. Exit");
        
        System.out.print("[+] Enter your choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                userRegister();
                break;
            
                case 2:
                userLogin();
                break;

            case 3:
                System.out.println("\nExiting now.");
                sleepConsole(); // Sleep terminal for 3 seconds.
                System.exit(0); // Exit the program.

            default:
                System.out.println("\nInvalid choice. Exiting by default.");
                sleepConsole(); // Sleep terminal for 3 seconds.
                System.exit(0); // Exit the program.
        }
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------------
    // Calls mainMenu() to start the program.
    public static void main(String[] args) {
        mainMenu(); // Proceed to main menu.
    }
}
