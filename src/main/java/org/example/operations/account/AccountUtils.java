package org.example.operations.account;

import org.example.model.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountUtils {
    private static final String DELIMITER_COMMA = ",";

    public static List<Account> generateAccounts() {
        List<Account> accountList = new ArrayList<>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/atm-simulation-sample.csv"));
            while ((line = reader.readLine()) != null) {
                String[] lineRead = line.split(DELIMITER_COMMA);
                //Order: name, pin, balance, accountNumber
                Account account = new Account(lineRead[0], lineRead[1], Integer.parseInt(lineRead[2]), lineRead[3]);
                accountList.add(account);
            }
        } catch (IOException e) {
            System.out.println("Something wrong with csv");
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid Balance Data Detected");
        }
        if (!validateAccounts(accountList)) {
            System.exit(0);
        }
        return accountList;
    }

    private static boolean validateAccounts(List<Account> accountList) {
        List<String> duplicateAccountNumber = new ArrayList<>();
        List<Account> duplicateAccount = new ArrayList<>();
        for (int index = 0; index < accountList.size(); index++) {
            Account currentAccount = accountList.get(index);
            long countByAccountNumber = accountList.stream()
                    .filter(account -> account.getAccountNumber().equals(currentAccount.getAccountNumber())).count();
            long countDuplicateRecord = accountList.stream()
                    .filter((account -> account.equals(currentAccount))).count();
            if (countDuplicateRecord > 1 && (!duplicateAccount.contains(currentAccount))) {
                    duplicateAccount.add(currentAccount);
            }
            if (countByAccountNumber > 1 && (!duplicateAccountNumber.contains(currentAccount.getAccountNumber()))) {
                    duplicateAccountNumber.add(currentAccount.getAccountNumber());
            }
        }
        boolean validationResult = true;

        if (duplicateAccount.size() > 0) {
            System.out.println("There can't be duplicated records: "
                    + duplicateAccount.get(0));
            validationResult = false;
            return validationResult;
        } else if (duplicateAccountNumber.size() > 0) {
            System.out.println("There can't be 2 different accounts with the same Account Number: "
                    + duplicateAccountNumber.get(0));
            validationResult = false;
            return validationResult;
        }
        return validationResult;
    }

    public static Account doesAccountExists(String accountNumber, String pin, List<Account> accountList) {
        return accountList.stream().filter(account -> accountNumber.equals(account.getAccountNumber()) &&
                pin.equals(account.getPin())).findAny().orElse(null);
    }

    public static boolean doesAccountExistsByNumber(String accountNumber, List<Account> accountList) {
        return accountList.stream().anyMatch(account -> accountNumber.equals(account.getAccountNumber()));
    }

    public static Account getAccountByNumber(String accountNumber, List<Account> accountList) {
        return accountList.stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber()))
                .findAny().orElse(null);
    }
}
