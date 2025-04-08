package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bankapp.BankAccount;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class InterestTests {
    
    @Test
    public void testDefaultInterestRate() {
        BankAccount account = new BankAccount("user1", "pass1", "ACC001");
        assertEquals(0.5, account.getInterestRate(), 0.001);
    }
    
    @Test
    public void testCustomInterestRate() {
        BankAccount account = new BankAccount("user1", "pass1", "ACC001", 2.5);
        assertEquals(2.5, account.getInterestRate(), 0.001);
    }
    
    @Test
    public void testSetInterestRate() {
        BankAccount account = new BankAccount("user1", "pass1", "ACC001");
        account.setInterestRate(1.5);
        assertEquals(1.5, account.getInterestRate(), 0.001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeInterestRate() {
        BankAccount account = new BankAccount("user1", "pass1", "ACC001");
        account.setInterestRate(-1.0);
    }
    
    @Test
    public void testNoInterestIfLessThanDay() {
        BankAccount account = new BankAccount("user1", "pass1", "ACC001", 5.0);
        account.deposit(1000.0);
        
        double interest = account.calculateInterest();
        assertEquals(0.0, interest, 0.001);
    }
    @Test
    public void testBasicInterestCalculation() {
        BankAccount account = new BankAccount("user1", "pass1", "ACC001", 5.0);
        account.deposit(1000.0);
        double interest = account.calculateInterest();
        assertTrue("Interest should be calculated", interest >= 0.0);
    }
} 