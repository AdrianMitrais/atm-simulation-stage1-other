package org.example.operations.menus;

import org.example.model.Account;
import org.example.operations.account.AccountUtils;
import org.example.operations.utils.InputValidationUtils;

import java.util.*;

public class FundTransferMenu {

    private static final String DESTINATION_PROMPT = "Please enter destination account and \n" +
            "press Enter to continue: ";
    private static final String TRANSFER_AMOUNT_PROMPT = "Please enter transfer amount and \n" +
            "press Enter to continue: ";
    private static final Integer TRANSFER_LIMIT = 1000;
    private static final Integer TRANSFER_MIN = 1;

    public static void displayMenus(Scanner scanner, Account account, List<Account> accountList) {
        System.out.println(DESTINATION_PROMPT);
        String destinationNumber = scanner.next();

        System.out.println(TRANSFER_AMOUNT_PROMPT);
        int transferAmount = scanner.nextInt();

        Random random = new Random();
        int randomNumber = random.nextInt(1,999999);
        String referenceNumber = String.format("%06d", randomNumber);
        String referenceNumberPrompt = "Reference Number: " + referenceNumber + "\n" +
                "Press Enter to continue";
        System.out.println(referenceNumberPrompt);

        Map<String, String> transferDetails = new HashMap<>();
        transferDetails.put("destinationNumber", destinationNumber);
        transferDetails.put("transferAmount", Integer.toString(transferAmount));
        transferDetails.put("referenceNumber", referenceNumber);
        displayTransferConfirmationMenu(scanner, transferDetails, account);
        String[] options = {"1. Confirm Transfer", "2. Cancel Transfer"};
        printMenus(options);
        String optionSelect = scanner.next();
        switch (optionSelect) {
            case "1":
                if (isTransactionValid(AccountUtils.generateAccounts(), account, transferDetails)) {
                    SummaryMenu.displayTransferSummaryMenu(scanner, transferDetails, account, accountList);
                } else {
                    MainMenu.displayMenus(account, scanner, accountList);
                    break;
                }
                break;

            case "2":
                MainMenu.displayMenus(account, scanner, accountList);
                break;
            default:
                if (InputValidationUtils.isInputContainLettersAndSpecial(optionSelect) ||
                        InputValidationUtils.isInputWithinMenuCount(options.length, optionSelect)) {
                    System.out.println("Invalid Input Detected");
                }
                displayMenus(scanner, account, accountList);
        }

    }

    private static boolean isTransactionValid(List<Account> accounts, Account account, Map<String, String> transferDetails) {
        boolean accountExists = AccountUtils.doesAccountExistsByNumber(transferDetails.get("destinationNumber"), accounts);
        if (InputValidationUtils.isInputContainLettersAndSpecial(transferDetails.get("destinationNumber"))
                || !accountExists || transferDetails.get("destinationNumber").equals(account.getAccountNumber())) {
            System.out.println("Invalid account");
            return false;
        }

        if (Integer.parseInt(transferDetails.get("transferAmount")) > TRANSFER_LIMIT) {
            System.out.println("Maximum amount to transfer is $1000");
            return false;
        }

        if (Integer.parseInt(transferDetails.get("transferAmount")) < TRANSFER_MIN) {
            System.out.println("Minimum amount to transfer is $1");
            return false;
        }

        if (InputValidationUtils.isInputContainLettersAndSpecial(transferDetails.get("transferAmount"))) {
            System.out.println("Invalid amount");
            return false;
        }

        if (account.getBalance() < Integer.parseInt(transferDetails.get("transferAmount"))) {
            System.out.println("Insufficient Balance");
            return false;
        }

        if (transferDetails.get("referenceNumber") == null
                && InputValidationUtils.isInputContainLettersAndSpecial(transferDetails.get("referenceNumber"))) {
            System.out.println("Invalid Reference Number");
            return false;
        }

        return true;
    }

    private static void printMenus(String[] options) {
        for(int index = 0; index < options.length; index++) {
            System.out.println(options[index]);
        }
        System.out.print("Choose Option: ");
    }

    private static void displayTransferConfirmationMenu(Scanner scanner, Map<String, String> transferDetails, Account account) {
        String transferConfirmation = "Transfer Confirmation \n" +
                "Destination Account : " + transferDetails.get("destinationNumber") + "\n" +
                "Transfer Amount     : " + transferDetails.get("transferAmount") + "\n" +
                "ReferenceNumber     : " + transferDetails.get("referenceNumber") + "\n";
        System.out.println(transferConfirmation);
    }




}
