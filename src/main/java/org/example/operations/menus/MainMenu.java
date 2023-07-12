package org.example.operations.menus;

import org.example.model.Account;
import org.example.model.TransactionHistory;
import org.example.operations.utils.ConstantsUtils;
import org.example.operations.utils.InputValidationUtils;

import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public static void displayMenus(Account account, Scanner scanner, List<Account> accountList) {
        String[] options = {"1. Withdraw", "2. Fund Transfer", "3. Transaction History", "4. Exit"};
        Boolean loggedIn = account == null ? false : true;
        while(loggedIn) {
            greetings(account.getName());
            printMenus(options);
            String option = scanner.next();
            switch (option) {
                case "1" -> WithdrawMenu.displayMenus(scanner, account, accountList);
                case "2" -> FundTransferMenu.displayMenus(scanner, account, accountList);
                case "3" -> {
                    if (account.getTransactionHistory() == null || account.getTransactionHistory().isEmpty()) {
                        System.out.println("No transaction found");
                    } else {
                        transactionsDetailFormat(account.getTransactionHistory());
                    }
                    System.out.println("Press Enter to Continue");
                    String readLine = scanner.nextLine();
                    if(!scanner.hasNextLine()) {
                        readLine = null;
                    }
                }
                case "4" -> {
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

    private static void transactionsDetailFormat(List<TransactionHistory> transactionHistories) {
        int startingIndex = 0;
        if (transactionHistories.size() > ConstantsUtils.TEN_RECORDS_ONLY) {
            startingIndex = transactionHistories.size() - ConstantsUtils.TEN_RECORDS_ONLY - 1;
        }
        for (int index = startingIndex; index < transactionHistories.size(); index++) {
            TransactionHistory history = transactionHistories.get(index);
            if (history.getTransactionType().equals(ConstantsUtils.TRANSFER_OUTGOING)
                && history.getDestination() != null) {
                System.out.println(index + "\n" +
                        "Transaction Type:"+ history.getTransactionType() + "\n" +
                        "Transaction Date: " + history.getTransactionDate() +"\n" +
                        "Destination:" + history.getDestination() + "\n" +
                        "Amount: " + history.getAmount());
            } else if (history.getTransactionType().equals(ConstantsUtils.TRANSFER_INCOMING)
                && history.getOrigin() != null) {
                System.out.println(index + "\n" +
                        "Transaction Type:"+ history.getTransactionType() + "\n" +
                        "Transaction Date: " + history.getTransactionDate() +"\n" +
                        "Origin:" + history.getOrigin() + "\n" +
                        "Amount: " + history.getAmount());
            } else if (history.getTransactionType().equals(ConstantsUtils.WITHDRAWAL)) {
                System.out.println(index + "\n" +
                        "Transaction Type:"+ history.getTransactionType() + "\n" +
                        "Transaction Date: " + history.getTransactionDate() +"\n" +
                        "Amount: " + history.getAmount());
            }
        }
    }
}
