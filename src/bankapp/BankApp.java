package bankapp;

import java.util.Scanner;
import java.util.InputMismatchException;

public class BankApp {
    private final Menu menu;
    private final Scanner scanner;

    public BankApp() {

        this.menu = new Menu(); 
        this.scanner = new Scanner(System.in);
    }

    public void runApplication() {
        boolean running = true;
        while (running) {
            menu.displayMainMenu();
            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            } finally {
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    menu.createAccount();
                    break;
                case 2:
                    menu.login();
                    if (menu.getCurrentAccount() != null) { 
                        runLoggedInMenu();
                    }
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    if (choice != -1) { 
                         System.out.println("Invalid choice! Please try again.");
                    }
            }
        }
        System.out.println("Thank you for using our banking application!");
        scanner.close(); 
    }

    private void runLoggedInMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            menu.displayLoggedInMenu();
            int choice = -1;
             try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            } finally {
                 scanner.nextLine();
            }

            BankAccount currentAccount = menu.getCurrentAccount(); 
            if (currentAccount == null) { 
                System.out.println("Error: No user logged in. Logging out.");
                loggedIn = false;
                continue;
            }

            try {
                switch (choice) {
                    case 1: //deposit
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = -1;
                        try {
                             depositAmount = scanner.nextDouble();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid amount. Please enter a number.");
                        } finally {
                             scanner.nextLine(); // Consume newline
                        }
                        if (depositAmount != -1) {
                             currentAccount.deposit(depositAmount);
                             System.out.println("Deposit successful. New balance: " + String.format("%.2f", currentAccount.getBalance()));
                        }
                        break;
                    case 2: //transfer money
                        menu.transferMoney();
                        break;
                    case 3: //check balance 
                        menu.checkBalance();
                        break;
                    case 4: //view transaction history
                        menu.displayTransactionHistory();
                        break;
                    case 5: //change password
                        menu.changePassword();
                        break;
                    case 6: //change username
                        menu.changeUsername();
                        break;
                    case 7: //change nickname
                        menu.changeNickname();
                        break;
                    case 8: //apply interest
                        menu.applyInterest();
                        break;
                    case 9: //view interest rate
                        menu.viewInterestRate();
                        break;
                    case 10: //set savings goal
                        menu.setSavingsGoal();
                        break;
                    case 11: //view savings goal progress
                        menu.viewSavingsGoalProgress();
                        break;
                    case 12: //freeze account
                        menu.freezeAccount();
                        break;
                    case 13: //unfreeze account
                        menu.unfreezeAccount();
                        break;
                    case 14: //logout
                    case 17: //also logout
                        menu.logout();
                        loggedIn = false;
                        break;
                    case 15: //set monthly spending limit
                        menu.setSpendingLimit();
                         break;
                    case 16: //view monthly spending
                         menu.viewMonthlySpending();
                         break;
                    default:
                         if (choice != -1) {
                            System.out.println("Invalid choice! Please try again.");
                         }
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                 System.out.println("Invalid input format.");
                 scanner.nextLine(); //clear invalid input
            }
        }
    }

    public static void main(String[] args) {
        BankApp app = new BankApp();
        app.runApplication();
    }
}
