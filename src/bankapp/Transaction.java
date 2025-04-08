package bankapp;

import java.time.LocalDateTime;

public class Transaction {
    private String type;  // "DEPOSIT", "WITHDRAW", "TRANSFER"
    private double amount;
    private String description;
    private LocalDateTime timestamp;
    private String fromAccount;
    private String toAccount;  // null for deposits and withdrawals
    
    public Transaction(String type, double amount, String fromAccount, String toAccount) {
        this.type = type;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.timestamp = LocalDateTime.now();
        
        // Create description based on transaction type
        switch (type) {
            case "DEPOSIT":
                this.description = String.format("Deposit of $%.2f", amount);
                break;
            case "WITHDRAW":
                this.description = String.format("Withdrawal of $%.2f", amount);
                break;
            case "TRANSFER":
                this.description = String.format("Transfer of $%.2f to account %s", amount, toAccount);
                break;
            default:
                this.description = "Unknown transaction";
        }
    }
    
    public Transaction(String type2, double amount2, String accountNumber, Object toAccount2) {
		// TODO Auto-generated constructor stub
	}

	public String getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getFromAccount() {
        return fromAccount;
    }
    
    public String getToAccount() {
        return toAccount;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s", timestamp, description);
    }
} 