package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bankapp.BankAccount;

public class DepositWithdrawalTotalsTests {

    @Test
    public void testNoTransactions() {
        BankAccount acct = new BankAccount("u","p","ACC");
        assertEquals(0.0, acct.getTotalDeposits(), 0.001);
        assertEquals(0.0, acct.getTotalWithdrawals(), 0.001);
    }

    @Test
    public void testOnlyDeposits() {
        BankAccount acct = new BankAccount("u","p","ACC");
        acct.deposit(100.0);
        acct.deposit(50.0);
        assertEquals(150.0, acct.getTotalDeposits(), 0.001);
        assertEquals(0.0, acct.getTotalWithdrawals(), 0.001);
    }

    @Test
    public void testOnlyWithdrawals() {
        BankAccount acct = new BankAccount("u","p","ACC");
        acct.deposit(200.0);           // to have balance
        acct.withdraw(30.0);
        acct.withdraw(20.0);
        assertEquals(200.0, acct.getTotalDeposits(), 0.001);
        assertEquals(50.0, acct.getTotalWithdrawals(), 0.001);
    }

    @Test
    public void testMixedTransactions() {
        BankAccount acct = new BankAccount("u","p","ACC");
        acct.deposit(300.0);
        acct.withdraw(75.0);
        acct.deposit(25.0);
        acct.withdraw(25.0);
        assertEquals(325.0, acct.getTotalDeposits(), 0.001);
        assertEquals(100.0, acct.getTotalWithdrawals(), 0.001);
    }
}