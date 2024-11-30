package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.util.AccountFactory;
import org.cs3343.safepaws.util.DbManager;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Map;
import java.util.NoSuchElementException;

public final class LoginHandler {
    /**
     * The index for health status.
     */
    private static LoginHandler instance;

    private LoginHandler() {
    }
    /**
     * Gets the single instance of the handler.
     *
     * @return the instance of LoginHandler
     */
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
            try {
                Account thisAccount = (DbManager
                        .getInstance().readWithCondition(
                                Account.class, "ACCOUNT",
                                Map.of("Username", inputUsername))).getFirst();
                if (thisAccount == null) {
                    return false;
                }
                return BCrypt.checkpw(inputPassword, thisAccount.getPassword());
            } catch (NoSuchElementException ex) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error during authenticating in:"
                    + ex.getMessage());
            ex.printStackTrace();
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
                Account.class, "ACCOUNT",
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
                    memberProfile,
                    null
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
    public static boolean duplicateUsername(final String inputUsername)
    throws Exception {
        try {
            Account account = (DbManager
                    .getInstance().readWithCondition(
                    Account.class, "ACCOUNT",
                    Map.of("Username", inputUsername))).getFirst();
        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
    }


}
