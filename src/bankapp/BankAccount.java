package bankapp;

public class BankAccount {
	private double balance;
	private String username;
	private String accountNumber;
	private String password;

	public BankAccount(String username, String password, String accountNumber) {
		this.balance = 0;
	    this.username = username;
	    this.password = password;
	    this.accountNumber = accountNumber;
	}

	public void deposit(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException();
		}
		this.balance += amount;
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be positive");
		}
		if (amount > balance) {
			throw new IllegalStateException("Insufficient funds");
		}
		this.balance -= amount;
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

}
