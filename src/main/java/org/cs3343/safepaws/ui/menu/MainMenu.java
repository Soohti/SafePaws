package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.TestDb;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

public class MainMenu extends Menu {
    /**
     * The name of the menu.
     */
    private static final String NAME = "Main Menu";
    /**
     * The description of the menu.
     */
    private static final String DESCRIPTION = "Welcome to SafePaws!";
    /**
     * The menu items.
     */
    private final UI[] menuItems = new UI[] {
            new TestMenu(this),
            new TestDb(this),
    };

    /**
     * Constructs a new MainMenu.
     */
    public MainMenu() {
        super(NAME, DESCRIPTION, null);
        setMenuItems(menuItems);
    }

    @Override
    public final boolean isVisibleTo(final Session session) {
        return true;
    }
}
