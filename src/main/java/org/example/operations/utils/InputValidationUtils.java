package org.example.operations.utils;

import org.example.model.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputValidationUtils {
    private static final int VALID_INPUT_LENGTH = 6;
    public static boolean isInputContainLettersAndSpecial(String input) {
        if (input != null) {
            try {
                int integer = Integer.parseInt(input);
                return false;
            } catch (NumberFormatException e) {
                return true;
            }
        } else {
            return true;
        }
    }


    public static Account doesAccountExists(String accountNumber, String pin, List<Account> accountList) {
        return accountList.stream().filter(account -> accountNumber.equals(account.getAccountNumber()) &&
                pin.equals(account.getPin())).findAny().orElse(null);
    }

    public static boolean isInputLengthValid(String input) {
        return input.length() != VALID_INPUT_LENGTH;
    }

    public static boolean isInputWithinMenuCount(int optionCount, String input) {
        if (!isInputContainLettersAndSpecial(input)) {
            if (optionCount < Integer.parseInt(input) || Integer.parseInt(input) == 0) {
                return true;
            }
        }
        return false;
    }

}
