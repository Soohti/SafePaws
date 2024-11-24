
package org.cs3343.safepaws.entity;

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
    private String username;

    /**
     * The password of the account.
     */
    private String password;

    /**
     * The role of the account.
     */
    private String role;

    /**
     * The id of the account.
     */
    private int id;

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
     * @return true if the username is valid, false otherwise
     */
    public static boolean isValidUsername(final String username) {
        if (username.length() < MIN_USERNAME_LENGTH
                || username.length() > MAX_USERNAME_LENGTH) {
            return false;
        }
        return true;
    }

    /**
     * Validates the password.
     *
     * @param password the password to validate
     * @return true if the password is valid, false otherwise
     */
    public static boolean isValidPassword(final String password) {
        if (password.length() < MIN_PASSWORD_LENGTH
                || password.length() > MAX_PASSWORD_LENGTH) {
            return false;
        }
        return true;
    }

    /**
     * Validates the role.
     *
     * @param role the role to validate
     * @return true if the role is valid, false otherwise
     */
    public static boolean isValidRole(final String role) {
        if ("A".equals(role) || "M".equals(role) || "S".equals(role)) {
            return true;
        }
        return false;
    }

    /**
     * Instantiates a new account with the
     * specified username, password, and role.
     *
     * @param newUn the username
     * @param newP the password
     * @param newRole the role
     */
    public Account(final String newUn,
            final String newP, final String newRole) {
        this.username = newUn;
        this.password = newP;
        this.role = newRole;
    }

    /**
     * Copy constructor to create a new account
     * by copying the fields of an existing account.
     *
     * @param other the account to copy
     */
    public Account(final Account other) {
        this.username = other.username;
        this.password = other.password;
        this.role = other.role;
        this.id = other.id;
    }


    /**
     * Instantiates a new account with default values.
     */
    public Account() {
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
     * Sets the username.
     *
     * @param newUsername the new username
     */
    public void setUsername(final
            String newUsername) {
        this.username = newUsername;
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
     * Sets the password.
     *
     * @param newPass the new password
     */
    public void setPassword(final String newPass) {
        this.password = newPass;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param newRole the new role
     */
    public void setRole(final String newRole) {
        this.role = newRole;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param newId the new id
     */
    public void setId(final int newId) {
        this.id = newId;
    }
}
