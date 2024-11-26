package org.cs3343.safepaws.ui.account;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Admin;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.menu.AdminMenu;
import org.cs3343.safepaws.ui.menu.MemberMenu;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

/**
 * The Login class handles the login process for users. It extends the UI class
 * and provides functionality for user authentication.
 */
public class Login extends UI {

    /**
     * The constant NAME represents the name of the login screen.
     */
    private static final String NAME = "Log In";

    /**
     * Execute.
     *
     * @param session the session
     * @return the ui
     */

    @Override
    public UI execute(final Session session) {
        session.println("Enter your username:");
        String username = session.requestInput();

        session.println("Enter your password:");
        String password = session.requestInput();

        if (DbManager.authenticateUser(username, password)) {
            session.println("Log in successfully.");
            Account account = DbManager.selectAccount(username);
            if (account instanceof Member memberAccount) {
                session.setAccount(memberAccount);
                return new MemberMenu();
            } else if (account instanceof Admin adminAccount) {
                session.setAccount(adminAccount);
                return new AdminMenu();
            }
        } else {
            session.println("Your username or password is incorrect.");
        }

        return this.getReferrer();
    }

    /**
     * Instantiates a new login.
     *
     * @param pReferrer the referrer
     */
    public Login(final UI pReferrer) {
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
        return session.getAccount() == null;
    }

}
