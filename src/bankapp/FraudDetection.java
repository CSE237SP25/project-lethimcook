package bankapp;

import java.util.List;

public class FraudDetection {

    private double transactionLimit;

    public FraudDetection(double upperTransactionLimit) {
        this.transactionLimit = upperTransactionLimit;
    }

    public void checkForLargeTransactions(BankAccount account) {
        List<Transaction> history = account.getTransactionHistory();

        for (Transaction t : history) {
            if (t.getAmount() >= transactionLimit) {
                System.out.println("ALERT: Suspicious Activity Detected " + account.getAccountNumber() +
                        " -> $" + t.getAmount());
            }
        }
    }

    public void setLargeTransactionLimit(double limit) {
        this.transactionLimit = limit;
    }

    public double getLargeTransactionLimit() {
        return transactionLimit;
    }
}
