package org.example.operations.menus;

import org.example.model.Account;
import org.example.operations.account.AccountUtils;

import java.util.List;
import java.util.Scanner;

public class EntryMenu {
    public static void entryMenu() {
        List<Account> accountList = AccountUtils.generateAccounts();
        Scanner scanner = new Scanner(System.in);
        int falseCount = 0;
        while(falseCount < 3) {
            System.out.println("Enter Account Number");
            String accountNumber = scanner.next();
            //TODO: if account number is not found, falseCount++, else PIN entry. If PIN wrong, falseCount++
        }

    }

    public static void greetings() {
        System.out.println("Welcome to Mitrais Bank");
    }
}
