package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.Exit;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;
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
            session.out.println(description);
        }
        for (int i = 0; i < menuItems.length; i++) {
            if (menuItems[i].isVisibleTo(session)) {
                session.out.println((i + 1) + ". " + menuItems[i].getName());
            }
        }
        if (this.referrer != null) {
            session.out.println("B. Back");
        }
        session.out.println("E. Exit");
        session.out.print("Please enter your choice: ");
//        Scanner scanner = UIExecutor.getInstance().getScanner();

        do {
            String choice = session.requestInput();
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
                        session.out.print("Invalid choice, please try again: ");
                    }
                } catch (NumberFormatException e) {
                    session.out.print("Invalid choice, please try again: ");
                }
            }
        } while (true);
    }
}
