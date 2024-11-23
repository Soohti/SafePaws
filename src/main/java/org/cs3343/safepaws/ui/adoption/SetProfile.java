package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;

/**
 * The SetProfile class is responsible for setting the profile of a member.
 */
public class SetProfile extends UI {

    /**
     * The name of the profile setting UI.
     */
    private static final String NAME = "Set Profile";

    /**
     * Constructor for SetProfile.
     *
     * @param referrer the UI referrer
     */
    public SetProfile(final UI referrer) {
        super(NAME, referrer);
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

        session.println("Enter your extroversion level "
                + "(a positive integer meeting 0-10, "
                + "larger numbers indicate more extroversion):");
        extroversionLevel = Integer.parseInt(session.requestInput());
        while (!MemberProfile.isValidExtroversionLevel(extroversionLevel)) {
            session.println("Your input extroversion level"
                    + " is invalid. Please enter again:");
            extroversionLevel = Integer.parseInt(session.requestInput());
        }
        

        session.println("Enter your daily activity level "
                + "(a positive integer meeting 0-10, "
                + "larger numbers indicate more activity):");
        dailyActivityLevel = Integer.parseInt(session.requestInput());
        while (!MemberProfile.isValidDailyActivityLevel(dailyActivityLevel)) {
            session.println("Your input daily activity level "
                    + "is invalid. Please enter again:");
            dailyActivityLevel = Integer.parseInt(session.requestInput());
        }

        session.println("Enter your house size "
                + "(in square meters, if larger than "
                + "100000000, enter 100000000):");
        houseSize = Integer.parseInt(session.requestInput());
        while (!MemberProfile.isValidHouseSize(houseSize)) {
            session.println("Your input house size is invalid. "
                    + "Please enter again:");
            houseSize = Integer.parseInt(session.requestInput());
        }

        session.println("Enter your work hours per day (an interger):");
        workHours = Integer.parseInt(session.requestInput());
        while (!MemberProfile.isValidWorkHours(workHours)) {
            session.println("Your input work hours is "
                    + "invalid. Please enter again:");
            workHours = Integer.parseInt(session.requestInput());
        }

        session.println("Enter the number of family members "
                + "(if larger than 100000000, enter 100000000):");
        numberOfFamilyMembers = Integer.parseInt(session.requestInput());
        while (!MemberProfile.isValidNumberOfFamilyMembers(
                numberOfFamilyMembers)) {
            session.println("Your input number of family members"
                    + " is invalid. Please enter again:");
            numberOfFamilyMembers = Integer.parseInt(session.requestInput());
        }

        String message = "Enter your previous pet experience level "
                + "(a positive integer meeting 0-10,"
                + "larger numbers indicate more activity):";
        session.println(message);
        previousPetExperience = Integer.parseInt(session.requestInput());
        while (!MemberProfile.isValidPreviousPetExperience(previousPetExperience)) {
            session.println("Your input previous pet experience"
                    + " is invalid. Please enter again:");
            previousPetExperience = Integer.parseInt(session.requestInput());
        }

        session.println("Enter your financial budget (monthly, "
                + "in dollars, if larger than 100000000, enter 100000000):");
        financialBudget = Integer.parseInt(session.requestInput());
        while (!MemberProfile.isValidFinancialBudget(financialBudget)) {
            session.println("Your input financial budget is "
                    + "invalid. Please enter again:");
            financialBudget = Integer.parseInt(session.requestInput());
        }

        session.println("Enter your preferred species (length: "
                + "0-30, any character):");
        preferredSpecies = session.requestInput();
        while (!MemberProfile.isValidPreferredSpecies(preferredSpecies)) {
            session.println("Your input preferred species is "
                    + "invalid. Please enter again:");
            preferredSpecies = session.requestInput();
        }

        session.println("Enter your preferred breed (length: "
                + "0-30, any character):");
        preferredBreed = session.requestInput();
        while (!MemberProfile.isValidPreferredBreed(preferredBreed)) {
            session.println("Your input preferred breed is "
                    + "invalid. Please enter again:");
            preferredBreed = session.requestInput();
        }

        session.println("Enter your preferred pet gender "
                + "(\"m\" for male, \"f\" for female):");
        gender = session.requestInput();
        while (!MemberProfile.isValidGender(gender)) {
            session.println("Your input gender is invalid. "
                    + "Please enter again:");
            gender = session.requestInput();
        }

        Account account = session.getAccount();
        int[] numericAttributes = {extroversionLevel, dailyActivityLevel,
                houseSize, workHours, numberOfFamilyMembers,
                previousPetExperience, financialBudget};

        ((Member) account).setProfile(preferredSpecies,
                preferredBreed, gender, numericAttributes);

        String userName = session.getAccount().getUsername();
        int mId = 0;

        try {
            mId = DbManager.selectAccount(userName).getId();
            DbManager.changeMemProfile(mId,
                    ((Member) session.getAccount()).getProfile());
            session.println("Profile set successfully.");
        } catch (Exception e) {
            session.println("Error during setting profile.");
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
        return session.getAccount() != null
                && "M".equals(session.getAccount().getRole());
    }
}
