package org.cs3343.safepaws.ui.account;

import org.cs3343.safepaws.handler.CreateAccountHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;
import org.mindrot.jbcrypt.BCrypt;

/**
 * The CreateAccount class provides the
 * functionality to create a new user
 * account. It extends the UI class.
 */
public class CreateAccount extends UI {

    /**
     * The constant NAME representing the name
     * of this UI component.
     */
    private static final String NAME = "Create Account";

    /**
     * Execute.
     *
     * @param session the session
     * @return the ui
     */
    @Override
    public UI execute(final Session session) {
        CreateAccountHandler handler = new CreateAccountHandler();
        session.println("Enter a username "
                + "(length: 8-30, any character):");
        String username = session.requestInput();
        while (handler.isValidUsername(username) != 2) {
            if (handler.isValidUsername(username) == 0) {
                session.println("Your input username is invalid. "
                        + "Please enter again:");
            } else {
                session.println("Your input username has been used. "
                        + "Please enter again:");
            }
            username = session.requestInput();
        }

        session.println("Enter a password (length: 8-16, "
                + "any character):");
        String password = session.requestInput();
        while (!handler.isValidPassword(password)) {
            session.println("Your input password is invalid. "
                    + "Please enter again:");
            password = session.requestInput();
        }
        password = BCrypt.hashpw(password, BCrypt.gensalt());

        session.println("Enter your role "
                + "\"M\" for member), \"S\" for shelter:");
        String role = session.requestInput();
        while (!handler.isValidRole(role)) {
            session.println("Your input role is invalid. "
                    + "Please enter again:");
            role = session.requestInput();
        }

        try {
            handler.createAccount(username, password, role);
            session.println("Account created successfully.");
        } catch (Exception ex) {
            session.println("Error during creating account." + ex.getMessage());
        }

        return this.getReferrer();
    }

    /**
     * Instantiates a new creates the account.
     *
     * @param pReferrer the referrer
     */
    public CreateAccount(final UI pReferrer) {
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
