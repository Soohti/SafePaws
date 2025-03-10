package org.cs3343.safepaws.ui.account;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.menu.MainMenu;
import org.cs3343.safepaws.util.Session;

/**
 * The Logout class handles the user logout functionality. It extends the UI
 * class and provides methods to execute the logout process and determine the
 * visibility of the logout option based on the session state.
 */
public class Logout extends UI {

    /**
     * The constant NAME represents the name of the logout action.
     */
    private static final String NAME = "Log Out";

    /**
     * Execute.
     *
     * @param session the session
     * @return the ui
     */
    @Override
    public UI execute(final Session session) {
        session.setAccount(null);
        session.println("Bye bye.");
        return new MainMenu();
    }


    /**
     * Instantiates a new logout.
     *
     * @param pReferrer the referrer
     */
    public Logout(final UI pReferrer) {
        super(NAME, pReferrer);
    }
}
