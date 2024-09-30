package org.cs3343.safepaws.action.menu;

import org.cs3343.safepaws.action.Action;
import org.cs3343.safepaws.action.Session;

public class TestMenu extends Menu {
    private static final String name = "Test Menu";
    private static final String title = "SafePaws - Test Menu";
    private static final String description = null;
    private final Action[] actions = new Action[]{

    };

    public TestMenu(Action referrer) {
        super(name, title, description);
        this.setActions(actions);
        this.setReferrer(referrer);
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return true;
    }
}
