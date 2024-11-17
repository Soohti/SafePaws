package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.*;
import java.io.IOException;
import java.sql.SQLException;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

/**
 * 
 */
public class SetProfile extends UI {

    private static final String NAME = "Set Profile";

    /**
     * @param referrer
     */
    public SetProfile(final UI referrer) {
        super("Set Profile", referrer);
    }

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

        String message = "" + "Enter your previous pet experience level " + "(1-10, higher value is more experienced):";
        session.println(message);
        previousPetExperience = Integer.parseInt(session.requestInput());

        session.println("" + "Enter your financial budget " + "(monthly, in dollars):");
        financialBudget = Integer.parseInt(session.requestInput());

        
        session.println("Enter your preferred species:");
        preferredSpecies = session.requestInput();;

        session.println("Enter your preferred breed:");
        preferredBreed = session.requestInput();

        session.println("Enter your preferred pet gender (m/f):");
        gender = session.requestInput();

        Account account = session.getAccount();
        ((Member)session.getAccount()).setProfile(preferredSpecies, preferredBreed, gender, extroversionLevel, dailyActivityLevel, houseSize,
                workHours, numberOfFamilyMembers, previousPetExperience, financialBudget);
        String userName = session.getAccount().getUsername();
        int mId = 0;

        
        try {
            mId = DbManager.selectAccount(userName).getId();
            DbManager.changeMemProfile(mId, ((Member)session.getAccount()).getProfile());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.println("Profile set successfully.");

        return this.getReferrer();
    }

    @Override
    public boolean isVisibleTo(final Session session) {
        return session.getAccount() != null && "M".equals(session.getAccount().getRole());
    }
}