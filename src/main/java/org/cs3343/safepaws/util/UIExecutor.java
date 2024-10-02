package org.cs3343.safepaws.util;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.Session;
import org.cs3343.safepaws.ui.menu.MainMenu;

import java.util.Scanner;

public class UIExecutor {
    private static final UIExecutor instance = new UIExecutor();
    private final Scanner scanner = new Scanner(System.in);

    private UIExecutor() {
    }

    public static UIExecutor getInstance() {
        return instance;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void start() {
        UI ui = new MainMenu();
        Session session = new Session();
        do {
            ui = ui.getNextUI(session);
        } while (ui != null);
    }

    public void stop() {
        scanner.close();
        System.out.println("Thank you for using SafePaws. Goodbye!");
    }
}
