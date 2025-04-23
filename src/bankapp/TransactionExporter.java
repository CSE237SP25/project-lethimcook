package bankapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TransactionExporter {

    public void exportToCSV(BankAccount account, String filename) {
        List<Transaction> transactions = account.getTransactionHistory();

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("Type,Amount,FromAccount,ToAccount,Note,Timestamp\n");

            for (Transaction t : transactions) {
                String type = t.getType();
                double amount = t.getAmount();
                String from = t.getFromAccount();
                String to = t.getToAccount();
                String note = t.getNote();
                String time = t.getTimestamp().toString();

                if (to == null) {
                    to = "";
                }

                if (note == null) {
                    note = "";
                }

                writer.write(type + "," + amount + "," + from + "," + to + "," + note + "," + time + "\n");
            }

            writer.close();
            System.out.println("Export Successful! Have a nice day!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
