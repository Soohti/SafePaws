package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.util.AccountFactory;
import org.cs3343.safepaws.util.DbManager;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Map;
import java.util.Objects;

public final class LoginHandler {

    private static LoginHandler instance;

    private LoginHandler() {
    }

    public static LoginHandler getInstance() {
        if (instance == null) {
            instance = new LoginHandler();
        }
        return instance;
    }
    /**
     * Authenticates a user.
     *
     * @param inputUsername the username.
     * @param inputPassword the password.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticateUser(
            final String inputUsername,
            final String inputPassword) {
        try {
            Account thisAccount = (Account) DbManager
                    .getInstance().readWithCondition(
                    Account.class, "Account",
                    Map.of("Username", inputUsername));
            return BCrypt.checkpw(thisAccount.getPassword(), inputPassword);
        } catch (Exception ex) {
            System.out.println("Error during authenticating in: "
                    + ex.getMessage());
        }
        return false;
    }
    /**
     * Selects an account by username.
     *
     * @param inputUsername the username.
     * @return the account, or null if not found.
     */
    public Account selectAccount(final String inputUsername) {
        try {
        Account thisAccount =  (DbManager
                .getInstance().readWithCondition(
                Account.class, "Account",
                Map.of("Username", inputUsername))).getFirst();
        MemberProfile memberProfile = null;
        if (thisAccount.getRole().equalsIgnoreCase("m")) {
            memberProfile = (DbManager
                    .getInstance().readWithCondition(
                            MemberProfile.class,
                            "MEMBER_PROFILE",
                            Map.of("Id",
                                    String.valueOf(thisAccount.getId()))))
                    .getFirst();
        }
            return AccountFactory.createAccount(thisAccount.getId(),
                    thisAccount.getUsername(),
                    thisAccount.getPassword(),
                    thisAccount.getRole(),
                    memberProfile
                    );
        } catch (Exception ex) {
            System.out.println("Error during Logging in: "
                    + ex.getMessage());
        }
        return null;
    }
    /**
     * Check any duplicate username.
     *
     * @param inputUsername the username.
     * @return the boolean.
     */
    public static boolean duplicateUsername(final String inputUsername) {
        try {
            Account thisAccount = (Account) DbManager
                    .getInstance().readWithCondition(
                    Account.class, "Account",
                    Map.of("Username", inputUsername));
            return thisAccount.getUsername().equals(inputUsername);
        } catch (Exception ex) {
            System.out.println("Error during Logging in: "
                    + ex.getMessage());
        }
        return true;
    }


}
