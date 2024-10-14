package org.cs3343.safepaws.ui;

import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

public class TestDb extends UI {
    /**
     * The name of the UI.
     */
    private static final String NAME = "Test Database Connection";

    /**
     * Constructs a new TestDb UI.
     *
     * @param referrer the referrer of the UI
     */
    public TestDb(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the UI and returns the referrer as the next UI.
     *
     * @param session the session to use
     * @return the referrer of the UI
     */
    @Override
    public UI execute(final Session session) {
        String result = DbManager.testSelect();
        session.println("Result: " + result);
        return this.getReferrer();
    }

    @Override
    public final boolean isVisibleTo(final Session session) {
        return true;
    }
}
