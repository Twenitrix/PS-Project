import java.util.Scanner;
/*Add Tomorrow
* Display Account--
* Initial balance
* Password Protection--
* Transaction History
* Auto generate accNo.
* SI calc if possible */

public class Main {
    public static void main(String[] args) {
        AccountsService service = new AccountsService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n==============================================================================");
        System.out.println("                Welcome to Group 2 Bank Management System");
        System.out.println("==============================================================================\n");

        int choice;
        do {
            System.out.print("""
                    -----------------------------------Menu-------------------------------------
                    1. Create Account
                    2. Deposit
                    3. Withdraw
                    4. Display Account
                    5. Update Name
                    6. Delete Account
                    7. Display All Accounts (Admin)
                    8. Transaction History
                    9. Interest Calculator
                    10. Exit
                    ----------------------------------------------------------------------------
                    """);
            System.out.print("Please Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Please Enter Your New Account Number: ");
                    long accNo = scanner.nextLong();
                    scanner.nextLine();
                    while (service.checkAccount(accNo)) {
                        System.out.println("Account already exists. Try another.");
                        System.out.print("Please Enter Your Account Number: ");
                        accNo = scanner.nextLong();
                        scanner.nextLine();
                    }
                    System.out.print("Please Enter Your Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Please Enter Your Initial Balance (must be > 0): ");
                    float balance = scanner.nextFloat();
                    while (balance <= 0) {
                        System.out.print("Invalid amount. Initial balance must be > 0: ");
                        balance = scanner.nextFloat();
                    }
                    scanner.nextLine();
                    System.out.print("Please Set Your Password: ");
                    String password = scanner.nextLine();
                    service.createAccount(accNo, name, balance, password);
                    System.out.println("Account Created Successfully!");
                    break;
                }
                case 2: {
                    System.out.print("Please Enter Your Account Number: ");
                    long accNo = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Please Enter Your Password: ");
                    String password = scanner.nextLine();
                    if (!service.verifyPassword(accNo, password)) {
                        System.out.println("Wrong password. Access denied.");
                        break;
                    }
                    System.out.print("Enter The Amount To Be Deposited: ");
                    float amount = scanner.nextFloat();
                    while (amount <= 0) {
                        System.out.println("Invalid Amount.");
                        System.out.print("Please Enter The Amount: ");
                        amount = scanner.nextFloat();
                    }
                    service.deposit(accNo, amount);
                    break;
                }
                case 3: {
                    System.out.print("Please Enter Your Account Number: ");
                    long accNo = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Please Enter Your Password: ");
                    String password = scanner.nextLine();
                    if (!service.verifyPassword(accNo, password)) {
                        System.out.println("Wrong password. Access denied.");
                        break;
                    }
                    System.out.print("Please Enter The Amount To Withdraw: ");
                    float amount = scanner.nextFloat();
                    while (amount <= 0 || !service.withdraw(accNo, amount)) {
                        if (amount <= 0) System.out.println("Invalid Amount.");
                        System.out.print("Please Enter The Amount: ");
                        amount = scanner.nextFloat();
                    }
                    break;
                }
                case 4: {
                    System.out.print("Please Enter Account No: ");
                    long accNo = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Please Enter Your Password: ");
                    String password = scanner.nextLine();
                    if (!service.verifyPassword(accNo, password)) {
                        System.out.println("Wrong password. Access denied.");
                        break;
                    }
                    Accounts a = service.findAccount(accNo);
                    System.out.println("Account No : " + a.getAccNo());
                    System.out.println("Name       : " + a.getName());
                    System.out.println("Balance    : Rs. " + a.getBalance());
                    break;
                }
                case 5: {
                    System.out.print("Please Enter Account No.: ");
                    long accNo = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Please Enter Your Password: ");
                    String password = scanner.nextLine();
                    if (!service.verifyPassword(accNo, password)) {
                        System.out.println("Wrong password. Access denied.");
                        break;
                    }
                    System.out.print("Please Enter Your New Name: ");
                    String name = scanner.nextLine();
                    service.updateName(accNo, name);
                    break;
                }
                case 6: {
                    System.out.print("Please Enter Account No.: ");
                    long accNo = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Please Enter Your Password: ");
                    String password = scanner.nextLine();
                    if (!service.verifyPassword(accNo, password)) {
                        System.out.println("Wrong password. Access denied.");
                        break;
                    }
                    service.deleteAccount(accNo);
                    break;
                }
                case 7: {
                    // admin only
                    System.out.print("Enter Admin Password: ");
                    scanner.nextLine();
                    String adminPass = scanner.nextLine();
                    if (!adminPass.equals("PathakjiTheGoat")) {
                        System.out.println("Wrong admin password.");
                        break;
                    }
                    service.displayAll();
                    break;
                }
                case 8: {
                    System.out.print("Please Enter Account No: ");
                    long accNo = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Please Enter Your Password: ");
                    String password = scanner.nextLine();
                    if (!service.verifyPassword(accNo, password)) {
                        System.out.println("Wrong password. Access denied.");
                        break;
                    }
                    service.displayTransactionHistory(accNo);
                    break;
                }
                case 9: {
                    System.out.print("Please Enter Account No: ");
                    long accNo = scanner.nextLong();
                    scanner.nextLine();
                    Accounts a = service.findAccount(accNo);
                    if (a == null) {
                        System.out.println("Account Not Found.");
                        break;
                    }
                    System.out.println("Select Interest Type:");
                    System.out.println("1. Simple Interest");
                    System.out.println("2. Compound Interest");
                    System.out.print("Choice: ");
                    int intChoice = scanner.nextInt();
                    System.out.print("Enter Time (in years): ");
                    float time = scanner.nextFloat();
                    System.out.print("Enter Rate of Interest (e.g., 5 for 5%): ");
                    float rate = scanner.nextFloat();
                    
                    if (intChoice == 1) {
                        float si = (a.getBalance() * rate * time) / 100;
                        System.out.println("Estimated Simple Interest: Rs. " + si);
                        System.out.println("Total Amount after " + time + " years: Rs. " + (a.getBalance() + si));
                    } else if (intChoice == 2) {
                        System.out.print("Compounding frequency per year (e.g., 1 for annually, 12 for monthly): ");
                        int n = scanner.nextInt();
                        double amount = a.getBalance() * Math.pow(1 + (rate / 100 / n), n * time);
                        System.out.println("Estimated Compound Interest: Rs. " + (float)(amount - a.getBalance()));
                        System.out.println("Total Amount after " + time + " years: Rs. " + (float)amount);
                    } else {
                        System.out.println("Invalid Choice.");
                    }
                    break;
                }
                case 10: {
                    System.out.println("Thank you for using Group 2 Bank Management System!");
                    return;
                }
                default: {
                    System.out.println("Invalid choice.");
                }
            }
        } while (choice != 10);
    }
}