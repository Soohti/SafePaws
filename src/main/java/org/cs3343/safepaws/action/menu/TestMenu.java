package org.cs3343.safepaws.action.menu;

import org.cs3343.safepaws.action.Action;
import org.cs3343.safepaws.action.Session;
import org.cs3343.safepaws.action.TestDb;

public class TestMenu extends Menu {
    private static final String name = "Test Menu";
    private static final String title = "SafePaws - Test Menu";
    private static final String description = null;
    private final Action[] actions = new Action[]{
            new TestDb(this)
    };

    public TestMenu(Action referrer) {
        super(name, title, description, referrer);
        this.setActions(actions);
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return true;
    }
}
