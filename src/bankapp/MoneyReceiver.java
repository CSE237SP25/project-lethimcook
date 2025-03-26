package bankapp;

public class MoneyReceiver {
    private Bank bank;
    
    public MoneyReceiver(Bank bank) {
        if (bank == null) {
            throw new IllegalArgumentException("Bank cannot be null");
        }
        this.bank = bank;
    }
    
    public void receiveMoney(BankAccount recipient, String senderUsername, double amount) {
        // Validate input parameters
        if (recipient == null) {
            throw new IllegalArgumentException("Recipient account cannot be null");
        }
        
        if (senderUsername == null || senderUsername.trim().isEmpty()) {
            throw new IllegalArgumentException("Sender username cannot be empty");
        }
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        
        // Check if sender account exists
        BankAccount senderAccount = bank.getAccount(senderUsername);
        if (senderAccount == null) {
            throw new IllegalArgumentException("Sender account not found");
        }
        
        try {
            // Perform the transfer from sender to recipient
            senderAccount.transfer(recipient, amount);
            
            System.out.println("Money received successfully from " + senderUsername + 
                               ": $" + String.format("%.2f", amount));
        } catch (IllegalStateException e) {
            // This will catch insufficient funds error
            throw new IllegalStateException("Sender has insufficient funds for transfer");
        }
    }
}