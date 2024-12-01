package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.account.Logout;
import org.cs3343.safepaws.ui.admin.AddPet;
import org.cs3343.safepaws.ui.admin.ReviewApplication;
import org.cs3343.safepaws.ui.admin.SuggestShelter;

public class AdminMenu extends Menu {
    /**
     * The name of the menu.
     */
    private static final String NAME = "Admin Menu";
    /**
     * The menu items.
     */
    private final UI[] menuItems = new UI[] {
            new Logout(this),
            new AddPet(this),
            new ReviewApplication(this),
            new SuggestShelter(this)
    };

    /**
     * Constructs a new MainMenu.
     */
    public AdminMenu() {
        super(NAME, null, null);
        setMenuItems(menuItems);
    }
}
