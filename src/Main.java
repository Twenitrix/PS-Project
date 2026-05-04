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
                    8. Exit
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
                    System.out.print("Please Enter Your Initial Balance: ");
                    float balance = scanner.nextFloat();
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
                    System.out.println("Thank you for using Group 2 Bank Management System!");
                    return;
                }
                default: {
                    System.out.println("Invalid choice.");
                }
            }
        } while (choice != 8);
    }
}