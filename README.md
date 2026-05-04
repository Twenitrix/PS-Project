# Group 2 Bank Management System 🏦

A robust, console-based Bank Management System built in Java. This application simulates real-world banking operations, providing a secure and interactive command-line interface for users to manage their finances, calculate interest, and track transaction histories.

## ✨ Features

- **Account Management**: Create, update, delete, and view bank accounts.
- **Secure Authentication**: Passwords are cryptographically hashed using **BCrypt**, ensuring user data remains secure even in the local database.
- **Transactions**: Securely deposit and withdraw funds with built-in validation to prevent overdrafts.
- **Initial Balance Validation**: Enforces a strict positive initial balance requirement during account creation.
- **Transaction History**: Automatically tracks and stores a detailed log of all account activities (Initial deposits, deposits, and withdrawals).
- **Interest Calculator**: Built-in financial tools to estimate future balances using either **Simple Interest** or **Compound Interest** formulas.
- **Data Persistence**: Uses flat-file storage (`accounts.txt`) with a custom CSV serialization format, meaning your data is saved between application restarts.
- **Admin Panel**: An exclusive admin view protected by a master password to display all registered accounts within the system.

## 🛠️ Technology Stack

- **Language**: Java
- **Security**: jBCrypt (for password hashing)
- **Storage**: Local File System (`.txt` / Custom CSV format)

## 🚀 How to Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Twenitrix/PS-Project.git
   cd PS-Project
   ```

2. **Compile the Code**:
   Navigate to the `src` directory and compile the Java files:
   ```bash
   cd src
   javac Main.java Accounts.java AccountsService.java BCrypt.java
   ```

3. **Run the Application**:
   Execute the compiled `Main` class:
   ```bash
   java Main
   ```

## 📋 Menu Options

When you launch the application, you will be greeted with the following interactive menu:

1. **Create Account**: Open a new bank account with an initial deposit and a secure password.
2. **Deposit**: Add funds to your account (requires password).
3. **Withdraw**: Remove funds from your account safely (requires password).
4. **Display Account**: View your current balance and account details (requires password).
5. **Update Name**: Change the registered name on your account.
6. **Delete Account**: Permanently remove your account from the system.
7. **Display All Accounts (Admin)**: View a summary of all accounts in the database (requires Admin password).
8. **Transaction History**: View a chronological log of all your deposits and withdrawals.
9. **Interest Calculator**: Estimate your future wealth using your current balance with Simple or Compound interest.
10. **Exit**: Save all data and safely terminate the application.

## 🔒 Security Note
This project was built for educational purposes. Passwords are securely hashed via BCrypt before being saved to `accounts.txt`. However, the Admin password currently resides in the source code, which is standard for demonstration but should be migrated to environment variables for production environments.

---
*Developed by Group 2*
