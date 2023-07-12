package org.example.operations.menus;

import org.example.model.Account;
import org.example.operations.account.AccountUtils;
import org.example.operations.utils.InputValidationUtils;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Scanner;

public class EntryMenu {
    private static final String ACCOUNT_NUMBER = "Account Number";
    private static final String PIN = "PIN";

    public static void displayMenu(List<Account> accountList) {
        greetings();
        String accountNumber;
        String pin;
        Scanner scanner = new Scanner(System.in);
        int falseCount = 0;
        Boolean operationFinished = false;
        while(!operationFinished) {
            System.out.println("Enter Account Number");
            accountNumber = scanner.next();
            if("exit".equalsIgnoreCase(accountNumber)) {
                operationFinished = true;
            } else {
                if (validateInput(accountNumber, ACCOUNT_NUMBER)) {
                    System.out.println("Enter PIN");
                    pin = scanner.next();
                    if (validateInput(pin, PIN)) {
                        Account accountMatch = AccountUtils.doesAccountExists(accountNumber, pin, accountList);
                        if(accountMatch != null) {
                            MainMenu.displayMenus(accountMatch, scanner, accountList);
                        } else {
                            System.out.println("Invalid Account Number/PIN");
                        }
                    } else if ("exit".equalsIgnoreCase(accountNumber)) {
                        operationFinished = true;
                    }
                }
            }

        }

        scanner.close();
    }

    public static void greetings() {
        System.out.println("Welcome to Mitrais Bank");
    }

    public static boolean validateInput(String input, String source) {
        if (InputValidationUtils.isInputLengthValid(input)) {
            System.out.println(source + " should have 6 digits length");
            return false;
        }

        if (InputValidationUtils.isInputContainLettersAndSpecial(input)) {
            System.out.println(source + " should only contains numbers");
            return false;
        }

        return true;
    }
}
