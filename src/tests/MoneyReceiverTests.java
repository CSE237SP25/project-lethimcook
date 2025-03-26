package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import bankapp.Bank;
import bankapp.BankAccount;
import bankapp.MoneyReceiver;

public class MoneyReceiverTests {
    @Test
    public void testSuccessfulMoneyReceive() {
        // 1. Create objects to be tested
        Bank bank = new Bank();
        bank.createAccount("sender", "senderpass", "123456");
        bank.createAccount("recipient", "recipientpass", "789012");
        
        BankAccount senderAccount = bank.getAccount("sender");
        BankAccount recipientAccount = bank.getAccount("recipient");
        senderAccount.deposit(500.00);
        recipientAccount.deposit(100.00);
        
        MoneyReceiver moneyReceiver = new MoneyReceiver(bank);
        
        // 2. Call the method being tested
        moneyReceiver.receiveMoney(recipientAccount, "sender", 200.00);
        
        // 3. Use assertions to verify results
        assertEquals(300.00, senderAccount.getBalance(), 0.005);
        assertEquals(300.00, recipientAccount.getBalance(), 0.005);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSenderUsername() {
        // 1. Create objects to be tested
        Bank bank = new Bank();
        bank.createAccount("recipient", "recipientpass", "789012");
        
        BankAccount recipientAccount = bank.getAccount("recipient");
        MoneyReceiver moneyReceiver = new MoneyReceiver(bank);
        
        // 2. Call the method being tested
        moneyReceiver.receiveMoney(recipientAccount, "nonexistent", 50.00);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeTransferAmount() {
        // 1. Create objects to be tested
        Bank bank = new Bank();
        bank.createAccount("sender", "senderpass", "123456");
        bank.createAccount("recipient", "recipientpass", "789012");
        
        BankAccount senderAccount = bank.getAccount("sender");
        BankAccount recipientAccount = bank.getAccount("recipient");
        
        MoneyReceiver moneyReceiver = new MoneyReceiver(bank);
        
        // 2. Call the method being tested
        moneyReceiver.receiveMoney(recipientAccount, "sender", -50.00);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testInsufficientFundsSender() {
        // 1. Create objects to be tested
        Bank bank = new Bank();
        bank.createAccount("sender", "senderpass", "123456");
        bank.createAccount("recipient", "recipientpass", "789012");
        
        BankAccount senderAccount = bank.getAccount("sender");
        BankAccount recipientAccount = bank.getAccount("recipient");
        senderAccount.deposit(100.00);
        
        MoneyReceiver moneyReceiver = new MoneyReceiver(bank);
        
        // 2. Call the method being tested
        moneyReceiver.receiveMoney(recipientAccount, "sender", 200.00);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullRecipientAccount() {
        // 1. Create objects to be tested
        Bank bank = new Bank();
        bank.createAccount("sender", "senderpass", "123456");
        
        MoneyReceiver moneyReceiver = new MoneyReceiver(bank);
        
        // 2. Call the method being tested
        moneyReceiver.receiveMoney(null, "sender", 50.00);
    }
}