package org.cs3343.safepaws.ui;

import org.cs3343.safepaws.util.Session;

public class Exit extends UI {
    private static final String name = "Exit";

    public Exit(UI referrer) {
        super(name, referrer);
    }

    @Override
    protected UI execute(Session session) {
        session.out.println("Thank you for using SafePaws. Goodbye!");
        return null;
    }

    @Override
    public UI getNextUI(Session session) {
        session.out.println("=== " + name + " ===");
        UI nullUI = execute(session);
        session.out.println();
        return nullUI;
    }
}
