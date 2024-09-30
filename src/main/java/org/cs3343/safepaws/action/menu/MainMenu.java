package org.cs3343.safepaws.action.menu;

import org.cs3343.safepaws.action.Action;
import org.cs3343.safepaws.action.Session;

public class MainMenu extends Menu {
    private static final String name = "Main Menu";
    private static final String title = "SafePaws - Main Menu";
    private static final String description = "Welcome to SafePaws!";
    private final Action[] actions = new Action[]{
            new TestMenu(this),
    };

    public MainMenu() {
        super(name, title, description);
        this.setActions(actions);
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return true;
    }
}
