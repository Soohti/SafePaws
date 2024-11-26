package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.account.Logout;
import org.cs3343.safepaws.ui.adoption.MemberCheckApplication;
import org.cs3343.safepaws.ui.adoption.MemberSetProfile;
import org.cs3343.safepaws.ui.adoption.MemberSubmitApplication;
import org.cs3343.safepaws.ui.adoption.MemberViewPets;
import org.cs3343.safepaws.util.Session;

/**
 * Represents the member menu in the SafePaws application.
 */
public class MemberMenu extends Menu {
    /**
     * The name of the menu.
     */
    private static final String NAME = "Member Menu";
    /**
     * The description of the menu.
     */
    private static final String DESCRIPTION = "Welcome to SafePaws!";
    /**
     * The menu items.
     */
    private final UI[] menuItems = new UI[] {
            new Logout(this), // go to main menu
            new MemberCheckApplication(this),
            new MemberSetProfile(this),
            new MemberSubmitApplication(this),
            new MemberViewPets(this),
    };

    /**
     * Constructs a new MemberMenu.
     */
    public MemberMenu() {
        super(NAME, DESCRIPTION, null);
        setMenuItems(menuItems);
    }

    /**
     * Determines if the menu is visible to the given session.
     *
     * @param session the session to check visibility for
     * @return true if the menu is visible to the session, false otherwise
     */
    @Override
    public final boolean isVisibleTo(final Session session) {
        return session.getAccount().getUsername() != null
                && "M".equals(session.getAccount().getRole());
    }
}
