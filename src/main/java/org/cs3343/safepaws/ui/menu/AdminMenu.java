package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.account.Logout;
import org.cs3343.safepaws.ui.adoption.AdminAddPet;
import org.cs3343.safepaws.ui.adoption.AdminCheckApplication;
import org.cs3343.safepaws.ui.adoption.AdminSeeAllApplication;
import org.cs3343.safepaws.ui.adoption.AdminViewDetailApplication;
import org.cs3343.safepaws.util.Session;

public class AdminMenu extends Menu {
    /**
     * The name of the menu.
     */
    private static final String NAME = "Admin Menu";
    /**
     * The description of the menu.
     */
    private static final String DESCRIPTION = "Welcome to SafePaws!";
    /**
     * The menu items.
     */
    private final UI[] menuItems = new UI[] {
            new Logout(this), //go to main menu
            new AdminAddPet(this),
            new AdminCheckApplication(this),
            new AdminSeeAllApplication(this),
            new AdminViewDetailApplication(this),
    };

    /**
     * Constructs a new MainMenu.
     */
    public AdminMenu(final UI referrer) {
        super(NAME, DESCRIPTION, null);
        setMenuItems(menuItems);
    }

    @Override
    public final boolean isVisibleTo(final Session session) {
        return session.getAccount() != null && "A".equals(session.getAccount().getRole());
    }
    
}

