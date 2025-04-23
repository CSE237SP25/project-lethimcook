# cse237-project25

## Team Members:
- JJ Choi  
- Joseph Lim  
- Ryan Lo  
- Aaron Hubhachen  

---

## Iteration 2

### What user stories were completed this iteration?
1. User can set a spending limit
2. User can freeze/unfreeze their account
3. User can detect potential fraud activity
4. User can view a summary of their payments
5. User can set savings goals
6. User can nickname their accounts
7. User can view interest rates
8. User can view their transaction history

### What user stories do you intend to complete next iteration?
- Add transaction notes
- Add account statistics
- View account number
- Add privacy mode
- Add currency conversion
- Add login history tracking
- Add CSV export for transactions
- Add deposit/withdrawal totals
- Add viewing last N transactions

### Is there anything that you implemented but doesn't currently work?
- The `MoneyReceiver` class needs review; its purpose overlaps with `Transfer` logic

### What commands are needed to compile and run your code from the command line?
We’ve added a `run.sh` script to simplify compiling and running the project on macOS/Linux.

#### How to Run (macOS/Linux):
1. Open Terminal and navigate to the project folder
2. Make the script executable (only once):
```bash
chmod +x run.sh
```
3. Run the application:
```bash
./run.sh
```

---

## Iteration 3

### What user stories were completed this iteration?
1. User can add notes when making a deposit, withdrawal, or transfer (#27)
2. User can view simple statistics on their account (#29)
3. User can view their deposit and withdrawal total (#34)
4. User can return the last n transactions (#31)
5. User can export their transaction history via CSV (#35) (Placeholder - Needs confirmation)
6. User can see how many times they've logged in (login history) (#37) (Placeholder - Needs confirmation)
7. User can see balance in a different currency (#40, #39)
8. User can toggle a privacy mode (#41)
9. User can view their account number (#45)

### What user stories do you intend to complete next iteration? (Final Phase)
- We're done :)

### Is there anything that you implemented but doesn't currently work?
No

### What commands are needed to compile and run your code from the command line?
Use the `run.sh` script:

```bash
./run.sh
```