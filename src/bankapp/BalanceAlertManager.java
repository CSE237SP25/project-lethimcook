package bankapp;

public class BalanceAlertManager {
    private double threshold;

    public BalanceAlertManager(double threshold) {
        this.threshold = threshold;
    }

    public boolean isBelowThreshold(BankAccount account) {
        return account.getBalance() < threshold;
    }

    public void printAlert(BankAccount account) {
        if (isBelowThreshold(account)) {
            System.out.println("ALERT: Balance is below $" + threshold + " for account " + account.getAccountNumber());
        }
    }

    public void setThreshold(double newThreshold) {
        this.threshold = newThreshold;
    }

    public double getThreshold() {
        return threshold;
    }
}
