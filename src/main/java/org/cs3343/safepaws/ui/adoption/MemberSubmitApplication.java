package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.*;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

import java.sql.SQLException;

/**
 * Class to handle the submission of adoption applications.
 */
public class MemberSubmitApplication extends UI {
    /**
     * The name of the UI component.
     */
    private static final String NAME = "Submit an application";

    /**
     * Constructor for MemberSubmitApplication.
     *
     * @param referrer the UI referrer
     */
    public MemberSubmitApplication(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the submission process.
     *
     * @param session the current session
     * @return the referrer UI
     */
    @Override
    protected UI execute(final Session session) {

        Member user = (Member) session.getAccount();

        session.println("Enter the ID of the pet you want to apply for:");
        String userInput = session.requestInput();
        int pid = Integer.parseInt(userInput);
        while (Application.isValidPid(pid) != 2) {
            if (Application.isValidPid(pid) == 0) {
                session.println("Your input pet id is invalid."
                        + " Please enter again:");
            } else {
                session.print("This pet has been adopted. "
                        + "Please enter again:");
            }
            pid = Integer.parseInt(session.requestInput());
        }

        Pet pet;
        try {
            pet = DbManager.selectPetById(pid);
            session.println("Pet selected successfully.");
        } catch (Exception e) {
            pet = null;
            session.println("Error during selecting pet.");
        }
        int state = 0;
        AppState applicationState = AppState.fromInt(state);
        if (pet != null) {
            Application application = new Application(user, pet, applicationState);
            try {
                DbManager.insertApplication(application);
                session.println("Application created successfully.");
            } catch (SQLException e) {
                session.println("Error during creating application.");
            }
        }
        return this.getReferrer();
    }

    /**
     * Checks if the UI is visible to the session.
     *
     * @param session the current session
     * @return true if visible, false otherwise
     */
    @Override
    public boolean isVisibleTo(final Session session) {
        return session.getAccount() instanceof Member;
    }
}
