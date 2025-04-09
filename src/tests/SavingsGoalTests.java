package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bankapp.BankAccount;

public class SavingsGoalTests {
    
    @Test
    public void testSetAndGetSavingsGoal() {
        BankAccount account = new BankAccount("user1", "pass1", "ACC001");
        account.setSavingsGoal(1000.00);
        assertEquals(1000.0, account.getSavingsGoal(), 0.001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSavingsGoal() {
        BankAccount account = new BankAccount("user1", "pass1", "ACC001");
        account.setSavingsGoal(-500.00);
    }
    
    @Test
    public void testSavingsGoalProgressPercentage() {
        BankAccount account = new BankAccount("user1", "pass1", "ACC001");
        account.deposit(200.00);
        account.setSavingsGoal(1000.00);
        double progress = account.getSavingsGoalProgressPercentage();
        // Expected progress: 20%
        assertEquals(20.0, progress, 0.001);
    }
    
    @Test
    public void testRemainingAmountToSavingsGoal() {
        BankAccount account = new BankAccount("user1", "pass1", "ACC001");
        account.deposit(300.00);
        account.setSavingsGoal(1000.00);
        double remaining = account.getRemainingAmountToSavingsGoal();
        // Expected remaining amount: 700
        assertEquals(700.0, remaining, 0.001);
    }
    
    @Test
    public void testOverAchievedSavingsGoal() {
        BankAccount account = new BankAccount("user1", "pass1", "ACC001");
        account.deposit(1200.00);
        account.setSavingsGoal(1000.00);
        double remaining = account.getRemainingAmountToSavingsGoal();
        // If balance exceeds savings goal, remaining should be 0
        assertEquals(0.0, remaining, 0.001);
    }
}
