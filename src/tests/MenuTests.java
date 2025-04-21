package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import bankapp.BankAccount;
import bankapp.Menu;
import bankapp.Bank;
import bankapp.Transaction;

public class MenuTests {
    private Menu menu;
    private Bank bank;
    
    @Before
    public void setUp() {
        menu = new Menu();
        bank = new Bank();
        
        bank.createAccount("testuser", "password", "TEST123");
        BankAccount account = bank.login("testuser", "password");
       
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
        assertNotNull("User should be logged in", menu.getCurrentAccount());
        menu.processUserInput(25);
        BankAccount account = menu.getCurrentAccount();
        assertEquals(25.0, account.getBalance(), 0.005);
    }
    
    @Test
    public void testUserWithdrawal() {
        menu.processUserInput(100);
        BankAccount account = menu.getCurrentAccount();
        account.withdraw(50);
        assertEquals(50.0, account.getBalance(), 0.005);
    }
    
    @Test
    public void testTransactionHistory() {
        menu.processUserInput(100);
        BankAccount account = menu.getCurrentAccount();
        account.withdraw(30);
        menu.displayTransactionHistory();
        assertEquals(70.0, account.getBalance(), 0.005);
    }
    
    @Test
    public void testTransferMoney() {
        bank.createAccount("recipient", "password", "TEST456");
        menu.processUserInput(100);
        BankAccount sender = menu.getCurrentAccount();
        BankAccount recipient = bank.getAccount("recipient");
        sender.transfer(recipient, 50);
        assertEquals(50.0, sender.getBalance(), 0.005);
        assertEquals(50.0, recipient.getBalance(), 0.005);
    }
    
    @Test
    public void testTransferMoneyWithNote() {
        bank.createAccount("recipient", "password", "TEST456");
        menu.processUserInput(100);
        BankAccount sender = menu.getCurrentAccount();
        BankAccount recipient = bank.getAccount("recipient");
        sender.transfer(recipient, 50, "Test transfer note");
        
    
        assertEquals(50.0, sender.getBalance(), 0.005);
        assertEquals(50.0, recipient.getBalance(), 0.005);
        
      
        Transaction lastTransaction = sender.getTransactionHistory().get(sender.getTransactionHistory().size() - 1);
        assertEquals("Test transfer note", lastTransaction.getNote());
    }
}
