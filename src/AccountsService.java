import java.util.ArrayList;
import java.io.*;

public class AccountsService {
    private static final String FILE = "accounts.txt";
    private ArrayList<Accounts> accounts = new ArrayList<>();
    public AccountsService() {
        loadFromFile();
    }
    private void loadFromFile() {
        File f = new File(FILE);
        if (!f.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine())!=null) {
                accounts.add(Accounts.fromCsv(line));
            }
        } catch (IOException e) {
            System.out.println("Error Loading The File");
        }
    }
    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE, false))) {
            for (Accounts a : accounts) {
                pw.println(a.toCsv());
            }
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public void createAccount(long accNo, String name, float balance, String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        Accounts newAccount = new Accounts(accNo, name, balance, hashed);
        if (balance > 0) {
            newAccount.addTransaction("Initial Deposit: +" + balance);
        }
        accounts.add(newAccount);
        saveToFile();
    }

    public Accounts findAccount(long accNo) {
        for (Accounts a : accounts) {
            if (a.getAccNo() == accNo) return a;
        }
        return null;
    }

    public boolean checkAccount(long accNo) {
        for (Accounts a : accounts) {
            if (a.getAccNo() == accNo) return true;
        }
        return false;
    }

    public boolean deposit(long accNo, float amount) {
        Accounts a = findAccount(accNo);
        if (a == null) {System.out.println("Account Not Found"); return false;}
        a.setBalance(a.getBalance() + amount);
        a.addTransaction("Deposit: +" + amount);
        saveToFile();
        System.out.println("New Balance: "+a.getBalance());
        return true;
    }

    public boolean withdraw(long accNo, float amount) {
        Accounts a = findAccount(accNo);
        if (a == null) { System.out.println("Account Not Found"); return false;}
        if (amount > a.getBalance()) { System.out.println("Invalid Amount"); return false;}
        a.setBalance(a.getBalance() - amount);
        a.addTransaction("Withdrawal: -" + amount);
        saveToFile();
        System.out.println("New Balance: "+a.getBalance());
        return true;
    }
    public boolean deleteAccount(long accNo) {
        Accounts a = findAccount(accNo);
        if (a == null) { System.out.println("Account Not Found."); return false; }
        accounts.remove(a);
        saveToFile();
        System.out.println("Account Deleted.");
        return true;
    }
    public boolean updateName(long accNo, String newName) {
        Accounts a = findAccount(accNo);
        if (a == null) { System.out.println("Account Not Found"); return false;}
        a.setName(newName);
        saveToFile();
        System.out.println("Updated Name : "+a.getName());
        return true;
    }
    public void displayAll() {
        for (Accounts a : accounts) {
            System.out.println(a.getAccNo() + " | " + a.getName() + " | Rs." + a.getBalance());
        }
    }
    public boolean verifyPassword(long accNo, String password) {
        Accounts a = findAccount(accNo);
        if (a == null) return false;
        return BCrypt.checkpw(password, a.getPassword());
    }

    public void displayTransactionHistory(long accNo) {
        Accounts a = findAccount(accNo);
        if (a == null) {
            System.out.println("Account Not Found");
            return;
        }
        System.out.println("--- Transaction History for " + a.getName() + " ---");
        if (a.getTransactions().isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String t : a.getTransactions()) {
                System.out.println(t);
            }
        }
        System.out.println("----------------------------------------");
    }

}
