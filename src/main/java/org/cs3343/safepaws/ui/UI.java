package org.cs3343.safepaws.ui;

import org.cs3343.safepaws.util.Session;

public abstract class UI {
    /**
     * The name of the UI.
     */
    private final String name;
    /**
     * The referrer of the UI.
     */
    private final UI referrer;

    protected abstract UI execute(Session session);

    /**
     * Constructs a new UI.
     *
     * @param pName     the name of the UI
     * @param pReferrer the referrer of the UI
     */
    public UI(final String pName, final UI pReferrer) {
        this.name = pName;
        this.referrer = pReferrer;
    }

    /**
     * Returns the name of the UI.
     *
     * @return the name of the UI
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the referrer of the UI.
     *
     * @return the referrer of the UI
     */
    public final UI getReferrer() {
        return referrer;
    }

    /**
     * Returns the next UI.
     *
     * @param session the session
     * @return the next UI
     */
    public final UI getNextUI(final Session session) {
        session.println("=== " + name + " ===");
        UI nextUI = execute(session);
        session.println();
        return nextUI;
    }
}
