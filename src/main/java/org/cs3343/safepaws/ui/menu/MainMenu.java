package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.account.CreateAccount;
import org.cs3343.safepaws.ui.account.Login;

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
            new CreateAccount(this),
            new Login(this),
    };

    /**
     * Constructs a new MainMenu.
     */
    public MainMenu() {
        super(NAME, DESCRIPTION, null);
        setMenuItems(menuItems);
    }
}
