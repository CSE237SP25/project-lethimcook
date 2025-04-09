package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import bankapp.BankAccount;
import bankapp.Menu;
import bankapp.Bank;

public class MenuTests {
    private Menu menu;
    private Bank bank;
    
    @Before
    public void setUp() {
        menu = new Menu();
        bank = new Bank();
        
        // Create a test account directly using the Bank class
        bank.createAccount("testuser", "password", "TEST123");
        
        // Login directly using the Bank class
        BankAccount account = bank.login("testuser", "password");
        
        // Set the current account in the Menu
        // This requires adding a setCurrentAccount method to Menu class
        // For now, we'll use reflection to set the private field
        try {
            java.lang.reflect.Field field = Menu.class.getDeclaredField("currentAccount");
            field.setAccessible(true);
            field.set(menu, account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testUserDeposit() {
        // Verify user is logged in
        assertNotNull("User should be logged in", menu.getCurrentAccount());
        
        // Call method being tested
        menu.processUserInput(25);
        
        // Use assertions to verify correctness
        BankAccount account = menu.getCurrentAccount();
        assertEquals(25.0, account.getBalance(), 0.005);
    }
    
    @Test
    public void testUserWithdrawal() {
        // First deposit some money
        menu.processUserInput(100);
        
        // Then withdraw using the BankAccount directly since Menu.processUserInput doesn't handle withdrawals
        BankAccount account = menu.getCurrentAccount();
        account.withdraw(50);
        
        // Verify balance
        assertEquals(50.0, account.getBalance(), 0.005);
    }
    
    @Test
    public void testTransactionHistory() {
        // Make some transactions
        menu.processUserInput(100);
        
        // Withdraw using BankAccount directly
        BankAccount account = menu.getCurrentAccount();
        account.withdraw(30);
        
        // Display transaction history (this will print to console)
        menu.displayTransactionHistory();
        
        // Verify balance
        assertEquals(70.0, account.getBalance(), 0.005);
    }
    
    @Test
    public void testTransferMoney() {
        // Create a second account directly using the Bank class
        bank.createAccount("recipient", "password", "TEST456");
        
        // Deposit money to the first account
        menu.processUserInput(100);
        
        // Transfer money using the BankAccount directly
        BankAccount sender = menu.getCurrentAccount();
        BankAccount recipient = bank.getAccount("recipient");
        sender.transfer(recipient, 50);
        
        // Verify balances
        assertEquals(50.0, sender.getBalance(), 0.005);
        assertEquals(50.0, recipient.getBalance(), 0.005);
    }
}
