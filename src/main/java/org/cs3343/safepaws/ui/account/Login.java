package org.cs3343.safepaws.ui.account;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Admin;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.menu.AdminMenu;
import org.cs3343.safepaws.ui.menu.MemberMenu;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;
import java.sql.SQLException;

public class Login extends UI {
    private static final String NAME = "Log In";

    /**
     * Execute.
     *
     * @param session the session
     * @return the ui
     * @throws IOException Signals that an I/O exception has occurred.
     */

    @Override
    public UI execute(Session session) throws IOException {
        session.println("Enter your username:");
        String username = session.requestInput();

        session.println("Enter your password:");
        String password = session.requestInput();

        try {
            if (DbManager.authenticateUser(username, password)) {
                session.println("Log in successfully.");
                Account account = DbManager.selectAccount(username);
                if (account instanceof Member) {
                    Member memberAccount = (Member) account;
                    session.setAccount(memberAccount);
                } else if (account instanceof Admin) {
                    Admin adminAccount = (Admin) account;
                    session.setAccount(adminAccount);
                }
                if (session.getAccount() != null && "A".equals(
                        session.getAccount().getRole())) {
                    return new AdminMenu();
                } else if (session.getAccount() != null && "M".equals(
                        session.getAccount().getRole())) {
                    return new MemberMenu();
                }

            } else {
                session.println("Your username or password is incorrect.");
            }
        } catch (SQLException e) {
            session.println("Error creating account: " + e.getMessage());
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
