package org.cs3343.safepaws.ui.admin;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.handler.ReadApplicationHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

/**
 * The ReviewApplication class allows an admin
 * to view and update the state
 * of an adoption application.
 */
public final class ReviewApplication extends UI {
    /**
     * The name of the UI for reviewing adoption applications.
     */
    private static final String NAME =
            "Review " + "adoption applications";

    /**
     * Constructs an ReviewApplication instance.
     *
     * @param referrer the UI that referred to this instance
     */
    public ReviewApplication(final UI referrer) {
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
                    ReadApplicationHandler handler =
                            new ReadApplicationHandler();
                    int aid = Integer.parseInt(choice);
                    Application thisApplication =
                            handler.findApplicationByAid(aid);
                    if (thisApplication != null) {
                        ShowDetailApplication.show(session, aid);
                        Application.State appState;
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
}
