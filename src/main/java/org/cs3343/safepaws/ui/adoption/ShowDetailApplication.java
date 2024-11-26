package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.PetMatchingAlgo;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;

/**
 * ShowDetailApplication class provides the functionality for
 * admin to view detailed information of a specific application.
 */
public final class ShowDetailApplication {
    /**
     * Default constructor.
     */
    private ShowDetailApplication() {
    }

    /**
     * Executes the UI logic for viewing application details.
     *
     * @param session the current session
     * @param aid of application
     * @throws IOException if an I/O error occurs
     */
    public static void show(final Session session, final int aid)
            throws IOException {
        Application application = DbManager.selectApplication(aid);
        Member m = application.getUser();
        Pet p = application.getPet();
        MemberProfile pf = m.getProfile();

        session.printf(
                "%-5s %-20s %-20s %-20s %-20s %-15s %-15s %-25s %-25s %-20s "
                        + "%-10s",
                "Id", "PreferredSpecies", "PreferredBreed", "ExtroversionLevel",
                "DailyActivityLevel", "HouseSize", "WorkHours",
                "NumberOfFamilyMembers", "PreviousPetExperience",
                "FinancialBudget", "Gender");
        session.println("");
        session.printf(
                "%-5d %-20s %-20s %-20d %-20d %-15d %-15d %-25d %-25d %-20d "
                        + "%-10s",
                m.getId(), pf.getPreferredSpecies(), pf.getPreferredBreed(),
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

        String[] state = {"Pending", "Approved", "Rejected"};
        session.println("");
        session.printf("%-10s %-10s", "Score", "State");
        session.println("");
        session.printf("%-10f %-10s", PetMatchingAlgo.calculateMatch(m, p),
                state[application.getState()]);
        session.println("");

    }

}
