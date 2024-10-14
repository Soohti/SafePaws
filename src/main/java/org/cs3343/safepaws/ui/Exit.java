package org.cs3343.safepaws.ui;

import org.cs3343.safepaws.util.Session;

public class Exit extends UI {
    /**
     * The name of the Exit UI.
     */
    private static final String NAME = "Exit";

    /**
     * Constructor of the Exit UI.
     *
     * @param referrer The UI that referred to this UI.
     */
    public Exit(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Prints a goodbye message and returns null to exit UIExecutor.
     *
     * @param session The session of the user.
     * @return null.
     */
    @Override
    protected UI execute(final Session session) {
        session.println("Thank you for using SafePaws. Goodbye!");
        return null;
    }
}
