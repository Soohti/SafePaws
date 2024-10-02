package org.cs3343.safepaws.action;

import org.cs3343.safepaws.util.DbManager;

public class TestDb extends Action {
    private static final String name = "Test Database Connection";

    public TestDb(Action referrer) {
        super(name, referrer);
    }

    @Override
    public Action execute(Session session) {
        String result = DbManager.testSelect();
        System.out.println("Result: " + result);
        return null;
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return true;
    }
}
