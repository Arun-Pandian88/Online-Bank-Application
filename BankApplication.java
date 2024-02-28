import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountNo;
    private String password;
    private String name;
    private double balance;
    private Map<LocalDateTime, Map<String, Double>> deposits;
    private Map<LocalDateTime, Map<String, Double>> withdrawals;

    // Constructor
    public BankAccount() {
        deposits = new HashMap<>();
        withdrawals = new HashMap<>();
    }

    // Account_Create method
    public void accountCreate() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Account Creation");

        // Get user input for name
        System.out.println("Name: ");
        name = scan.nextLine();

        // Get user input for account number
        System.out.println("Account Number: ");
        accountNo = scan.nextLine();

        // Get user input for password with a minimum length of 8 characters
        do {
            System.out.println("Password (minimum 8 characters): ");
            password = scan.nextLine();

            if (password.length() < 8) {
                System.out.println("Invalid Password! Minimum 8 characters required.");
            } else {
                System.out.println("Password is correct");
            }

        } while (password.length() < 8);

        System.out.println("Account created successfully!");
    }

    // deposit method
    private void deposit(double amount) {
        balance += amount;
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Check if deposits map contains the current date-time entry
        Map<String, Double> depositMap = deposits.get(currentDateTime);

        // If the entry does not exist, create a new map and put it into deposits
        if (depositMap == null) {
            depositMap = new HashMap<>();
            deposits.put(currentDateTime, depositMap);
        }

        // Add the transaction to the inner map
        depositMap.put("Transaction " + (depositMap.size() + 1), amount);

        System.out.println("Amount deposited successfully!");
    }


    // withdraw method

        // Withdraw method
        private void withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
                LocalDateTime currentDateTime = LocalDateTime.now();

                // Check if withdrawals map contains the current date-time entry
                Map<String, Double> withdrawalMap = withdrawals.get(currentDateTime);

                // If the entry does not exist, create a new map and put it into withdrawals
                if (withdrawalMap == null) {
                    withdrawalMap = new HashMap<>();
                    withdrawals.put(currentDateTime, withdrawalMap);
                }

                // Add the transaction to the inner map
                withdrawalMap.put("Transaction " + (withdrawalMap.size() + 1), amount);

                System.out.println("Amount withdrawn successfully!");
            } else {
                System.out.println("Insufficient funds for withdrawal.");
            }
        }


    // getBalance method
    private double getBalance() {
        return balance;
    }

    // displayMenu method
    private void displayMenu() {
        Scanner in = new Scanner(System.in);
        char checkAgain;

        do {
            System.out.println("Choose your option:");
            System.out.println("A: Deposit");
            System.out.println("B: Withdraw");
            System.out.println("C: Check Balance");
            System.out.println("D: Account Details");
            System.out.println("E: Logout");

            char choice = in.next().charAt(0);

            switch (choice) {
                case 'A':
                    System.out.println("Deposit");
                    System.out.println("Enter the deposit amount:");
                    double depositAmount = in.nextDouble();
                    deposit(depositAmount);
                    break;
                case 'B':
                    System.out.println("Withdraw");
                    System.out.println("Enter the withdrawal amount:");
                    double withdrawAmount = in.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 'C':
                    System.out.println("Balance: " + getBalance());
                    break;
                case 'D':
                    displayAccountDetails();
                    break;
                case 'E':
                    System.out.println("Logging out. Thank you!");
                    return;  // Exit the loop and the method
                default:
                    System.out.println("Invalid option!");
            }

            System.out.println("Do you want to perform another transaction? (Y/N): ");
            checkAgain = in.next().charAt(0);
        } while (checkAgain == 'Y' || checkAgain == 'y');

        System.out.println("Thank you!");
    }

    public void displayAccountDetails() {
        System.out.println("Account Details");
        System.out.println("======================================");
        System.out.println("Name: " + name);
        System.out.println("Account No: " + accountNo);
        System.out.println("Balance: " + balance);
        System.out.println("Deposit Transactions:" +deposits);

        System.out.println("Withdrawal Transactions: " + withdrawals);

    }



    // login method
    public void login() {
        System.out.println("LOGIN");
        System.out.println("---------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Account Number: ");
        String accountNoInput = scanner.nextLine();
        System.out.println("Password: ");
        String passwordInput = scanner.nextLine();

        if (accountNo.equals(accountNoInput) && password.equals(passwordInput)) {
            System.out.println("Login successful!");
            displayMenu();
        } else {
            System.out.println("Invalid Account Number or Password. Please check and try again.");
        }
    }


}

public class BankApplication {
    public static void main(String[] args) {
        BankAccount stateBank = new BankAccount();
        stateBank.accountCreate();
        System.out.println();
        stateBank.login();

    }
}
