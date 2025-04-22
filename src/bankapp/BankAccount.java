package bankapp;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

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
        
        // Calculate interest: Principal * Rate * Time
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

    // Account Statistics Methods
    public double getAverageTransactionAmount() {
        if (transactionHistory.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (Transaction t : transactionHistory) {
            total += t.getAmount();
        }
        return total / transactionHistory.size();
    }

    public Transaction getLargestTransaction() {
        if (transactionHistory.isEmpty()) {
            return null;
        }
        Transaction largest = transactionHistory.get(0);
        for (Transaction t : transactionHistory) {
            if (t.getAmount() > largest.getAmount()) {
                largest = t;
            }
        }
        return largest;
    }

    public String getMostFrequentTransactionType() {
        if (transactionHistory.isEmpty()) {
            return "No transactions";
        }
        
        Map<String, Integer> typeCounts = new HashMap<>();
        for (Transaction t : transactionHistory) {
            String type = t.getType();
            typeCounts.put(type, typeCounts.getOrDefault(type, 0) + 1);
        }
        
        String mostFrequent = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : typeCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }

    public String getMostActiveDay() {
        if (transactionHistory.isEmpty()) {
            return "No transactions";
        }
        
        Map<String, Integer> dayCounts = new HashMap<>();
        for (Transaction t : transactionHistory) {
            String dayOfWeek = t.getTimestamp().getDayOfWeek().toString();
            dayCounts.put(dayOfWeek, dayCounts.getOrDefault(dayOfWeek, 0) + 1);
        }
        
        String mostActive = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : dayCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostActive = entry.getKey();
            }
        }
        return mostActive;
    }

    public void displayAccountStatistics() {
        System.out.println("\n=== Account Statistics ===");
        System.out.printf("Average Transaction Amount: $%.2f%n", getAverageTransactionAmount());
        
        Transaction largest = getLargestTransaction();
        if (largest != null) {
            System.out.printf("Largest Transaction: $%.2f (%s)%n", 
                largest.getAmount(), largest.getType());
        }
        
        System.out.println("Most Frequent Transaction Type: " + getMostFrequentTransactionType());
        System.out.println("Most Active Day: " + getMostActiveDay());
        System.out.println("========================");
    }
    
    /**
     * Returns up to the last n transactions, most-recent first.
     * @param n number of recent transactions to return; must be non-negative
     * @throws IllegalArgumentException if n < 0
     */
    public List<Transaction> getRecentTransactions(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number of transactions must be non-negative");
        }
        List<Transaction> history = getTransactionHistory();  // chronological order
        int size = history.size();
        List<Transaction> recent = new ArrayList<>();
        for (int i = size - 1; i >= 0 && recent.size() < n; i--) {
            recent.add(history.get(i));
        }
        return recent;
    }
}
