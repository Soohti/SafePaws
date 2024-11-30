package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.handler.ReadApplicationHandler;
import org.cs3343.safepaws.algorithm.PetMatchingAlgo;
import org.cs3343.safepaws.util.Session;

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
     * @param aid     of application
     */
    public static void show(final Session session, final int aid) {
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
