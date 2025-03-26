package bankapp;

public class BillPayment {
    private Bank bank;
    
    public BillPayment(Bank bank) {
        if (bank == null) {
            throw new IllegalArgumentException("Bank cannot be null");
        }
        this.bank = bank;
    }
    
    public void payBill(BankAccount account, String billProvider, double amount) {
        // Validate bill payment parameters
        if (account == null) {
            throw new IllegalArgumentException("Bank account cannot be null");
        }
        
        if (billProvider == null || billProvider.trim().isEmpty()) {
            throw new IllegalArgumentException("Bill provider cannot be empty");
        }
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Bill payment amount must be positive");
        }
        
        try {
            // Attempt to withdraw the amount from the account
            account.withdraw(amount);
            
            // Optional: You could log the bill payment details here
            System.out.println("Bill paid successfully: " + 
                               billProvider + " - $" + String.format("%.2f", amount));
        } catch (IllegalStateException e) {
            // This will catch insufficient funds error from BankAccount
            throw new IllegalStateException("Insufficient funds to pay bill");
        }
    }
}