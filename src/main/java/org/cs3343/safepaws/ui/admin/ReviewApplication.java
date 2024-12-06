package org.cs3343.safepaws.ui.admin;

import org.cs3343.safepaws.algorithm.PetMatchingAlgo;
import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.handler.ReadApplicationHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

import java.util.ArrayList;

/**
 * The ReviewApplication class allows an admin
 * to view and update the state
 * of an adoption application.
 */
public final class ReviewApplication extends UI {
    /**
     * The name of the UI for reviewing adoption applications.
     */
    private static final String NAME =
            "Review " + "adoption applications";

    /**
     * Constructs an ReviewApplication instance.
     *
     * @param referrer the UI that referred to this instance
     */
    public ReviewApplication(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the admin view application process.
     *
     * @param session the current session
     * @return the referrer UI
     */
    @Override
    protected UI execute(final Session session) {
        showAll(session);
        do {
            session.println("Enter the id of application you want "
                    + "to view details and check.\n"
                    + "or \"V\" to view all applications "
                    + "and \"E\" to exit ");
            String choice = session.requestInput();
            if ("E".equals(choice)) {
                return this.getReferrer();
            } else if ("V".equals(choice)) {
                showAll(session);
            } else {
                try {
                    ReadApplicationHandler handler =
                            new ReadApplicationHandler();
                    int aid = Integer.parseInt(choice);
                    Application thisApplication =
                            handler.findApplicationByAid(aid);
                    if (thisApplication != null) {
                        showDetail(session, aid);
                        Application.State appState;
                        appState = thisApplication.getState();
                        if (appState != Application.State.PENDING) {
                            session.print("This application has "
                                    + "already been processed.\n");
                        } else {
                            session.println("Enter what state you want to "
                                    + "set (1: approve; 2: reject):");
                            int inputStateNumber = Integer
                                    .parseInt(session.requestInput());
                            while (!Application
                                    .isValidState(inputStateNumber)) {
                                session.println("Your input state is "
                                        + "invalid. Please enter again:");
                                inputStateNumber = Integer
                                        .parseInt(session.requestInput());
                            }
                            int pid = thisApplication.getPet().getId();
                            Application.State state =
                                    Application.State
                                            .values()[inputStateNumber];
                            handler.changeApplicationState(aid, pid, state);
                            session.println("Your operation is completed.");
                        }
                    } else {
                        session.print("Invalid choice, please try again: ");
                    }
                } catch (NumberFormatException e) {
                    session.print("Invalid choice, please try again: ");
                }
            }
        } while (true);
    }

    /**
     * Display all adoption applications.
     *
     * @param session the current session
     */
    public static void showAll(final Session session) {
        ReadApplicationHandler handler = new ReadApplicationHandler();
        ArrayList<Application> applications;
        applications = handler.findAllApplication();
        session.printf("%-10s %-16s %-10s %-10s %-10s",
                "Id", "Member", "PetID", "Score", "State");
        session.println("");

        for (Application a : applications) {
            session.printf("%-10d %-16s %-10d %-10f %-10s",
                    a.getId(), a.getUser().getUsername(),
                    a.getPet().getId(),
                    PetMatchingAlgo.calculateMatch(a.getUser(), a.getPet()),
                    a.getState());
            session.println("");
        }
    }

    /**
     * Executes the UI logic for viewing application details.
     *
     * @param session the current session
     * @param aid     of application
     */
    public static void showDetail(final Session session, final int aid) {
        ReadApplicationHandler handler = new ReadApplicationHandler();
        Application application = handler.findApplicationByAid(aid);
        Member m = application.getUser();
        Pet p = application.getPet();
        MemberProfile pf = m.getProfile();

        session.printf(
                "%-16s %-20s %-20s %-20s %-20s %-15s %-15s %-25s %-25s %-20s "
                        + "%-10s",
                "Member", "PreferredSpecies", "PreferredBreed",
                "ExtroversionLevel",
                "DailyActivityLevel", "HouseSize", "WorkHours",
                "NumberOfFamilyMembers", "PreviousPetExperience",
                "FinancialBudget", "Gender");
        session.println("");
        session.printf(
                "%-16s %-20s %-20s %-20d %-20d %-15d %-15d %-25d %-25d %-20d "
                        + "%-10s",
                m.getUsername(), pf.getPreferredSpecies(),
                pf.getPreferredBreed(),
                pf.getExtroversionLevel(), pf.getDailyActivityLevel(),
                pf.getHouseSize(), pf.getWorkHours(),
                pf.getNumberOfFamilyMembers(), pf.getPreviousPetExperience(),
                pf.getFinancialBudget(), pf.getGender());
        session.println("");

        session.println("");
        session.printf("%-5s %-15s %-15s %-15s %-5s %-10s %-6s %-15s %-15s",
                "Id", "Name", "Species", "Breed", "Age", "Weight", "Gender",
                "ActivityLevel", "HealthStatus");
        session.println("");
        session.printf("%-5d %-15s %-15s %-15s %-5d %-10d %-6s %-15d %-15d",
                p.getId(), p.getName(), p.getSpecies(), p.getBreed(),
                p.getAge(), p.getWeight(), p.getGender(),
                p.getActivityLevel(),
                p.getHealthStatus());
        session.println("");
        session.println("");
        session.printf("%-10s %-10s", "Score", "State");
        session.println("");
        session.printf("%-10f %-10s", PetMatchingAlgo.calculateMatch(m, p),
                application.getState());
        session.println("");
    }
}
