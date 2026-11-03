package com.lab.banking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    @Test
    void depositIncreasesBalance() {
        BankAccount account = new BankAccount("Asha", 1000.0);
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance());
    }

    @Test
    void withdrawalDecreasesBalance() {
        BankAccount account = new BankAccount("Ravi", 1000.0);
        account.withdraw(250.0);
        assertEquals(750.0, account.getBalance());
    }

    @Test
    void withdrawalAboveBalanceIsRejected() {
        BankAccount account = new BankAccount("Maya", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(200.0));
    }
}
