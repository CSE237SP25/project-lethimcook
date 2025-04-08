package bankapp;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

	private double balance;
	private String username;
	private String password;
	private String accountNumber;
	private List<Transaction> transactionHistory;
	
	public BankAccount(String username, String password, String accountNumber) {
		this.balance = 0;
		this.username = username;
		this.password = password;
		this.accountNumber = accountNumber;
		this.transactionHistory = new ArrayList<>();
	}
	
	public void deposit(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Deposit amount must be positive");
		}
		this.balance += amount;
		transactionHistory.add(new Transaction("DEPOSIT", amount, accountNumber, null));
	}
	
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be positive");
		}
		if (amount > balance) {
			throw new IllegalStateException("Insufficient funds");
		}
		this.balance -= amount;
		transactionHistory.add(new Transaction("WITHDRAW", amount, accountNumber, null));
	}
	
	public void transfer(BankAccount recipient, double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Transfer amount cannot be negative");
		}
		if(amount > balance) {
			throw new IllegalStateException("Insufficient funds for transfer");
		}
		this.withdraw(amount);
		recipient.deposit(amount);
		transactionHistory.add(new Transaction("TRANSFER", amount, accountNumber, recipient.getAccountNumber()));
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
		return new ArrayList<>(transactionHistory);  // Return a copy to prevent external modification
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
}
