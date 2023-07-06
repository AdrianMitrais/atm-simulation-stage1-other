package org.example.operations.menus;

import org.example.model.Account;
import org.example.operations.utils.InputValidationUtils;

import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public static void displayMenus(Account account, Scanner scanner, List<Account> accountList) {
        String[] options = {"1. Withdraw", "2. Fund Transfer", "3. Exit"};
        Boolean loggedIn = account == null ? false : true;
        while(loggedIn) {
            greetings(account.getName());
            printMenus(options);
            String option = scanner.next();
            switch (option) {
                case "1" -> WithdrawMenu.displayMenus(scanner, account, accountList);
                case "2" -> FundTransferMenu.displayMenus(scanner, account, accountList);
                case "3" -> {
                    System.out.println("Thank you for banking with us");
                    loggedIn = false;
                }
                default -> {
                    if (InputValidationUtils.isInputContainLettersAndSpecial(option) ||
                    InputValidationUtils.isInputWithinMenuCount(options.length, option)) {
                        System.out.println("Invalid Input Detected");
                    }
                }
            }
        }
    }

    private static void printMenus(String[] options) {
        for(int index = 0; index < options.length; index++) {
            System.out.println(options[index]);
        }
        System.out.print("Choose Option: ");
    }

    private static void greetings(String name) {
        System.out.println("Welcome, " + name + "\n" +
                "Please Select a Menu:");
    }
}
