package org.cs3343.safepaws.ui;

public abstract class UI {
    protected String name;
    protected UI referrer;

    protected abstract UI execute(Session session);

    public UI(String name, UI referrer) {
        this.name = name;
        this.referrer = referrer;
    }

    public String getName() {
        return name;
    }

    public UI getNextUI(Session session) {
        System.out.println("=== " + name + " ===");
        UI nextUI = execute(session);
        System.out.println();
        return nextUI == null ? referrer : nextUI;
    }

    public boolean isVisibleTo(Session session) {
        return false;
    }
}
