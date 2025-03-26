package bankapp;

import java.util.Scanner;

public class Menu {
	private BankAccount theAccount;

	public Menu() {
		theAccount = new BankAccount(null, null, null);
	}

	// display methods don't need to be tested
	public void displayOptions() {
		System.out.println("Please eenter an amount to be deposited.");
	}

	// methods that require user input don't need to be tested
	public double getUserInput() {
		Scanner keyboardInput = new Scanner(System.in);
		double userInput = keyboardInput.nextDouble();
		return userInput;
	}

	//
	public void processUserInput(double amount) {
		theAccount.deposit(amount);
	}

	public BankAccount getAccount() {
		return theAccount;
	}
}
