package bankapp;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class BankAccount {

    private double balance;
    private String username;
    private String password;
    private String accountNumber;
    private List<Transaction> transactionHistory;
    private double interestRate;
    private LocalDateTime lastInterestApplied;
    private String nickname;
    private double savingsGoal;
    private boolean isFrozen = false;
    private double monthlySpendingLimit = 0;
    
    public BankAccount(String username, String password, String accountNumber) {
        this.balance = 0;
        this.username = username;
        this.password = password;
        this.accountNumber = accountNumber;
        this.transactionHistory = new ArrayList<>();
        this.interestRate = 0.5;
        this.lastInterestApplied = LocalDateTime.now();
        this.nickname = username;
        this.savingsGoal = 0;
    }
    
    public BankAccount(String username, String password, String accountNumber, double interestRate) {
        this(username, password, accountNumber);
        this.interestRate = interestRate;
    }
    
    public void deposit(double amount) {
        deposit(amount, null);
    }

    public void deposit(double amount, String note) {
    	if (isFrozen) {
    	        throw new IllegalStateException("Account is frozen. Cannot deposit.");
    	}
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance += amount;
        transactionHistory.add(new Transaction("DEPOSIT", amount, accountNumber, null, note));
    }
    
    public void withdraw(double amount) {
        withdraw(amount, null);
    }

    public void withdraw(double amount, String note) {
    	if (isFrozen) {
            throw new IllegalStateException("Account is frozen. Cannot withdraw.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        this.balance -= amount;
        transactionHistory.add(new Transaction("WITHDRAW", amount, accountNumber, null, note));
    }
    
    public void transfer(BankAccount recipient, double amount) {
        transfer(recipient, amount, null);
    }

    public void transfer(BankAccount recipient, double amount, String note) {
        if (recipient == null) {
            throw new IllegalArgumentException("Recipient account cannot be null");
        }
        if(amount < 0) {
            throw new IllegalArgumentException("Transfer amount cannot be negative");
        }
        if(amount > balance) {
            throw new IllegalStateException("Insufficient funds for transfer");
        }
        this.withdraw(amount);
        recipient.deposit(amount);
        transactionHistory.add(new Transaction("TRANSFER", amount, accountNumber, recipient.getAccountNumber(), note));
    }
    
    public double getBalance() {
        return this.balance;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
    
    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory); 
    }
    
    public void displayTransactionHistory() {
        System.out.println("\nTransaction History for Account " + accountNumber + ":");
        System.out.println("----------------------------------------");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("----------------------------------------");
    }
    
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
    
    public double getInterestRate() {
        return interestRate;
    }
    
    public void setInterestRate(double newRate) {
        if (newRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        this.interestRate = newRate;
    }
    
    public LocalDateTime getLastInterestApplied() {
        return lastInterestApplied;
    }
    
    public double calculateInterest() {
        LocalDateTime now = LocalDateTime.now();
        long days = java.time.Duration.between(lastInterestApplied, now).toDays();
        
        if (days <= 0) {
            return 0.0;
        }
        
        double dailyRate = interestRate / 100.0 / 365.0;
        double interest = balance * dailyRate * days;
        
        return interest;
    }
    
    public double applyInterest() {
        double interest = calculateInterest();
        if (interest > 0) {
            this.balance += interest;
            transactionHistory.add(new Transaction("INTEREST", interest, accountNumber, null));
            lastInterestApplied = LocalDateTime.now();
        }
        return interest;
    }
    
    public String getNickname() {
        return this.nickname;
    }
    
    public void setNickname(String newNickname) {
        if (newNickname == null || newNickname.trim().isEmpty()) {
            throw new IllegalArgumentException("Nickname cannot be empty");
        }
        this.nickname = newNickname;
    }
    
    
    public void setSavingsGoal(double goal) {
        if (goal < 0) {
            throw new IllegalArgumentException("Savings goal cannot be negative");
        }
        this.savingsGoal = goal;
    }
    
    public double getSavingsGoal() {
        return this.savingsGoal;
    }
    
    public double getSavingsGoalProgressPercentage() {
        if (savingsGoal <= 0) {
            return 0;
        }
        return (balance / savingsGoal) * 100;
    }
    
    public double getRemainingAmountToSavingsGoal() {
        double remaining = savingsGoal - balance;
        return remaining > 0 ? remaining : 0;
    }
    
    public void freezeAccount() {
        isFrozen = true;
    }

    public void unfreezeAccount() {
        isFrozen = false;
    }

    public boolean isFrozen() {
        return isFrozen;
    }
    public void setMonthlySpendingLimit(double limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Limit must be non-negative");
        }
        this.monthlySpendingLimit = limit;
    }

    public double getMonthlySpendingLimit() {
        return monthlySpendingLimit;
    }
    public double getMonthlySpending() {
        double total = 0;
        LocalDateTime now = LocalDateTime.now();
        for (Transaction t : transactionHistory) {
            if ((t.getType().equals("WITHDRAW") || t.getType().equals("TRANSFER"))
                    && t.getTimestamp().getMonth() == now.getMonth()
                    && t.getTimestamp().getYear() == now.getYear()) {
                total += t.getAmount();
            }
        }
        return total;
    }
    public boolean isOverSpendingLimit() {
        return monthlySpendingLimit > 0 && getMonthlySpending() > monthlySpendingLimit;
    }
}
