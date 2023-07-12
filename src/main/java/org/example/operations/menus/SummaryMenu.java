package org.example.operations.menus;

import org.example.model.Account;
import org.example.model.TransactionHistory;
import org.example.operations.account.AccountUtils;
import org.example.operations.utils.ConstantsUtils;
import org.example.operations.utils.InputValidationUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        Integer destinationAccountNewBalance = destinationAccount.getBalance() + transferAmount;
        accountList.get(accountList.indexOf(destinationAccount)).setBalance(destinationAccountNewBalance);

        addOutgoingTransactionHistory(transferAmount, destinationAccount.getAccountNumber(), account, accountList);
        addIncomingTransactionHistory(transferAmount, account.getAccountNumber(), destinationAccount, accountList);

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

    private static void addOutgoingTransactionHistory(int transferAmount, String destination, Account originAccount, List<Account> accountList) {
        List<TransactionHistory> transactionHistories = originAccount.getTransactionHistory() == null
                || originAccount.getTransactionHistory().isEmpty() ? new ArrayList<>() : originAccount.getTransactionHistory();
        TransactionHistory newHistory = new TransactionHistory();

        int accountIndex = accountList.indexOf(originAccount);
        newHistory.setAmount("-" + transferAmount);
        newHistory.setTransactionDate(LocalDate.now().toString());
        newHistory.setTransactionType(ConstantsUtils.TRANSFER_OUTGOING);
        newHistory.setDestination(destination);
        transactionHistories.add(newHistory);
        accountList.get(accountIndex).setTransactionHistory(transactionHistories);
        originAccount.setTransactionHistory(transactionHistories);
    }

    private static void addIncomingTransactionHistory(int transferAmount, String origin, Account destinationAccount, List<Account> accountList) {
        List<TransactionHistory> transactionHistories = destinationAccount.getTransactionHistory() == null
                || destinationAccount.getTransactionHistory().isEmpty() ? new ArrayList<>() : destinationAccount.getTransactionHistory();
        TransactionHistory newHistory = new TransactionHistory();
        int accountIndex = accountList.indexOf(destinationAccount);
        newHistory.setAmount("+" + transferAmount);
        newHistory.setTransactionDate(LocalDate.now().toString());
        newHistory.setTransactionType(ConstantsUtils.TRANSFER_INCOMING);
        newHistory.setOrigin(origin);
        transactionHistories.add(newHistory);
        accountList.get(accountIndex).setTransactionHistory(transactionHistories);
        destinationAccount.setTransactionHistory(transactionHistories);
    }

}
