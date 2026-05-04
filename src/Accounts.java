public class Accounts {
    private long accNo;
    private String name;
    private float balance;
    private String password;
    private java.util.ArrayList<String> transactions;
    public Accounts(long accNo, String name, float balance, String password) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
        this.password = password;
        this.transactions = new java.util.ArrayList<>();
    }

    public long getAccNo() {
        return accNo;
    }

    public float getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void addTransaction(String transaction) {
        this.transactions.add(transaction);
    }

    public java.util.ArrayList<String> getTransactions() {
        return transactions;
    }

    public String toCsv() {
        String txnStr = String.join("|", transactions);
        return accNo+","+ name + ","+balance+","+password+","+txnStr;
    }

    public static Accounts fromCsv(String line) {
        String[] parts = line.split(",", 5);
        Accounts acc = new Accounts(
                Long.parseLong(parts[0].trim()),
                parts[1].trim(),
                Float.parseFloat(parts[2].trim()),
                parts[3].trim()
        );
        if (parts.length > 4 && !parts[4].trim().isEmpty()) {
            String[] txns = parts[4].split("\\|");
            for (String t : txns) {
                acc.addTransaction(t);
            }
        }
        return acc;
    }
}
