package org.cs3343.safepaws.ui;

public class Exit extends UI {
    private static final String name = "Exit";

    public Exit(UI referrer) {
        super(name, referrer);
    }

    @Override
    protected UI execute(Session session) {
        System.out.println("Thank you for using SafePaws. Goodbye!");
        return null;
    }

    @Override
    public UI getNextUI(Session session) {
        System.out.println("=== " + name + " ===");
        UI nullUI = execute(session);
        System.out.println();
        return nullUI;
    }
}
