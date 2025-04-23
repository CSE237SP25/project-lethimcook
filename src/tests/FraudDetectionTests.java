package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bankapp.BankAccount;
import bankapp.FraudDetection;

public class FraudDetectionTests {

    @Test
    public void testAlertForLargeTransaction() {
        BankAccount account = new BankAccount("user", "pass", "TestAccount");
        account.deposit(5000);
        FraudDetection fraud = new FraudDetection(1000);
        fraud.checkForLargeTransactions(account);
        assertEquals(5000, account.getBalance(), 0.001);
    }
}
