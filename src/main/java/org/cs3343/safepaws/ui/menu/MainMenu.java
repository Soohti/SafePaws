package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.Session;
import org.cs3343.safepaws.ui.TestDb;

public class MainMenu extends Menu {
    private static final String name = "Main Menu";
    private static final String title = "SafePaws - Main Menu";
    private static final String description = "Welcome to SafePaws!";

    private final UI[] UIs = new UI[]{
            new TestMenu(this),
            new TestDb(this)
    };

    public MainMenu() {
        super(name, title, description, null);
        this.setUIs(UIs);
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return true;
    }
}
