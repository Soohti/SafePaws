package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.handler.UpdateMemberProfileHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.AccountFactory;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

/**
 * The MemberSetProfile class is responsible for
 * setting the profile of a member.
 */
public class MemberSetProfile extends UI {

    /**
     * The name of the profile setting UI.
     */
    private static final String NAME = "Set Profile";

    /**
     * The maximum extroversion level.
     */
    private static final int MAX_EXTROVERSION_LEVEL = 10;

    /**
     * The maximum daily activity level.
     */
    private static final int MAX_DAILY_ACTIVITY_LEVEL = 10;

    /**
     * The maximum house size.
     */
    private static final int MAX_HOUSE_SIZE = 1000000000;

    /**
     * The maximum work hours.
     */
    private static final int MAX_WORK_HOURS = 24;

    /**
     * The maximum number of family members.
     */
    private static final int MAX_FAMILY_MEMBERS = 10;

    /**
     * The maximum previous pet experience.
     */
    private static final int MAX_PREVIOUS_PET_EXPERIENCE = 10;

    /**
     * The maximum financial budget.
     */
    private static final int MAX_FINANCIAL_BUDGET = 100000000;

    /**
     * The maximum length of the species.
     */
    private static final int MAX_SPECIES_LENGTH = 30;

    /**
     * The maximum length of the breed.
     */
    private static final int MAX_BREED_LENGTH = 30;

    /**
     * Constructor for MemberSetProfile.
     *
     * @param referrer the UI referrer
     */
    public MemberSetProfile(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the profile setting process.
     *
     * @param session the current session
     * @return the referrer UI
     */
    @Override
    protected UI execute(final Session session) {
        String preferredSpecies;
        String preferredBreed;
        String gender;

        session.println("Enter your extroversion level "
                + "(a positive integer meeting 0-10, "
                + "larger numbers indicate more extroversion):");
        int intExtroversionLevel =
                session.requestNumericInput(0, MAX_EXTROVERSION_LEVEL);

        session.println("Enter your daily activity level "
                + "(a positive integer meeting 0-10, "
                + "larger numbers indicate more activity):");
        int intDailyActivityLevel =
                session.requestNumericInput(0, MAX_DAILY_ACTIVITY_LEVEL);

        session.println("Enter your house size "
                + "(in square meters, if larger than "
                + "100000000, enter 100000000):");
        int intHouseSize = session.requestNumericInput(0, MAX_HOUSE_SIZE);

        session.println("Enter your work hours per day "
                + "(an integer between 0 and 24):");
        int intWorkHours = session.requestNumericInput(0, MAX_WORK_HOURS);

        session.println("Enter the number of family members "
                + "(if larger than 10, enter 10):");
        int intNumberOfFamilyMembers =
                session.requestNumericInput(1, MAX_FAMILY_MEMBERS);

        session.println("Enter your previous pet experience level "
                + "(a positive integer meeting 0-10,"
                + "larger numbers indicate more experience):");
        int intPreviousPetExperience =
                session.requestNumericInput(0, MAX_PREVIOUS_PET_EXPERIENCE);

        session.println("Enter your financial budget (monthly, in dollars, "
                + "if larger than 100000000, enter 100000000):");
        int intFinancialBudget =
                session.requestNumericInput(0, MAX_FINANCIAL_BUDGET);

        session.println("Enter your preferred species (length: "
                + "0-30, any character):");
        preferredSpecies = session.requestInput();
        while (preferredSpecies.length() > MAX_SPECIES_LENGTH) {
            session.println("Your input preferred species is "
                    + "invalid. Please enter again:");
            preferredSpecies = session.requestInput();
        }

        session.println("Enter your preferred breed (length: "
                + "0-30, any character):");
        preferredBreed = session.requestInput();
        while (preferredBreed.length() > MAX_BREED_LENGTH) {
            session.println("Your input preferred breed is "
                    + "invalid. Please enter again:");
            preferredBreed = session.requestInput();
        }

        session.println("Enter your preferred pet gender "
                + "(\"m\" for male, \"f\" for female):");
        gender = session.requestInput();
        while (!"m".equals(gender) && !"f".equals(gender)) {
            session.println("Your input gender is invalid. "
                    + "Please enter again:");
            gender = session.requestInput();
        }
        Account account = session.getAccount();

        MemberProfile profile = new MemberProfile.Builder()
                .setId(account.getId())
                .setPreferredSpecies(preferredSpecies)
                .setPreferredBreed(preferredBreed)
                .setGender(gender)
                .setExtroversionLevel(intExtroversionLevel)
                .setDailyActivityLevel(intDailyActivityLevel)
                .setHouseSize(intHouseSize)
                .setWorkHours(intWorkHours)
                .setNumberOfFamilyMembers(intNumberOfFamilyMembers)
                .setPreviousPetExperience(intPreviousPetExperience)
                .setFinancialBudget(intFinancialBudget)
                .build();
        session.setAccount(AccountFactory.createAccount(
                account.getId(), account.getUsername(),
                account.getPassword(), account.getRole(),
                profile));
        try {
            UpdateMemberProfileHandler.getInstance()
                    .updateMemberProfile(profile);
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
        return session.getAccount() instanceof Member;
    }
}
