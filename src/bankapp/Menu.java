package bankapp;

import java.util.Scanner;

public class Menu {
    private Bank theBank;
    private BankAccount currentAccount;
    private Scanner keyboardInput;
    
    public Menu() {
        theBank = new Bank();
        keyboardInput = new Scanner(System.in);
        currentAccount = null;
    }
    
    public void displayMainMenu() {
        System.out.println("\n=== Banking Menu ===");
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }
    
    public void displayLoggedInMenu() {
        System.out.println("\n=== Account Menu ===");
        System.out.println("1. Deposit");
        System.out.println("2. Transfer Money");
        System.out.println("3. Check Balance");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
    }
    
    public void createAccount() {
        System.out.print("Enter username: ");
        String username = keyboardInput.next();
        System.out.print("Enter password: ");
        String password = keyboardInput.next();
        System.out.print("Enter account number: ");
        String accountNumber = keyboardInput.next();
        
        try {
            theBank.createAccount(username, password, accountNumber);
            System.out.println("Account created successfully!");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void login() {
        System.out.print("Enter username: ");
        String username = keyboardInput.next();
        System.out.print("Enter password: ");
        String password = keyboardInput.next();
        
        try {
            currentAccount = theBank.login(username, password);
            System.out.println("Login successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void processUserInput(double amount) {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        currentAccount.deposit(amount);
    }
    
    public void transferMoney() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        
        System.out.print("Enter recipient username: ");
        String recipientUsername = keyboardInput.next();
        System.out.print("Enter transfer amount: ");
        double amount = keyboardInput.nextDouble();
        
        BankAccount recipient = theBank.getAccount(recipientUsername);
        if (recipient == null) {
            System.out.println("Recipient account not found!");
            return;
        }
        
        try {
            currentAccount.transfer(recipient, amount);
            System.out.println("Transfer successful!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void checkBalance() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        System.out.println("Current balance: $" + currentAccount.getBalance());
    }
    
    public void logout() {
        currentAccount = null;
        System.out.println("Logged out successfully!");
    }
    
    public BankAccount getCurrentAccount() {
        return currentAccount;
    }
}