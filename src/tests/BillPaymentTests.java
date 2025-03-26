package bankapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BillPaymentTest {
    private Bank testBank;
    private BankAccount testAccount;
    private BillPayment billPayment;
    
    @BeforeEach
    public void setUp() {
        // Create a test bank and account
        testBank = new Bank();
        testBank.createAccount("testuser", "password", "123456");
        testAccount = testBank.getAccount("testuser");
        testAccount.deposit(500.00);  // Initial balance
        
        // Create bill payment instance
        billPayment = new BillPayment(testBank);
    }
    
    @Test
    public void testSuccessfulBillPayment() {
        // Test successful bill payment
        billPayment.payBill(testAccount, "Electric Company", 100.00);
        
        // Verify remaining balance
        assertEquals(400.00, testAccount.getBalance(), 0.001);
    }
    
    @Test
    public void testInsufficientFundsBillPayment() {
        // Attempt to pay a bill larger than account balance
        assertThrows(IllegalStateException.class, () -> {
            billPayment.payBill(testAccount, "Utility Company", 600.00);
        });
    }
    
    @Test
    public void testInvalidBillProviderName() {
        // Test empty bill provider name
        assertThrows(IllegalArgumentException.class, () -> {
            billPayment.payBill(testAccount, "", 50.00);
        });
        
        // Test null bill provider name
        assertThrows(IllegalArgumentException.class, () -> {
            billPayment.payBill(testAccount, null, 50.00);
        });
    }
    
    @Test
    public void testInvalidBillAmount() {
        // Test negative bill amount
        assertThrows(IllegalArgumentException.class, () -> {
            billPayment.payBill(testAccount, "Phone Company", -50.00);
        });
        
        // Test zero bill amount
        assertThrows(IllegalArgumentException.class, () -> {
            billPayment.payBill(testAccount, "Internet Provider", 0.00);
        });
    }
    
    @Test
    public void testNullAccount() {
        // Test null account
        assertThrows(IllegalArgumentException.class, () -> {
            billPayment.payBill(null, "Test Provider", 50.00);
        });
    }
}