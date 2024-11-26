package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.PetMatchingAlgo;
import org.cs3343.safepaws.util.Session;

import java.util.ArrayList;

/**
 * The ShowAllApplication class allows to view all adoption applications.
 */
public final class ShowAllApplication {
    /**
     * Default constructor.
     */
    private ShowAllApplication() {
    }

    /**
     * Display all adoption applications.
     *
     * @param session the current session
     */
    public static void show(final Session session) {
        ArrayList<Application> applications;
        try {
            applications = DbManager.viewAllApplication();
            session.printf("%-10s %-16s %-10s %-10s %-10s",
                    "Id", "Member", "PetID", "Score", "State");
            session.println("");

        for (Application a : applications) {
            session.printf("%-10d %-16s %-10d %-10f %-10s",
                    a.getId(), a.getUser().getUsername(),
                    a.getPet().getId(),
                    PetMatchingAlgo.calculateMatch(a.getUser(),
                            a.getPet()), state[a.getState()]);
            session.println("");
        }
    }
}
