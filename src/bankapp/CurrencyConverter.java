package bankapp;

import java.util.Scanner;

public class CurrencyConverter {
    private static final double USD_TO_EUR = 0.92;

    @SuppressWarnings("resource") 
    public static void convertCurrency() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== USD to EUR Converter ===");
        System.out.print("Enter amount in USD: ");

        double usd;
        try {
            usd = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }

        double eur = usd * USD_TO_EUR;
        System.out.printf("Converted: $%.2f USD = €%.2f EUR\n", usd, eur);
    }
}