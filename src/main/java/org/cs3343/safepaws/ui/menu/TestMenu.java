package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.Session;
import org.cs3343.safepaws.ui.TestDb;

public class TestMenu extends Menu {
    private static final String name = "Test Menu";
    private static final String title = "SafePaws - Test Menu";
    private static final String description = null;
    private final UI[] UIs = new UI[]{
            new TestDb(this)
    };

    public TestMenu(UI referrer) {
        super(name, title, description, referrer);
        this.setUIs(UIs);
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return true;
    }
}
