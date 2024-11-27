package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.util.DbManager;

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
            DbManager ormHelper = new DbManager();
            Account thisAccount = (Account) DbManager.readWithCondition(
                    Account.class, "Account",
                    Map.of("Username", inputUsername));
            return Objects.equals(thisAccount.getPassword(), inputPassword);
        } catch (Exception ex) {
            System.out.println("Error during authenticating in: "
                    + ex.getMessage());
        }
        return false;
    }

    public Account selectAccount(final String inputUsername) {
        try {
        Account thisAccount = (Account) DbManager.readWithCondition(
                Account.class, "Account",
                Map.of("Username", inputUsername));
        return thisAccount;
        } catch (Exception ex) {
            System.out.println("Error during Logging in: "
                    + ex.getMessage());
        }
        return null;
    }

    public static boolean duplicateUsername(final String inputUsername) {
        try {
            Account thisAccount = (Account) DbManager.readWithCondition(
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
