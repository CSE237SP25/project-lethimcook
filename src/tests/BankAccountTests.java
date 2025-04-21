package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import bankapp.BankAccount;
import bankapp.Transaction;

public class BankAccountTests {
	@Test
	public void testSimpleDeposit() {
		BankAccount account = new BankAccount(null, null, null);
		account.deposit(25);
		assertEquals(account.getBalance(), 25.0, 0.005);
	}

	@Test
	public void testNegativeDeposit() {
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

	@Test
	public void testTransferWithNote() {
		BankAccount sender = new BankAccount("user1", "pass1", "ACC001");
		BankAccount recipient = new BankAccount("user2", "pass2", "ACC002");
		
		sender.deposit(100.0);
		sender.transfer(recipient, 50.0, "Rent payment");
		
		assertEquals(50.0, sender.getBalance(), 0.001);
		assertEquals(50.0, recipient.getBalance(), 0.001);
		
		Transaction lastTransaction = sender.getTransactionHistory().get(sender.getTransactionHistory().size() - 1);
		assertEquals("Rent payment", lastTransaction.getNote());
	}
	
	@Test
	public void testTransferWithEmptyNote() {
		BankAccount sender = new BankAccount("user1", "pass1", "ACC001");
		BankAccount recipient = new BankAccount("user2", "pass2", "ACC002");
		
		sender.deposit(100.0);
		sender.transfer(recipient, 50.0, "");
		
		assertEquals(50.0, sender.getBalance(), 0.001);
		assertEquals(50.0, recipient.getBalance(), 0.001);
		
		Transaction lastTransaction = sender.getTransactionHistory().get(sender.getTransactionHistory().size() - 1);
		assertNull(lastTransaction.getNote());
	}
	
	@Test
	public void testTransferWithNullNote() {
		BankAccount sender = new BankAccount("user1", "pass1", "ACC001");
		BankAccount recipient = new BankAccount("user2", "pass2", "ACC002");
		
		sender.deposit(100.0);
		sender.transfer(recipient, 50.0, null);
		
		assertEquals(50.0, sender.getBalance(), 0.001);
		assertEquals(50.0, recipient.getBalance(), 0.001);
		
		Transaction lastTransaction = sender.getTransactionHistory().get(sender.getTransactionHistory().size() - 1);
		assertNull(lastTransaction.getNote());
	}

}