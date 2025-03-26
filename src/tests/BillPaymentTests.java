package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import bankapp.Bank;
import bankapp.BankAccount;
import bankapp.BillPayment;

public class BillPaymentTests {
    @Test
    public void testSuccessfulBillPayment() {
        // 1. Create objects to be tested
        Bank bank = new Bank();
        bank.createAccount("testuser", "password", "123456");
        BankAccount testAccount = bank.getAccount("testuser");
        testAccount.deposit(500.00);
        
        BillPayment billPayment = new BillPayment(bank);
        
        // 2. Call the method being tested
        billPayment.payBill(testAccount, "Electric Company", 100.00);
        
        // 3. Use assertions to verify results
        assertEquals(400.00, testAccount.getBalance(), 0.005);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testInsufficientFundsBillPayment() {
        // 1. Create objects to be tested
        Bank bank = new Bank();
        bank.createAccount("testuser", "password", "123456");
        BankAccount testAccount = bank.getAccount("testuser");
        testAccount.deposit(500.00);
        
        BillPayment billPayment = new BillPayment(bank);
        
        // 2. Call the method being tested
        billPayment.payBill(testAccount, "Utility Company", 600.00);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyBillProviderName() {
        // 1. Create objects to be tested
        Bank bank = new Bank();
        bank.createAccount("testuser", "password", "123456");
        BankAccount testAccount = bank.getAccount("testuser");
        testAccount.deposit(500.00);
        
        BillPayment billPayment = new BillPayment(bank);
        
        // 2. Call the method being tested
        billPayment.payBill(testAccount, "", 50.00);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullBillProviderName() {
        // 1. Create objects to be tested
        Bank bank = new Bank();
        bank.createAccount("testuser", "password", "123456");
        BankAccount testAccount = bank.getAccount("testuser");
        testAccount.deposit(500.00);
        
        BillPayment billPayment = new BillPayment(bank);
        
        // 2. Call the method being tested
        billPayment.payBill(testAccount, null, 50.00);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeBillAmount() {
        // 1. Create objects to be tested
        Bank bank = new Bank();
        bank.createAccount("testuser", "password", "123456");
        BankAccount testAccount = bank.getAccount("testuser");
        testAccount.deposit(500.00);
        
        BillPayment billPayment = new BillPayment(bank);
        
        // 2. Call the method being tested
        billPayment.payBill(testAccount, "Phone Company", -50.00);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testZeroBillAmount() {
        // 1. Create objects to be tested
        Bank bank = new Bank();
        bank.createAccount("testuser", "password", "123456");
        BankAccount testAccount = bank.getAccount("testuser");
        testAccount.deposit(500.00);
        
        BillPayment billPayment = new BillPayment(bank);
        
        // 2. Call the method being tested
        billPayment.payBill(testAccount, "Internet Provider", 0.00);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNullAccount() {
        // 1. Create objects to be tested
        Bank bank = new Bank();
        BillPayment billPayment = new BillPayment(bank);
        
        // 2. Call the method being tested
        billPayment.payBill(null, "Test Provider", 50.00);
    }
}