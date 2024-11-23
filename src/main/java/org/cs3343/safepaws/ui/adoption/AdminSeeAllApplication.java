package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.PetMatchingAlgo;
import org.cs3343.safepaws.util.Session;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The AdminSeeAllApplication class represents a UI component that allows an
 * admin to view all adoption applications.
 */
public class AdminSeeAllApplication extends UI {
    private static final String NAME = "Admin see "
            + "all applications for adoption";

    /**
     * Constructs an AdminSeeAllApplication instance.
     *
     * @param referrer the UI that referred to this instance
     */
    public AdminSeeAllApplication(UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the UI logic to display all adoption applications.
     *
     * @param session the current session
     * @return the referrer UI
     */
    @Override
    protected UI execute(Session session) {
        ArrayList<Application> applications = new ArrayList<>();
        String[] State = { "Pending", "Approved", "Rejected" };
        try {
            applications = DbManager.ViewAllApplication();
            session.printf("%-10s %-10s %-10s %-10s %-10s",
                    "Id", "Mid", "Pid", "Score", "State");
            session.println("");

            for (Application a : applications) {
                session.printf("%-10d %-10d %-10d %-10f %-10s",
                        a.getId(), a.getUser().getId(), a.getPet().getId(),
                        PetMatchingAlgo.calculateMatch(a.getUser(),
                                a.getPet()), State[a.getState()]);
                session.println("");
            }
        } catch (SQLException e) {
            session.println("Error creating account: " + e.getMessage());
        }
        return this.getReferrer();
    }

    /**
     * Determines if the UI is visible to the current session.
     *
     * @param session the current session
     * @return true if the session's account is 
     * not null and has an admin role,
     * false otherwise
     */
    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null
                && "A".equals(session.getAccount().getRole());
    }
}
