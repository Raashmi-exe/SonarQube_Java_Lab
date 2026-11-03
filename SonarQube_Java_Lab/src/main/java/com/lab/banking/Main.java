package com.lab.banking;

public class Main {
    public static void main(String[] args) {
        BankingService service = new BankingService();

        BankAccount account = new BankAccount("Asha", 5000.00);
        service.addAccount(account);

        account.deposit(1500.00);
        account.withdraw(750.00);

        System.out.println("Account holder: " + account.getAccountHolder());
        System.out.println("Current balance: " + account.getBalance());
        System.out.println("Total accounts: " + service.getAccountCount());
    }
}
