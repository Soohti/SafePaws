package org.cs3343.safepaws.util;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.menu.MainMenu;

public class UIExecutor {
    /**
     * Start the UI.
     *
     * @param session the session
     */
    public void start(final Session session) {
        UI ui = new MainMenu();
        do {
            ui = ui.getNextUI(session);
        } while (ui != null);
    }
}
