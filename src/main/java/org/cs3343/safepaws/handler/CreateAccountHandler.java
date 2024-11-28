package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.util.DbManager;

import java.util.Map;

public final class CreateAccountHandler {
    /**
     * The minimum length for a username.
     */
    private static final int MIN_USERNAME_LENGTH = 8;

    /**
     * The maximum length for a username.
     */
    private static final int MAX_USERNAME_LENGTH = 30;
    /**
     * The minimum length for a password.
     */
    private static final int MIN_PASSWORD_LENGTH = 8;

    /**
     * The maximum length for a password.
     */
    private static final int MAX_PASSWORD_LENGTH = 16;
    /**
     * The index for health status.
     */
    private static CreateAccountHandler instance;

    private CreateAccountHandler() {
    }
    /**
     * Gets the single instance of the handler.
     *
     * @return the instance of CreateAccountHandler
     */
    public static CreateAccountHandler getInstance() {
        if (instance == null) {
            instance = new CreateAccountHandler();
        }
        return instance;
    }
    /**
     * Inserts an account into the database.
     * @param username the username of account to insert.
     * @param password the password of account to insert.
     * @param role the role of account to insert.
     */
    public void createAccount(final String username,
                              final String password,
                              final String role)
            throws Exception {
        DbManager.getInstance().insertWithAutoValue(
                Map.of("Username", username,
                        "Password", password,
                        "Role", role),
                "ACCOUNT");
        Account thisAccount = (DbManager.getInstance().readWithCondition(
                Account.class,
                "ACCOUNT",
                Map.of("Username", username)
        )).getFirst();
        if (role.equalsIgnoreCase("m")) {
            MemberProfile memberProfile = new MemberProfile.Builder()
                    .setId(thisAccount.getId())
                    .setPreferredSpecies("Dog")
                    .setPreferredBreed("Dog")
                    .setGender("m")
                    .setExtroversionLevel(0)
                    .setDailyActivityLevel(0)
                    .setHouseSize(0)
                    .setWorkHours(0)
                    .setNumberOfFamilyMembers(0)
                    .setPreviousPetExperience(0)
                    .setFinancialBudget(0)
                    .build();
            createMemberProfile(memberProfile);
        }
        System.out.println("Account inserted successfully");
    }
    /**
     * Inserts a member profile into the database.
     * @param memberProfile the member profile of account to insert.
     */
    public void createMemberProfile(
            final MemberProfile memberProfile)
            throws Exception {
        DbManager.getInstance().insert(memberProfile,
                "MEMBER_PROFILE");
        System.out.println("Member profile inserted successfully");
    }
    /**
     * Validates the username.
     *
     * @param username the username to validate
     * @return 0 if the username is invalid, 1 if the username is taken,
     * 2 if the username is valid
     */
    public int isValidUsername(final String username) {
        if (!(username.length() >= MIN_USERNAME_LENGTH
                && username.length() <= MAX_USERNAME_LENGTH)) {
            return 0;
        } else if (LoginHandler.duplicateUsername(username)) {
            return 1;
        } else {
            return 2;
        }
    }
    /**
     * Validates the password.
     *
     * @param password the password to validate
     * @return true if the password is valid, false otherwise
     */
    public boolean isValidPassword(final String password) {
        return password.length() >= MIN_PASSWORD_LENGTH
                && password.length() <= MAX_PASSWORD_LENGTH;
    }
    /**
     * Validates the role.
     *
     * @param role the role to validate
     * @return true if the role is valid, false otherwise
     */
    public boolean isValidRole(final String role) {
        return "A".equals(role) || "M".equals(role) || "S".equals(role);
    }
}
