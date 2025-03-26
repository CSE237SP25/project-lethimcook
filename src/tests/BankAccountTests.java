package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import bankapp.BankAccount;

public class BankAccountTests {
	@Test
	public void testSimpleDeposit() {
		// 1. Create objects to be tested
		BankAccount account = new BankAccount(null, null, null);

		// 2. Call the method being tested
		account.deposit(25);

		// 3. Use assertions to verify results
		assertEquals(account.getBalance(), 25.0, 0.005);
	}

	@Test
	public void testNegativeDeposit() {
		// 1. Create object to be tested
		BankAccount account = new BankAccount(null, null, null);

		try {
			account.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

	@Test
	public void testInitialBalance() {
		BankAccount account = new BankAccount(null, null, null);
		assertEquals(0.0, account.getBalance(), 0.001);
	}

	@Test
	public void testWithdraw() {
		BankAccount account = new BankAccount(null, null, null);
		account.deposit(100.0);
		account.withdraw(50.0);
		assertEquals(50.0, account.getBalance(), 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeWithdraw() {
		BankAccount account = new BankAccount(null, null, null);
		account.withdraw(-50.0);
	}

	@Test(expected = IllegalStateException.class)
	public void testInsufficientFunds() {
		BankAccount account = new BankAccount(null, null, null);
		account.withdraw(100.0);
	}

}
