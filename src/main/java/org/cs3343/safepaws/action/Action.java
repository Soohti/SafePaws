package org.cs3343.safepaws.action;

public interface Action {
    String getName();

    Action execute(Session session);

    boolean isVisibleTo(Session session);
}
