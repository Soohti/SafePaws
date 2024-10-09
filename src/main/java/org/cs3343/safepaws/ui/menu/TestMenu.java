package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;
import org.cs3343.safepaws.ui.TestDb;

public class TestMenu extends Menu {
    private static final String name = "Test Menu";
    private static final String description = null;
    private final UI[] menuItems = new UI[]{
            new TestDb(this),
    };

    public TestMenu(UI referrer) {
        super(name, description, referrer);
        setMenuItems(menuItems);
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return true;
    }
}
