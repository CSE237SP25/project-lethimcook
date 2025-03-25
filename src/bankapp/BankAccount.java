package bankapp;

public class BankAccount {
	private double balance;

	public BankAccount() {
		this.balance = 0.0;
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
}
