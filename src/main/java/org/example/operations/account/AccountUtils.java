package org.example.operations.account;

import org.example.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountUtils {
    public static List<Account> generateAccounts() {
        Account accountOne = new Account();
        accountOne.setName("John Doe");
        accountOne.setPin("012108");
        accountOne.setBalance(100);
        accountOne.setAccountNumber("112233");

        Account accountTwo = new Account();
        accountTwo.setName("Jane Doe");
        accountTwo.setPin("932012");
        accountTwo.setBalance(30);
        accountTwo.setAccountNumber("112244");

        List<Account> accountList = new ArrayList<>();
        accountList.add(accountOne);
        accountList.add(accountTwo);
        return accountList;
    }
}
