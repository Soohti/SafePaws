package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.account.Logout;
import org.cs3343.safepaws.ui.member.CheckApplication;
import org.cs3343.safepaws.ui.member.SetProfile;
import org.cs3343.safepaws.ui.member.SubmitApplication;
import org.cs3343.safepaws.ui.member.ViewPets;

/**
 * Represents the member menu in the SafePaws application.
 */
public class MemberMenu extends Menu {
    /**
     * The name of the menu.
     */
    private static final String NAME = "Member Menu";
    /**
     * The menu items.
     */
    private final UI[] menuItems = new UI[] {
            new Logout(this), // go to main menu
            new CheckApplication(this),
            new SetProfile(this),
            new SubmitApplication(this),
            new ViewPets(this),
    };

    /**
     * Constructs a new MemberMenu.
     */
    public MemberMenu() {
        super(NAME, null, null);
        setMenuItems(menuItems);
    }
}
