package org.cs3343.safepaws.ui;

import org.cs3343.safepaws.util.Session;

import java.io.IOException;

public abstract class UI {
    protected String name;
    protected UI referrer;

    protected abstract UI execute(Session session) throws IOException;

    public UI(String name, UI referrer) {
        this.name = name;
        this.referrer = referrer;
    }

    public String getName() {
        return name;
    }

    public UI getNextUI(Session session) throws IOException {
        session.out.println("=== " + name + " ===");
        UI nextUI = execute(session);
        session.out.println();
        return nextUI == null ? referrer : nextUI;
    }

    public boolean isVisibleTo(Session session) {
        return false;
    }
}
