package org.cs3343.safepaws.util;

import org.cs3343.safepaws.action.Action;
import org.cs3343.safepaws.action.Session;
import org.cs3343.safepaws.action.menu.MainMenu;

import java.util.Scanner;

public class ActionExecutor {

    private static final ActionExecutor instance = new ActionExecutor();
    private final Scanner scanner = new Scanner(System.in);

    private ActionExecutor() {
    }

    public static ActionExecutor getInstance() {
        return instance;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void start() {
        Action action = new MainMenu();
        Session session = new Session();
        do {
//            System.out.print("\033[H\033[2J"); // Clear screen, should not be here
//            System.out.flush();
            action = action.execute(session);
        } while (action != null);
    }

    public void stop() {
        scanner.close();
        System.out.println("Thank you for using SafePaws. Goodbye!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignore
        }
    }
}
