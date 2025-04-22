package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bankapp.BankAccount;
import bankapp.Transaction;
import java.util.List;

public class RecentTransactionsTests {
    @Test
    public void testZeroRecentTransactions() {
        BankAccount acct = new BankAccount("u","p","ACC");
        acct.deposit(100);
        List<Transaction> recent = acct.getRecentTransactions(0);
        assertTrue(recent.isEmpty());
    }

    @Test
    public void testMoreThanExist() {
        BankAccount acct = new BankAccount("u","p","ACC");
        acct.deposit(100);
        acct.withdraw(50);
        List<Transaction> recent = acct.getRecentTransactions(5);
        // Expect both transactions, in reverse order
        assertEquals(2, recent.size());
        assertEquals("WITHDRAW", recent.get(0).getType());
        assertEquals("DEPOSIT", recent.get(1).getType());
    }

    @Test
    public void testSubsetOfTransactions() {
        BankAccount acct = new BankAccount("u","p","ACC");
        acct.deposit(10);
        acct.deposit(20);
        acct.deposit(30);
        List<Transaction> recent = acct.getRecentTransactions(2);
        assertEquals(2, recent.size());
        assertEquals(30.0, recent.get(0).getAmount(), 0.001);
        assertEquals(20.0, recent.get(1).getAmount(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeN() {
        BankAccount acct = new BankAccount("u","p","ACC");
        acct.getRecentTransactions(-1);
    }
}
