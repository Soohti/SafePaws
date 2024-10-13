package org.cs3343.safepaws.ui;

import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

public class TestDb extends UI {
    private static final String name = "Test Database Connection";

    public TestDb(UI referrer) {
        super(name, referrer);
    }

    @Override
    public UI execute(Session session) {
        String result = DbManager.testSelect();
        session.println("Result: " + result);
        return null;
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return true;
    }
}
