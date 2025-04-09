package bankapp;

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            menu.displayMainMenu();
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    menu.createAccount();
                    break;
                case 2:
                    menu.login();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            
            if (menu.getCurrentAccount() != null) {
                boolean loggedIn = true;
                while (loggedIn) {
                    menu.displayLoggedInMenu();
                    choice = scanner.nextInt();
                    
                    switch (choice) {
                        case 1:
                            System.out.print("Enter deposit amount: ");
                            double amount = scanner.nextDouble();
                            menu.processUserInput(amount);
                            break;
                        case 2:
                            menu.transferMoney();
                            break;
                        case 3:
                            menu.checkBalance();
                            break;
                        case 4:
                            menu.displayTransactionHistory();
                            break;
                        case 5:
                            menu.changePassword();
                            break;
                        case 6:
                            menu.changeUsername();
                            break;
                        case 7:
                            menu.changeNickname();
                            break;
                        case 8:
                            menu.applyInterest();
                            break;
                        case 9:
                            menu.viewInterestRate();
                            break;
                        case 10:
                            menu.setSavingsGoal();
                            break;
                        case 11:
                            menu.viewSavingsGoalProgress();
                            break;
                        case 12:
                            menu.freezeAccount();
                            break;
                        case 13:
                            menu.unfreezeAccount();
                            break;
                        case 14:
                            menu.logout();
                            loggedIn = false;
                        case 15:
                            menu.setSpendingLimit();
                            break;
                        case 16:
                            menu.viewMonthlySpending();
                            break;
                        case 17:
                            menu.logout();
                            loggedIn = false;
                            break;
                            
                        default:
                            System.out.println("Invalid choice!");
                    }
                }
            }
        }
        
        System.out.println("Thank you for using our banking application!");
        scanner.close();
    }
}
