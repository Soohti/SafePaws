package org.cs3343.safepaws.util;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.menu.MainMenu;

import java.io.IOException;

public class UIExecutor {
    /**
     * The session of the user associated with the UI.
     */
    private final Session session;

    /**
     * Constructor of the UIExecutor.
     *
     * @param pSession The session of the user.
     */
    public UIExecutor(final Session pSession) {
        this.session = new Session(pSession);
    }

    /**
     * Start the UI.
     *
     * @throws IOException If an error occurs.
     */
    public void start() throws IOException {
        UI ui = new MainMenu();
        do {
            ui = ui.getNextUI(session);
        } while (ui != null);
    }
}
