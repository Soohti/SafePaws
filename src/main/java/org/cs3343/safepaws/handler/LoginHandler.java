package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.ShelterLocation;
import org.cs3343.safepaws.util.AccountFactory;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Map;
import java.util.NoSuchElementException;

public final class LoginHandler {
    /**
     * Public constructor for instantiation.
     */
    public LoginHandler() {
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
                Account thisAccount = (DbManager.readWithCondition(
                        Account.class, TableSchema.Name.ACCOUNT,
                        Map.of(TableSchema.Column.Username,
                                inputUsername))).getFirst();
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
            Account thisAccount = (DbManager.readWithCondition(
                    Account.class, TableSchema.Name.ACCOUNT,
                    Map.of(TableSchema.Column.Username,
                            inputUsername))).getFirst();
            MemberProfile memberProfile = null;
            ShelterLocation shelterLocation = null;
            if ("m".equalsIgnoreCase(thisAccount.getRole())) {
                memberProfile = (DbManager.readWithCondition(
                        MemberProfile.class,
                        TableSchema.Name.MEMBER_PROFILE,
                        Map.of(TableSchema.Column.Id,
                                String.valueOf(thisAccount.getId()))))
                        .getFirst();
            }
            if ("s".equalsIgnoreCase(thisAccount.getRole())) {
                shelterLocation = (DbManager.readWithCondition(
                        ShelterLocation.class,
                        TableSchema.Name.SHELTER_LOCATION,
                        Map.of(TableSchema.Column.Id,
                                String.valueOf(thisAccount.getId()))))
                        .getFirst();
            }
            return AccountFactory.createAccount(thisAccount.getId(),
                    thisAccount.getUsername(),
                    thisAccount.getPassword(),
                    thisAccount.getRole(),
                    memberProfile,
                    shelterLocation
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
    public boolean duplicateUsername(final String inputUsername)
            throws Exception {
        try {
            Account account = (DbManager.readWithCondition(
                    Account.class, TableSchema.Name.ACCOUNT,
                    Map.of(TableSchema.Column.Username,
                            inputUsername))).getFirst();
            return inputUsername.equals(account.getUsername());
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


}
