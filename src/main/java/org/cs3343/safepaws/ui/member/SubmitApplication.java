package org.cs3343.safepaws.ui.member;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.handler.CheckApplicationHandler;
import org.cs3343.safepaws.handler.ReadPetHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

import java.sql.SQLException;


/**
 * Class to handle the submission of adoption applications.
 */
public final class SubmitApplication extends UI {
    /**
     * The name of the UI component.
     */
    private static final String NAME = "Submit an application";

    /**
     * Constructor for SubmitApplication.
     *
     * @param referrer the UI referrer
     */
    public SubmitApplication(final UI referrer) {
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
        ReadPetHandler handler = new ReadPetHandler();
        Member user = (Member) session.getAccount();
        session.println("Enter the ID of the pet you want to apply for:");
        String userInput = session.requestInput();
        int pid = Integer.parseInt(userInput);
        Pet thisPet = handler.findConditionalPet(pid);
        while (handler.isValidPetState(thisPet) != 2) {
            if (handler.isValidPetState(thisPet) == 0) {
                session.println("Your input pet id is invalid."
                        + " Please enter again:");
            } else {
                session.print("This pet has been adopted. "
                        + "Please enter again:");
            }
            pid = Integer.parseInt(session.requestInput());
            thisPet = handler.findConditionalPet(pid);
        }

        Application.State applicationState = Application.State.PENDING;
        CheckApplicationHandler
                checkApplicationHandler = new CheckApplicationHandler();
        if (thisPet != null) {
            try {
                checkApplicationHandler
                        .insertApplication(user,
                                thisPet,
                                applicationState);
                session.println("Application created successfully.");
            } catch (SQLException e) {
                session.println("Error during creating application.");
            }
        }
        return this.getReferrer();
    }
}
