package org.cs3343.safepaws.entity;

import org.cs3343.safepaws.handler.LoginHandler;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Represents an account with username, password, role, and id.
 */
public class Account {

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
     * The username of the account.
     */
    private final String username;

    /**
     * The password of the account.
     */
    private final String password;

    /**
     * The role of the account.
     */
    private final String role;

    /**
     * Encrypts the given password using BCrypt.
     *
     * @param password the password to encrypt
     * @return the encrypted password
     */
    public static String encryptPassword(final String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Validates the username.
     *
     * @param username the username to validate
     * @return 0 if the username is invalid, 1 if the username is taken,
     * 2 if the username is valid
     */
    public static int isValidUsername(final String username) {
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
    public static boolean isValidPassword(final String password) {
        return password.length() >= MIN_PASSWORD_LENGTH
                && password.length() <= MAX_PASSWORD_LENGTH;
    }

    /**
     * Validates the role.
     *
     * @param role the role to validate
     * @return true if the role is valid, false otherwise
     */
    public static boolean isValidRole(final String role) {
        return "A".equals(role) || "M".equals(role) || "S".equals(role);
    }

    /**
     * Instantiates a new account with the
     * specified username, password, and role.
     *
     * @param newUn   the username
     * @param newP    the password
     * @param newRole the role
     */
    public Account(final String newUn,
                   final String newP, final String newRole) {
        this.username = newUn;
        this.password = newP;
        this.role = newRole;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }
}
