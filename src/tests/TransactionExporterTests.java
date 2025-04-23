package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import bankapp.BankAccount;
import bankapp.TransactionExporter;
import java.io.File;

public class TransactionExporterTests {
    
    @Test
    public void testExportCreatesFile() {
        BankAccount account = new BankAccount("username", "password", "test_account");
        account.deposit(100, "Initial deposit");

        TransactionExporter exporter = new TransactionExporter();
        String filename = "test.csv";
        exporter.exportToCSV(account, filename);

        File file = new File(filename);
        assertTrue(file.exists());

        file.delete();
    }
}
