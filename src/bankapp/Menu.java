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
        System.out.println("4. View Transaction History");
        System.out.println("5. Change Password");
        System.out.println("6. Change Username");
        System.out.println("7. Change Nickname");
        System.out.println("8. Apply Interest");
        System.out.println("9. View Interest Rate");
        System.out.println("10. Set Savings Goal");       
        System.out.println("11. View Savings Goal Progress"); 
        System.out.println("12. Freeze Account");
        System.out.println("13. Unfreeze Account");
        System.out.println("14. View Account Statistics");
        System.out.println("15. Set Monthly Spending Limit");
        System.out.println("16. View Monthly Spending");
        System.out.println("17. Logout");
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
        keyboardInput.nextLine(); // Consume newline after number
        System.out.print("Add an optional note (press Enter to skip): ");
        String note = keyboardInput.nextLine();
        
        BankAccount recipient = theBank.getAccount(recipientUsername);
        if (recipient == null) {
            System.out.println("Recipient account not found!");
            return;
        }
        
        try {
            currentAccount.transfer(recipient, amount, note);
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
    
    public void displayTransactionHistory() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        currentAccount.displayTransactionHistory();
    }
    
    public void changePassword() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        System.out.print("Enter current password: ");
        String currentPwd = keyboardInput.next();
        if (!currentAccount.validatePassword(currentPwd)) {
            System.out.println("Incorrect password.");
            return;
        }
        System.out.print("Enter new password: ");
        String newPwd = keyboardInput.next();
        currentAccount.setPassword(newPwd);
        System.out.println("Password changed successfully!");
    }
    
    public void changeUsername() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        System.out.print("Enter current password: ");
        String currentPwd = keyboardInput.next();
        if (!currentAccount.validatePassword(currentPwd)) {
            System.out.println("Incorrect password.");
            return;
        }
        System.out.print("Enter new username: ");
        String newUsername = keyboardInput.next();
        BankAccount existing = theBank.getAccount(newUsername);
        if (existing != null) {
            System.out.println("Username already taken.");
            return;
        }
        String oldUsername = currentAccount.getUsername();
        currentAccount.setUsername(newUsername);
        theBank.updateUsername(oldUsername, currentAccount);
        System.out.println("Username changed successfully!");
    }
    
    public void changeNickname() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        System.out.print("Enter new nickname: ");
        String newNickname = keyboardInput.next();
        try {
            currentAccount.setNickname(newNickname);
            System.out.println("Nickname changed successfully! New nickname: " + currentAccount.getNickname());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void applyInterest() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        double interest = currentAccount.calculateInterest();
        if (interest <= 0) {
            System.out.println("No interest to apply at this time.");
            return;
        }
        double appliedInterest = currentAccount.applyInterest();
        System.out.printf("Interest applied: $%.2f%n", appliedInterest);
        System.out.printf("New balance: $%.2f%n", currentAccount.getBalance());
    }
    
    public void viewInterestRate() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        System.out.printf("Current interest rate: %.2f%% per year%n", currentAccount.getInterestRate());
    }
    
    public void setSavingsGoal() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        System.out.print("Enter new savings goal amount: ");
        double goal = keyboardInput.nextDouble();
        try {
            currentAccount.setSavingsGoal(goal);
            System.out.println("Savings goal set to: $" + String.format("%.2f", currentAccount.getSavingsGoal()));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void viewSavingsGoalProgress() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        double progress = currentAccount.getSavingsGoalProgressPercentage();
        double remaining = currentAccount.getRemainingAmountToSavingsGoal();
        System.out.printf("Savings Goal: $%.2f%n", currentAccount.getSavingsGoal());
        System.out.printf("Current Balance: $%.2f%n", currentAccount.getBalance());
        System.out.printf("Progress: %.2f%% complete%n", progress);
        System.out.printf("Remaining amount to reach goal: $%.2f%n", remaining);
    }
    
    public void logout() {
        currentAccount = null;
        System.out.println("Logged out successfully!");
    }
    
    public BankAccount getCurrentAccount() {
        return currentAccount;
    }
    
    public void freezeAccount() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        currentAccount.freezeAccount();
        System.out.println("Account has been frozen.");
    }

    public void unfreezeAccount() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        currentAccount.unfreezeAccount();
        System.out.println("Account has been unfrozen.");
    }
    public void setSpendingLimit() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        System.out.print("Enter new monthly spending limit: ");
        double limit = keyboardInput.nextDouble();
        currentAccount.setMonthlySpendingLimit(limit);
        System.out.println("Spending limit set to $" + String.format("%.2f", limit));
    }

    public void viewMonthlySpending() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        double spent = currentAccount.getMonthlySpending();
        double limit = currentAccount.getMonthlySpendingLimit();
        System.out.printf("You've spent $%.2f this month.\n", spent);
        if (limit > 0) {
            System.out.printf("Your limit is $%.2f.\n", limit);
            if (currentAccount.isOverSpendingLimit()) {
                System.out.println("⚠️ You are over your spending limit!");
            }
        } else {
            System.out.println("You have not set a spending limit.");
        }
    }

    public void viewAccountStatistics() {
        if (currentAccount == null) {
            System.out.println("Please login first!");
            return;
        }
        currentAccount.displayAccountStatistics();
    }
}
