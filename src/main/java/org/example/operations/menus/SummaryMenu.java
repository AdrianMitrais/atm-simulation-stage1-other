package org.example.operations.menus;

import org.example.model.Account;
import org.example.operations.utils.InputValidationUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Scanner;

public class SummaryMenu {
    public static void displayWithdrawalSummaryMenu(Integer withdrawAmount, Account account, Scanner scanner) {
        String[] options = {"1. Transaction", "2. Exit"};
        accountSummaryAfterWithdraw(withdrawAmount, account);
        accountSummaryMenu(options);
        String menuSelection = scanner.next();
        if(InputValidationUtils.isInputContainLettersAndSpecial(menuSelection)) {
                System.out.println("Invalid Input Detected");
        } else {
            switch (menuSelection) {
                case "1":
                    MainMenu.displayMenus(account, scanner);
                    break;
                case "2":
                    System.out.println("Thank you for banking with us.");
                    System.exit(0);
                    break;
            }

        }
    }
    public static void displayTransferSummaryMenu(Integer transferAmount, Account account){

    }

    public static void accountSummaryAfterWithdraw(Integer withdrawAmount, Account account) {
        System.out.println("Summary \n" +
                "Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm a")) + "\n" +
                "Withdraw: $" + withdrawAmount.toString() + "\n" +
                "Balance: $" + account.getBalance().toString());
    }

    private static void accountSummaryMenu(String[] options) {
        for(int index = 0; index < options.length; index++) {
            System.out.println(options[index]);
        }
    }
}
