package org.example.operations.menus;

import java.util.Scanner;

public class DisplayMenu {
    public static void mainMenu(String name, Boolean loggedIn, Scanner scanner) {
        Integer option;
        if(loggedIn) {
            while(loggedIn) {
                try {
                    greetings(name);
                    printMainMenus();
                    option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            System.out.println("A");
                            break;
                        case 2:
                            System.out.println("B");
                            break;
                        case 3:
                            System.out.println("Thank you for banking with us");
                            loggedIn = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void printMainMenus() {
        String[] options = {"1. Withdraw", "2. Fund Transfer", "3. Exit"};
        for(int index = 0; index < options.length; index++) {
            System.out.println(options[index]);
        }
    }

    public static void greetings(String name) {
        System.out.println("Welcome, " + name);
    }
}
