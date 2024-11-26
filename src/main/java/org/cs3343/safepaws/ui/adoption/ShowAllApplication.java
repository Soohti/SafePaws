package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.PetMatchingAlgo;
import org.cs3343.safepaws.util.Session;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The ShowAllApplication class allows to view all adoption applications.
 */
public class ShowAllApplication {
	/**
	 * Default constructor 
	 */
    public ShowAllApplication() {	
    }

    /**
     * Display all adoption applications.
     *
     * @param session the current session
     * @return null
     */
    public static void Show(final Session session) {
        ArrayList<Application> applications;
        String[] state = {"Pending", "Approved", "Rejected"};
        try {
            applications = DbManager.viewAllApplication();
            session.printf("%-10s %-10s %-10s %-10s %-10s",
                    "Id", "Mid", "Pid", "Score", "State");
            session.println("");

            for (Application a : applications) {
                session.printf("%-10d %-10d %-10d %-10f %-10s",
                        a.getId(), a.getUser().getId(), a.getPet().getId(),
                        PetMatchingAlgo.calculateMatch(a.getUser(),
                                a.getPet()), state[a.getState()]);
                session.println("");
            }
        } catch (SQLException e) {
            session.println("Error creating account: " + e.getMessage());
        }
    }
}
