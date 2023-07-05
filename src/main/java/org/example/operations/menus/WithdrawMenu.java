package org.example.operations.menus;

import org.example.model.Account;
import org.example.operations.utils.InputValidationUtils;

import java.util.Scanner;

public class WithdrawMenu {
    private static final Integer TEN_QUIDS = 10;
    private static final Integer FIFTY_QUIDS = 50;
    private static final Integer HUNDRED_QUIDS = 100;
    public static void displayMenus(Scanner scanner, Account account) {
        String[] options = {"1. $10", "2. $50", "3. $100", "4. Other", "5. Back"};
        printMenus(options);
        String selectMenu = scanner.next();
        if (InputValidationUtils.isInputContainLettersAndSpecial(selectMenu)) {
            System.out.println("Invalid Input Detected");
        }
        switch (selectMenu) {
            case "1" -> {
                account.setBalance(calculateBalance(TEN_QUIDS, account.getBalance()));
                SummaryMenu.displayWithdrawalSummaryMenu(TEN_QUIDS, account, scanner);
            }
            case "2" -> {
                account.setBalance(calculateBalance(FIFTY_QUIDS, account.getBalance()));
                SummaryMenu.displayWithdrawalSummaryMenu(FIFTY_QUIDS, account, scanner);
            }
            case "3" -> {
                account.setBalance(calculateBalance(HUNDRED_QUIDS, account.getBalance()));
                SummaryMenu.displayWithdrawalSummaryMenu(HUNDRED_QUIDS, account, scanner);
            }
            case "4" -> otherWithdrawalMenus(account, scanner);
            case "5" -> MainMenu.displayMenus(account, scanner);
            default -> {
                if (InputValidationUtils.isInputContainLettersAndSpecial(selectMenu) ||
                        InputValidationUtils.isInputWithinMenuCount(options.length, selectMenu)) {
                    System.out.println("Invalid Input Detected");
                }
                displayMenus(scanner, account);
            }
        }

    }

    public static void printMenus(String[] options) {

        for(int index = 0; index < options.length; index++) {
            System.out.println(options[index]);
        }
    }

    public static Integer calculateBalance(Integer withdrawAmount, Integer balance) {
        return balance - withdrawAmount;
    }

    private static void otherWithdrawalMenus(Account account, Scanner scanner) {
        System.out.println("Other Withdraw");
        System.out.println("Enter amount to withdraw:");
        int withdrawAmount = scanner.nextInt();
        Integer accountBalance = account.getBalance();
        account.setBalance(calculateBalance(withdrawAmount, accountBalance));
        SummaryMenu.displayWithdrawalSummaryMenu(withdrawAmount, account, scanner);

    }
}
