package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.account.Logout;
import org.cs3343.safepaws.ui.shelter.PlanRoute;
import org.cs3343.safepaws.ui.shelter.SetLocation;

public class ShelterMenu extends Menu {
    /**
     * The name of the menu.
     */
    private static final String NAME = "Shelter Menu";
    /**
     * The menu items.
     */
    private final UI[] menuItems = new UI[] {
            new Logout(this),
            new SetLocation(this),
            new PlanRoute(this)
    };

    /**
     * Constructs a new MainMenu.
     */
    public ShelterMenu() {
        super(NAME, null, null);
        setMenuItems(menuItems);
    }
}
