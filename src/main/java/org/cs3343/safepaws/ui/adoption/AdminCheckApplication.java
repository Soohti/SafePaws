package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;

/**
 * The AdminCheckApplication class allows an admin
 * to check and update the state
 * of an adoption application.
 */
public class AdminCheckApplication extends UI {
    // A pet can only be assigned to one member
    // If approved, pet should be erased
    // All applications for this pet should be rejected

    /**
     * The name of the UI for checking one application for adoption.
     */
    private static final String NAME =
            "Check one " + "application for adoption";

    /**
     * Constructs an AdminCheckApplication instance.
     *
     * @param referrer the UI that referred to this instance
     */
    public AdminCheckApplication(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the admin check application process.
     *
     * @param session the current session
     * @return the referrer UI
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected UI execute(final Session session) throws IOException {
        session.println("Enter the id of application you want "
                + "to check that has not been checked.:");
        int aid = Integer.parseInt(session.requestInput());
        while (!Application.isValidAid(aid)) {
            session.println("Your input application id is invalid. "
                    + "Please enter again:");
            aid = Integer.parseInt(session.requestInput());
        }

        session.println("Enter what state you want to set "
                + "(1: approve; 2: reject):");
        int state = Integer.parseInt(session.requestInput());
        while (!Application.isValidState(state)) {
            session.println("Your input state is invalid. "
                    + "Please enter again:");
            state = Integer.parseInt(session.requestInput());
        }

        DbManager.changeState(aid, state);

        return this.getReferrer();
    }

    /**
     * Determines if the UI is visible to the current session.
     *
     * @param session the current session
     * @return true if the session's account is not null and has the role 'A',
     * false otherwise
     */
    @Override
    public boolean isVisibleTo(final Session session) {
        return session.getAccount() != null
                && "A".equals(session.getAccount().getRole());
    }
}
