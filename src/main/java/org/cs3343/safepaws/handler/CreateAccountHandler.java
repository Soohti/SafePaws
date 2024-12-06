package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.ShelterLocation;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;

import java.sql.SQLException;
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
     * Public constructor for instantiation.
     */
    public CreateAccountHandler() {
    }

    /**
     * Inserts an account into the database.
     *
     * @param username the username of account to insert.
     * @param password the password of account to insert.
     * @param role     the role of account to insert.
     */
    public void createAccount(final String username,
                              final String password,
                              final String role)
            throws SQLException {
        DbManager.insertWithAutoValue(
                Map.of(TableSchema.Column.Username, username,
                        TableSchema.Column.Password, password,
                        TableSchema.Column.Role, role),
                TableSchema.Name.ACCOUNT);
        Account thisAccount = (DbManager.readWithCondition(
                Account.class,
                TableSchema.Name.ACCOUNT,
                Map.of(TableSchema.Column.Username, username)
        )).getFirst();
        if ("m".equalsIgnoreCase(role)) {
            MemberProfile memberProfile = new MemberProfile(
                    thisAccount.getId(),
                    "Dog",
                    "Dog",
                    "m"
            );
            createMemberProfile(memberProfile);
        }
        if ("s".equalsIgnoreCase(role)) {
            ShelterLocation shelterLocation = new ShelterLocation(
                    thisAccount.getId(),
                    0, 0
            );
            createMemberProfile(shelterLocation);
        }
        System.out.println("Account inserted successfully");
    }

    /**
     * Inserts a member profile into the database.
     *
     * @param memberProfile the member profile of account to insert.
     */
    public void createMemberProfile(
            final MemberProfile memberProfile)
            throws SQLException {
        try {
            DbManager.insert(memberProfile,
                    TableSchema.Name.MEMBER_PROFILE);
        } catch (IllegalAccessException e) {
            System.out.println("Error occur when inserting member profile: "
                    + e.getMessage());
        }
        System.out.println("Member profile inserted successfully");
    }

    /**
     * Inserts a member profile into the database.
     *
     * @param shelterLocation the member profile of account to insert.
     */
    public void createMemberProfile(
            final ShelterLocation shelterLocation)
            throws SQLException {
        try {
            DbManager.insert(shelterLocation,
                    TableSchema.Name.SHELTER_LOCATION);
        } catch (IllegalAccessException e) {
            System.out.println("Error occur when inserting shelter location: "
                    + e.getMessage());
        }
        System.out.println("Shelter location inserted successfully");
    }

    /**
     * Validates the username.
     *
     * @param username the username to validate
     * @return 0 if the username is invalid, 1 if the username is taken,
     * 2 if the username is valid
     */
    public int isValidUsername(final String username) {
        try {
            LoginHandler handler = new LoginHandler();
            if (!(username.length() >= MIN_USERNAME_LENGTH
                    && username.length() <= MAX_USERNAME_LENGTH)) {
                return 0;
            } else if (handler.duplicateUsername(username)) {
                return 1;
            } else {
                return 2;
            }
        } catch (SQLException ex) {
            System.out.println("Error occur when finding duplicate username: "
                    + ex.getMessage());
        }
        return 1;
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
        return "M".equals(role) || "S".equals(role);
    }
}
