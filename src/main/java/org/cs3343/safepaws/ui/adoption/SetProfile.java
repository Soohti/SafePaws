package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The SetProfile class is responsible for setting the profile of a member.
 */
public class SetProfile extends UI {

    private static final String NAME = "Set Profile";

    /**
     * Constructor for SetProfile.
     *
     * @param referrer the UI referrer
     */
    public SetProfile(final UI referrer) {
        super("Set Profile", referrer);
    }

    /**
     * Executes the profile setting process.
     *
     * @param session the current session
     * @return the referrer UI
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected UI execute(final Session session) throws IOException {
        int extroversionLevel;
        int dailyActivityLevel;
        int houseSize;
        int workHours;
        int numberOfFamilyMembers;
        int previousPetExperience;
        int financialBudget;
        String preferredSpecies;
        String preferredBreed;
        String gender;

        session.println("Enter your extroversion level (1-10):");
        extroversionLevel = Integer.parseInt(session.requestInput());

        session.println("Enter your daily activity level (1-10):");
        dailyActivityLevel = Integer.parseInt(session.requestInput());

        session.println("Enter your house size (in square meters):");
        houseSize = Integer.parseInt(session.requestInput());

        session.println("Enter your work hours per day:");
        workHours = Integer.parseInt(session.requestInput());

        session.println("Enter the number of family members:");
        numberOfFamilyMembers = Integer.parseInt(session.requestInput());

        String message = "Enter your previous pet experience level " + 
                         "(1-10, higher value is more experienced):";
        session.println(message);
        previousPetExperience = Integer.parseInt(session.requestInput());

        session.println("Enter your financial budget (monthly, in dollars):");
        financialBudget = Integer.parseInt(session.requestInput());

        session.println("Enter your preferred species:");
        preferredSpecies = session.requestInput();

        session.println("Enter your preferred breed:");
        preferredBreed = session.requestInput();

        session.println("Enter your preferred pet gender (m/f):");
        gender = session.requestInput();

        Account account = session.getAccount();
        int[] numericAttributes = { extroversionLevel, dailyActivityLevel, 
                                    houseSize, workHours, numberOfFamilyMembers,
                                    previousPetExperience, financialBudget };

        ((Member) session.getAccount()).setProfile(preferredSpecies, 
                                                   preferredBreed, gender, 
                                                   numericAttributes);

        String userName = session.getAccount().getUsername();
        int mId = 0;

        try {
            mId = DbManager.selectAccount(userName).getId();
            DbManager.changeMemProfile(mId, 
                                       ((Member) session.getAccount())
                                       .getProfile());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.println("Profile set successfully.");

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
        return session.getAccount() != null && 
               "M".equals(session.getAccount().getRole());
    }
}
