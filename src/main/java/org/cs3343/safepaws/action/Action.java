package org.cs3343.safepaws.action;

public abstract class Action {
    protected String name;
    protected Action referrer;

    protected abstract Action execute(Session session);

    public Action(String name, Action referrer) {
        this.name = name;
        this.referrer = referrer;
    }

    public String getName() {
        return name;
    }

    public Action getNextAction(Session session) {
        System.out.println("=== " + name + " ===");
        execute(session);
        return referrer;
    }

    public boolean isVisibleTo(Session session) {
        return false;
    }
}
