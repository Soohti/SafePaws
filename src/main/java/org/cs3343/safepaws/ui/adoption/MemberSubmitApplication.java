package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.handler.CheckMemberHandler;
import org.cs3343.safepaws.handler.ReadPetHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;


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

        Pet pet;
        try {
            pet = handler.findConditionalPet(pid);
            session.println("Pet selected successfully.");
        } catch (Exception e) {
            pet = null;
            session.println("Error during selecting pet.");
        }
        Application.State applicationState = Application.State.PENDING;
        CheckMemberHandler checkMemberHandler = new CheckMemberHandler();
        if (pet != null) {
            try {
                checkMemberHandler
                        .insertApplication(user,
                                pet,
                                applicationState);
                session.println("Application created successfully.");
            } catch (Exception e) {
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
