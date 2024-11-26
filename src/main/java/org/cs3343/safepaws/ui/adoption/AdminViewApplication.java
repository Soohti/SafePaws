package org.cs3343.safepaws.ui.adoption;

import java.io.IOException;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

/**
 * The AdminViewApplication class allows an admin
 * to view and update the state
 * of an adoption application.
 */
public class AdminViewApplication extends UI {
    /**
     * The name of the UI for checking one application for adoption.
     */
    private static final String NAME =
            "View all " + "applications for adoption";

    /**
     * Constructs an AdminViewApplication instance.
     *
     * @param referrer the UI that referred to this instance
     */
    public AdminViewApplication(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the admin view application process.
     *
     * @param session the current session
     * @return the referrer UI
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected UI execute(final Session session) throws IOException {
        ShowAllApplication.show(session);
        do {
            session.println("Enter the id of application you want "
                    + "to view details and check.\n"
                    + "or \"V\" to view all applications "
                    + "and \"E\" to exit ");
            String choice = session.requestInput();
            if ("E".equals(choice)) {
                return this.getReferrer();
            } else if ("V".equals(choice)) {
                ShowAllApplication.show(session);
            } else {
                try {
                    int aid = Integer.parseInt(choice);
                    if (Application.isValidAid(aid)) {
                    	ShowDetailApplication.show(session, aid);
                    	int sta = DbManager.selectApplication(aid).getState();
                    	if (sta != 0) {
                    		session.print("This application has "
                                    + "been checked.");
                    	} else {
                    		session.println("Enter what state you want to set "
                                    + "(1: approve; 2: reject):");
                            sta = Integer.parseInt(session.requestInput());
                            while (!Application.isValidState(sta)) {
                                session.println("Your input state is invalid. "
                                        + "Please enter again:");
                                sta = Integer.parseInt(session.requestInput());
                            }
                            DbManager.changeState(aid, sta);
                    	}
                    } else {
                        session.print("Invalid choice, please try again: ");
                    }
                } catch (NumberFormatException e) {
                    session.print("Invalid choice, please try again: ");
                }
            }
        } while (true);
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
