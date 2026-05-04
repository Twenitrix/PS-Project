public class Accounts {
    private long accNo;
    private String name;
    private float balance;
    private String password;
    public Accounts(long accNo, String name, float balance, String password) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
        this.password = password;
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

    public String toCsv() {
        return accNo+","+ name + ","+balance+","+password;
    }

    public static Accounts fromCsv(String line) {
        String[] parts = line.split(",", 4);
        return new Accounts(
                Long.parseLong(parts[0].trim()),
                parts[1].trim(),
                Float.parseFloat(parts[2].trim()),
                parts[3].trim()
        );
    }
}
