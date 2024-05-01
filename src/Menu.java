import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private Bank bank;

    public Menu() {
        scanner = new Scanner(System.in);
        bank = new Bank();
    }

    public void runMenu() {
        displayMainMenu();
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                accessAccount();
                break;
            case 2:
                openNewAccount();
                break;
            case 3:
                closeAllAccounts();
                break;
            case 4:
                System.out.println("Exiting the system...");
                return;
            default:
                System.out.println("Invalid entry. Please enter a number from 1 to 4.");
                break;
        }
        runMenu();
    }

    private void displayMainMenu() {
        System.out.println("******** MENU ********");
        System.out.println("1. Access account");
        System.out.println("2. Open new account");
        System.out.println("3. Close all accounts");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void openNewAccount() {
        System.out.println("Are you a new customer? (yes/no): ");
        String isNewCustomer = scanner.next();
        if (isNewCustomer.equalsIgnoreCase("yes")) {
            Customer newCustomer = createNewCustomer();
            if (newCustomer != null) {
                System.out.println("Enter deposit amount for new account: ");
                double initialDeposit = scanner.nextDouble();
                Account newAccount = new Account(initialDeposit);
                newCustomer.addAccount(newAccount);
                System.out.println("New account opened: " + newAccount);
            }
        } else if (isNewCustomer.equalsIgnoreCase("no")) {
            System.out.println("Enter your PIN: ");
            int pin = scanner.nextInt();
            Customer existingCustomer = bank.getCustomerByPin(pin);
            if (existingCustomer == null) {
                System.out.println("PIN is not valid. ");
            } else {
                System.out.println("Enter deposit amount for new account: ");
                double initialDeposit = scanner.nextDouble();
                Account newAccount = new Account(initialDeposit);
                existingCustomer.addAccount(newAccount);
                System.out.println("New account opened: " + newAccount);

            }
        } else {
            System.out.println("Invalid input. ");
        }
    }

    private Customer createNewCustomer() {
        System.out.println("Enter first name: ");
        String firstName = scanner.next();
        System.out.println("Enter last name: ");
        String lastName = scanner.next();
        System.out.println("Enter PIN: ");
        int pin = scanner.nextInt();
        Customer newCustomer = new Customer(firstName, lastName, pin);
        bank.addCustomer(newCustomer);
        return newCustomer;
    }

    private void accessAccount() {
        System.out.println("Enter your PIN: ");
        int pin = scanner.nextInt();
        Customer customer = bank.getCustomerByPin(pin);
        if (customer == null) {
            System.out.println("PIN is not valid.");
            return;
        }
        System.out.println("Accounts:");
        System.out.println(customer.getAllAccountsInfo());
        System.out.println("Enter the account number you want to access: ");
        int accountNumber = scanner.nextInt();
        Account account = customer.getAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("Account number is invalid.");
            return;
        }

        displayAccountMenu();
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
                break;
            case 2:
                System.out.println("Enter withdrawal amount: ");
                double withdrawalAmount = scanner.nextDouble();
                account.withdraw(withdrawalAmount);
                break;
            case 3:
                System.out.println("Account balance: " + account.getBalance());
                break;
            case 4:
                customer.removeAccount(account);
                System.out.println("Account closed successfully.");
                break;
            default:
                System.out.println("Invalid entry.");
                break;

        }
    }

    private void closeAllAccounts() {
        System.out.println("Enter your PIN: ");
        int pin = scanner.nextInt();
        Customer customer = bank.getCustomerByPin(pin);
        if (customer == null) {
            System.out.println("PIN is not valid.");
            return;
        }
        bank.removeCustomer(customer);
        System.out.println("Customer removed from bank registry.");
    }

    private void displayAccountMenu() {
        System.out.println("Account Menu");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check balance");
        System.out.println("4. Close account");
        System.out.print("Enter your choice: ");
    }
}