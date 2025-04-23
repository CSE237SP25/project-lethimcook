package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bankapp.BankAccount;
import bankapp.BalanceAlertManager;

public class BalanceAlertManagerTests {

    @Test
    public void testIsBelowThresholdTrue() {
        BankAccount account = new BankAccount("user", "pass", "ACC001");
        account.deposit(50);
        BalanceAlertManager alert = new BalanceAlertManager(100);
        assertTrue(alert.isBelowThreshold(account));
    }

    @Test
    public void testIsBelowThresholdFalse() {
        BankAccount account = new BankAccount("user", "pass", "ACC002");
        account.deposit(150);
        BalanceAlertManager alert = new BalanceAlertManager(100);
        assertFalse(alert.isBelowThreshold(account));
    }

    @Test
    public void testThresholdCanBeUpdated() {
        BankAccount account = new BankAccount("user", "pass", "ACC003");
        account.deposit(75);
        BalanceAlertManager alert = new BalanceAlertManager(50);
        alert.setThreshold(100);
        assertTrue(alert.isBelowThreshold(account));
    }
}
