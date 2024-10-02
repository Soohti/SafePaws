package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.Session;
import org.cs3343.safepaws.util.UIExecutor;

import java.util.Scanner;

public abstract class Menu extends UI {
    private final String title;
    private final String description;
    private UI[] UIs;

    public Menu(String name, String title, String description, UI referrer) {
        super(name, referrer);
        this.title = title;
        this.description = description;
    }

    public void setUIs(UI[] UIs) {
        this.UIs = UIs;
    }

    @Override
    public final UI getNextUI(Session session) {
        return execute(session);
    }

    @Override
    protected final UI execute(Session session) {
        if (title != null) {
            System.out.println("=== " + title + " ===");
        }
        if (description != null) {
            System.out.println(description);
        }
        for (int i = 0; i < UIs.length; i++) {
            if (UIs[i].isVisibleTo(session)) {
                System.out.println((i + 1) + ". " + UIs[i].getName());
            }
        }
        if (this.referrer != null) {
            System.out.println("B. Back");
        }
        System.out.println("E. Exit");
        System.out.print("Please enter your choice: ");
        Scanner scanner = UIExecutor.getInstance().getScanner();

        do {
            String choice = scanner.next();
            if ("E".equals(choice)) {
                return null;
            } else if (this.referrer != null && "B".equals(choice)) {
                return this.referrer;
            } else {
                try {
                    int choiceInt = Integer.parseInt(choice);
                    if (choiceInt > 0 && choiceInt <= UIs.length && UIs[choiceInt - 1].isVisibleTo(session)) {
                        return UIs[choiceInt - 1];
                    } else {
                        System.out.print("Invalid choice, please try again: ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice, please try again: ");
                }
            }
        } while (true);
    }
}
