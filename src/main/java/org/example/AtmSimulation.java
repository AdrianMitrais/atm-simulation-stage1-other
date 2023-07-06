package org.example;

import org.example.model.Account;
import org.example.operations.account.AccountUtils;
import org.example.operations.menus.EntryMenu;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class AtmSimulation {
    public static void main(String[] args) {
        List<Account> accountList = AccountUtils.generateAccounts();
        EntryMenu.displayMenu(accountList);
    }
}