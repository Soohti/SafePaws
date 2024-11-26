package org.cs3343.safepaws.ui.account;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.menu.MainMenu;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;

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
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    public UI execute(final Session session) throws IOException {
        session.setAccount(new Account());
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

    /**
     * Checks if is visible to.
     *
     * @param session the session
     * @return true, if is visible to
     */
    @Override
    public boolean isVisibleTo(final Session session) {
        return session.getAccount().getUsername() != null;
    }
}
