package com.lab.banking;

import java.util.ArrayList;
import java.util.List;

public class BankingService {
    private final List<BankAccount> accounts = new ArrayList<>();

    public void addAccount(BankAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        accounts.add(account);
    }

    public BankAccount findAccount(String accountHolder) {
        for (BankAccount account : accounts) {
            if (account.getAccountHolder().equalsIgnoreCase(accountHolder)) {
                return account;
            }
        }
        return null;
    }

    public int getAccountCount() {
        return accounts.size();
    }
}
