package org.cs3343.safepaws.util;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.menu.MainMenu;

public class UIExecutor {
    private final Session session;

    public UIExecutor(Session session) {
        this.session = session;
    }

    public void start() {
        UI ui = new MainMenu();
        do {
            ui = ui.getNextUI(session);
        } while (ui != null);
    }
}
