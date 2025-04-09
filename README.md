# cse237-project25

## Team Members:
- JJ Choi  
- Joseph Lim  
- Ryan Lo  
- Aaron Hubhachen  

---

## Iteration 2

### What user stories were completed this iteration?
1. A user should be able to set a spending limit (`SpendingLimit` branch)  
2. A user should be able to freeze their account (`FreezeAccount` branch)  
3. A user should be able to detect fraud activity (`FraudDetection` branch)  
4. A user should be able to view a summary of their payments (`PaymentSummary` branch)  
5. A user should be able to set savings goals (`SavingGoals` branch)  
6. A user should be able to nickname their accounts (`AccountNickname` branch)  
7. A user should be able to view interest rates (`InterestRate` branch)  
8. A user should be able to view their transaction history (`TransactionHistory` branch)  


### What user stories do you intend to complete next iteration?
- Finalize and integrate all features into `main`  
- Improve menu navigation and user interface clarity  
- Refactor `BankApp.java` to remove the large main method and improve code structure  
- Add more comprehensive testing and ensure all features are stable  

---

### Is there anything that you implemented but doesn't currently work?
- The `MoneyReceiver` class needs review; its purpose overlaps with `Transfer` logic  

---

### What commands are needed to compile and run your code from the command line?

We’ve added a `run.sh` script to simplify compiling and running the project on macOS/Linux.

#### How to Run (macOS/Linux):

1. Open Terminal and navigate to the project folder
2. Make the script executable (only once):

```bash
chmod +x run.sh
