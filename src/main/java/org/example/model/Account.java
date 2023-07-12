package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String name;
    private String pin;
    private Integer balance;
    private String accountNumber;
    private List<TransactionHistory> transactionHistory;

    public Account(String name, String pin, Integer balance, String accountNumber) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }
}
