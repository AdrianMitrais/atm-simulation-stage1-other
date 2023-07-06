package org.example.operations.menus;

import org.example.model.Account;
import org.example.operations.account.AccountUtils;
import org.example.operations.utils.InputValidationUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SummaryMenu {
    public static void displayWithdrawalSummaryMenu(Integer withdrawAmount, Account account, Scanner scanner, List<Account> accountList) {
        String[] options = {"1. Transaction", "2. Exit"};
        accountSummaryAfterWithdraw(withdrawAmount, account);
        accountSummaryMenu(options);
        String menuSelection = scanner.next();
        if(InputValidationUtils.isInputWithinMenuCount(options.length, menuSelection)) {
                System.out.println("Invalid Input Detected");
        } else {
            switch (menuSelection) {
                case "1":
                    MainMenu.displayMenus(account, scanner, accountList);
                    break;
                case "2":
                    System.out.println("Thank you for banking with us.");
                    EntryMenu.displayMenu(accountList);
                    break;
                default:
                    displayWithdrawalSummaryMenu(withdrawAmount, account, scanner, accountList);
            }

        }
    }
    public static void displayTransferSummaryMenu(Scanner scanner, Map<String, String> transferDetails,
                                                  Account account, List<Account> accountList) {
        int index = accountList.indexOf(account);
        Integer transferAmount = Integer.parseInt(transferDetails.get("transferAmount"));
        Integer finalBalance = account.getBalance() - transferAmount;
        account.setBalance(finalBalance);
        accountList.get(index).setBalance(finalBalance);
        transferDetails.put("balance", finalBalance.toString());

        Account destinationAccount = AccountUtils.getAccountByNumber(transferDetails.get("destinationNumber"), accountList);
        accountList.get(accountList.indexOf(destinationAccount)).setBalance(destinationAccount.getBalance() + transferAmount);

        String[] options = {"1. Transaction", "2. Exit"};
        accountSummaryAfterTransfer(scanner, transferDetails);
        accountSummaryMenu(options);
        String menuSelection = scanner.next();

        if(InputValidationUtils.isInputWithinMenuCount(options.length, menuSelection)) {
            System.out.println("Invalid Input Detected");
        } else {
            switch (menuSelection) {
                case "1":
                    MainMenu.displayMenus(account, scanner, accountList);
                    break;
                case "2":
                    System.out.println("Thank you for banking with us.");
                    EntryMenu.displayMenu(accountList);
                    break;
                default:
                    displayTransferSummaryMenu(scanner, transferDetails, account, accountList);
            }

        }
    }

    public static void accountSummaryAfterWithdraw(Integer withdrawAmount, Account account) {
        System.out.println("Summary \n" +
                "Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm a")) + "\n" +
                "Withdraw: $" + withdrawAmount.toString() + "\n" +
                "Balance: $" + account.getBalance().toString());
    }

    private static void accountSummaryAfterTransfer(Scanner scanner, Map<String, String> transferDetails) {
        String transferSummary = "Fund Transfer Summary \n" +
                "Destination Account : " + transferDetails.get("destinationNumber") + "\n" +
                "Transfer Amount     : " + transferDetails.get("transferAmount") + "\n" +
                "ReferenceNumber     : " + transferDetails.get("referenceNumber") + "\n" +
                "Balance             : " + transferDetails.get("balance") + "\n";
        System.out.println(transferSummary);
    }

    private static void accountSummaryMenu(String[] options) {
        for(int index = 0; index < options.length; index++) {
            System.out.println(options[index]);
        }
        System.out.print("Choose Option: ");
    }
}
