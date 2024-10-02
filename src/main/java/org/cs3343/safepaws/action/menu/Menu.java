package org.cs3343.safepaws.action.menu;

import org.cs3343.safepaws.action.Action;
import org.cs3343.safepaws.action.Session;
import org.cs3343.safepaws.util.ActionExecutor;

import java.util.Scanner;

public abstract class Menu extends Action {
    private final String title;
    private final String description;
    private Action[] actions;

    public Menu(String name, String title, String description, Action referrer) {
        super(name, referrer);
        this.title = title;
        this.description = description;
    }

    public void setActions(Action[] actions) {
        this.actions = actions;
    }

    @Override
    public final Action getNextAction(Session session) {
        return execute(session);
    }

    @Override
    protected final Action execute(Session session) {
        if (title != null) {
            System.out.println("=== " + title + " ===");
        }
        if (description != null) {
            System.out.println(description);
        }
        for (int i = 0; i < actions.length; i++) {
            if (actions[i].isVisibleTo(session)) {
                System.out.println((i + 1) + ". " + actions[i].getName());
            }
        }
        if (this.referrer != null) {
            System.out.println("B. Back");
        }
        System.out.println("E. Exit");
        System.out.print("Please enter your choice: ");
        Scanner scanner = ActionExecutor.getInstance().getScanner();

        do {
            String choice = scanner.next();
            if ("E".equals(choice)) {
                return null;
            } else if (this.referrer != null && "B".equals(choice)) {
                return this.referrer;
            } else {
                try {
                    int choiceInt = Integer.parseInt(choice);
                    if (choiceInt > 0 && choiceInt <= actions.length && actions[choiceInt - 1].isVisibleTo(session)) {
                        return actions[choiceInt - 1];
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
