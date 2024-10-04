package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.Exit;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.Session;
import org.cs3343.safepaws.util.UIExecutor;

import java.util.Scanner;

public abstract class Menu extends UI {
    private final String description;
    private UI[] menuItems;

    public Menu(String name, String description, UI referrer) {
        super(name, referrer);
        this.description = description;
    }

    protected void setMenuItems(UI[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    protected final UI execute(Session session) {
        if (description != null) {
            System.out.println(description);
        }
        for (int i = 0; i < menuItems.length; i++) {
            if (menuItems[i].isVisibleTo(session)) {
                System.out.println((i + 1) + ". " + menuItems[i].getName());
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
                return new Exit(this);
            } else if (this.referrer != null && "B".equals(choice)) {
                return this.referrer;
            } else {
                try {
                    int choiceInt = Integer.parseInt(choice);
                    if (choiceInt > 0 && choiceInt <= menuItems.length && menuItems[choiceInt - 1].isVisibleTo(session)) {
                        return menuItems[choiceInt - 1];
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
