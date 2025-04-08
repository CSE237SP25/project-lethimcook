package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bankapp.Transaction;
import java.time.LocalDateTime;

public class TransactionTests {
    
    @Test
    public void testDepositTransaction() {
        Transaction transaction = new Transaction("DEPOSIT", 100.0, "ACC123", null);
        
        assertEquals("DEPOSIT", transaction.getType());
        assertEquals(100.0, transaction.getAmount(), 0.001);
        assertEquals("ACC123", transaction.getFromAccount());
        assertNull(transaction.getToAccount());
        assertTrue(transaction.getDescription().contains("Deposit of $100.00"));
        assertNotNull(transaction.getTimestamp());
    }
    
    @Test
    public void testWithdrawalTransaction() {
        Transaction transaction = new Transaction("WITHDRAW", 50.0, "ACC123", null);
        
        assertEquals("WITHDRAW", transaction.getType());
        assertEquals(50.0, transaction.getAmount(), 0.001);
        assertEquals("ACC123", transaction.getFromAccount());
        assertNull(transaction.getToAccount());
        assertTrue(transaction.getDescription().contains("Withdrawal of $50.00"));
        assertNotNull(transaction.getTimestamp());
    }
    
    @Test
    public void testTransferTransaction() {
        Transaction transaction = new Transaction("TRANSFER", 75.0, "ACC123", "ACC456");
        
        assertEquals("TRANSFER", transaction.getType());
        assertEquals(75.0, transaction.getAmount(), 0.001);
        assertEquals("ACC123", transaction.getFromAccount());
        assertEquals("ACC456", transaction.getToAccount());
        assertTrue(transaction.getDescription().contains("Transfer of $75.00"));
        assertTrue(transaction.getDescription().contains("ACC456"));
        assertNotNull(transaction.getTimestamp());
    }
    
    @Test
    public void testUnknownTransactionType() {
        Transaction transaction = new Transaction("UNKNOWN", 25.0, "ACC123", null);
        
        assertEquals("UNKNOWN", transaction.getType());
        assertEquals(25.0, transaction.getAmount(), 0.001);
        assertEquals("Unknown transaction", transaction.getDescription());
    }
    
    @Test
    public void testToString() {
        Transaction transaction = new Transaction("DEPOSIT", 100.0, "ACC123", null);
        String toString = transaction.toString();
        
        assertTrue(toString.contains("Deposit of $100.00"));
        assertTrue(toString.contains(transaction.getTimestamp().toString()));
    }
    
    @Test
    public void testTransactionTimestamp() {
        LocalDateTime before = LocalDateTime.now();
        Transaction transaction = new Transaction("DEPOSIT", 100.0, "ACC123", null);
        LocalDateTime after = LocalDateTime.now();
        
        assertTrue(transaction.getTimestamp().isAfter(before) || transaction.getTimestamp().equals(before));
        assertTrue(transaction.getTimestamp().isBefore(after) || transaction.getTimestamp().equals(after));
    }
    
    @Test
    public void testZeroAmountTransaction() {
        Transaction transaction = new Transaction("DEPOSIT", 0.0, "ACC123", null);
        
        assertEquals(0.0, transaction.getAmount(), 0.001);
        assertTrue(transaction.getDescription().contains("$0.00"));
    }
    
    @Test
    public void testLargeAmountTransaction() {
        Transaction transaction = new Transaction("DEPOSIT", 999999.99, "ACC123", null);
        
        assertEquals(999999.99, transaction.getAmount(), 0.001);
        assertTrue(transaction.getDescription().contains("$999999.99"));
    }
} 