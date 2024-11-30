package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Admin;
import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.handler.ReadApplicationHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

/**
 * The AdminReviewApplication class allows an admin
 * to view and update the state
 * of an adoption application.
 */
public class AdminReviewApplication extends UI {
    /**
     * The name of the UI for reviewing adoption applications.
     */
    private static final String NAME =
            "Review " + "adoption applications";

    /**
     * Constructs an AdminReviewApplication instance.
     *
     * @param referrer the UI that referred to this instance
     */
    public AdminReviewApplication(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the admin view application process.
     *
     * @param session the current session
     * @return the referrer UI
     */
    @Override
    protected UI execute(final Session session) {
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
                    ReadApplicationHandler handler = new ReadApplicationHandler();
                    int aid = Integer.parseInt(choice);
                    Application thisApplication =
                            handler.findApplicationByAid(aid);
                    if (thisApplication != null) {
                        ShowDetailApplication.show(session, aid);
                        Application.State appState = null;
                        if (thisApplication.getState() != null) {
                            appState = thisApplication.getState();
                        } else {
                            session.println("Application found with"
                                    + " id contain error status" + aid);
                            continue;
                        }
                        if (appState != Application.State.PENDING) {
                            session.print("This application has "
                                    + "already been processed.\n");
                        } else {
                            session.println("Enter what state you want to "
                                    + "set (1: approve; 2: reject):");
                            int inputStateNumber = Integer
                                    .parseInt(session.requestInput());
                            while (!Application
                                    .isValidState(inputStateNumber)) {
                                session.println("Your input state is "
                                        + "invalid. Please enter again:");
                                inputStateNumber = Integer
                                        .parseInt(session.requestInput());
                            }
                            int pid = thisApplication.getPet().getId();
                            Application.State state =
                                    Application.State
                                            .values()[inputStateNumber];
                            handler.changeApplicationState(aid, pid, state);
                            session.println("Your operation is completed.");
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
        return session.getAccount() instanceof Admin;
    }
}
