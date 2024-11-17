package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.account.Logout;
import org.cs3343.safepaws.ui.adoption.MemberSeeApplication;
import org.cs3343.safepaws.ui.adoption.SetProfile;
import org.cs3343.safepaws.ui.adoption.SubmitApplication;
import org.cs3343.safepaws.ui.adoption.ViewPets;
import org.cs3343.safepaws.util.Session;

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
            new Logout(this), //go to main menu
            new MemberSeeApplication(this),
            new SetProfile(this),
            new SubmitApplication(this),
            new ViewPets(this),
    };

    /**
     * Constructs a new MainMenu.
     */
    public MemberMenu() {     //check the kind of account
        super(NAME, DESCRIPTION, null);
        setMenuItems(menuItems);
    }

    @Override
    public final boolean isVisibleTo(final Session session) {
        return session.getAccount() != null && "M".equals(session.getAccount().getRole());
    }
    
}

