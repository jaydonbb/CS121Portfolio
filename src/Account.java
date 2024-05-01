
public class Account {
    private double balance;
    private int accountNumber;
    private static int numberOfAccounts = 1000;

    public Account(double initialDeposit) {
        balance = initialDeposit;
        accountNumber = ++numberOfAccounts;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Amount deposited: " + amount);
        System.out.println("Updated balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {
            balance -= amount;
            System.out.println("Amount withdrawn: " + amount);
            System.out.println("Updated balance: " + balance);
        }
    }

    public String toString() {
        return "Account Number: " + accountNumber + "\nBalance: " + balance;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
