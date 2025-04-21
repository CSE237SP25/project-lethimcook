package bankapp;

import java.time.LocalDateTime;

public class Transaction {
    private String type;
    private double amount;
    private String description;
    private LocalDateTime timestamp;
    private String fromAccount;
    private String toAccount;  //null for deposits, withdrawals, and interest
    private String note;
    
    public Transaction(String type, double amount, String fromAccount, String toAccount, String note) {
        this.type = type;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.timestamp = LocalDateTime.now();
        this.note = (note == null || note.trim().isEmpty()) ? null : note.trim(); //store null if note is empty/whitespace

        switch (type) {
            case "DEPOSIT":
                this.description = String.format("Deposit of $%.2f", amount);
                break;
            case "WITHDRAW":
                this.description = String.format("Withdrawal of $%.2f", amount);
                break;
            case "TRANSFER":
                //make sure toAccount is not null for transfers before formatting
                String recipientInfo = (toAccount != null) ? " to account " + toAccount : "";
                this.description = String.format("Transfer of $%.2f%s", amount, recipientInfo);
                break;
            case "INTEREST":
                this.description = String.format("Interest applied: $%.2f", amount);
                break;
            default:
                this.description = "Unknown transaction";
        }
    }
    
    //overloaded constructor for transactions without a note (like interest)
    public Transaction(String type, double amount, String fromAccount, String toAccount) {
        this(type, amount, fromAccount, toAccount, null);
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
    
    public String getNote() {
        return note;
    }
    
    @Override
    public String toString() {
        String noteString = (note != null) ? " | Note: " + note : "";
        return String.format("[%s] %s%s", timestamp.toLocalDate(), description, noteString);
    }
} 