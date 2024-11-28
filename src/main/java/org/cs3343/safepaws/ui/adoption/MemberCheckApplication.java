package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.handler.CheckMemberHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

import java.util.List;

/**
 * This class represents the UI for members to see their submitted
 * adoption applications.
 */
public final class MemberCheckApplication extends UI {
    /**
     * The name of this UI.
     */
    private static final String NAME =
            "Check submitted adoption applications";

    /**
     * Constructs a new MemberCheckApplication with the specified referrer.
     *
     * @param referrer the UI that referred to this one
     */
    public MemberCheckApplication(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the UI logic for displaying the member's submitted applications.
     *
     * @param session the current session
     * @return the referrer UI
     */
    @Override
    protected UI execute(final Session session) {
        Member member = (Member) session.getAccount();
        List<Application> applications =
                CheckMemberHandler.getInstance()
                        .findApplicationByMid(member.getId());
        if (applications.isEmpty()) {
            session.println("You have not submitted "
                    + "any adoption applications.");
        } else {
            for (Application application : applications) {
                Pet pet = application.getPet();
                String petInfo = "Pet Name: "
                        + pet.getName() + ", Species: " + pet.getSpecies();
                Application.State state = application.getState();
                String status = state != null ? state.name() : "Unknown";
                session.println(petInfo + " | Status: " + status);
            }
        }
        return this.getReferrer();
    }

    /**
     * Determines if this UI is visible to the current session.
     *
     * @param session the current session
     * @return true if the session's account is a member, false otherwise
     */
    @Override
    public boolean isVisibleTo(final Session session) {
        return session.getAccount() instanceof Member;
    }
}
