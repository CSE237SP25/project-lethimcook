package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class MoneyReceiverTests {
    private MoneyReceiver moneyReceiver;
    private Bank bank;
    
    @Before
    public void setUp() {
        bank = new Bank();
        bank.createAccount("sender", "senderpass", "123456");
        bank.createAccount("recipient", "recipientpass", "789012");
        
        BankAccount senderAccount = bank.getAccount("sender");
        senderAccount.deposit(500.00);
        
        BankAccount recipientAccount = bank.getAccount("recipient");
        recipientAccount.deposit(100.00);
        
        moneyReceiver = new MoneyReceiver(bank);
    }
    
    @Test
    public void testSuccessfulMoneyReceive() {
        // 1. Get accounts
        BankAccount senderAccount = bank.getAccount("sender");
        BankAccount recipientAccount = bank.getAccount("recipient");
        
        // 2. Perform money receive
        moneyReceiver.receiveMoney(recipientAccount, "sender", 200.00);
        
        // 3. Verify sender's balance
        assertEquals(300.00, senderAccount.getBalance(), 0.005);
        
        // 4. Verify recipient's balance
        assertEquals(300.00, recipientAccount.getBalance(), 0.005);
    }
}