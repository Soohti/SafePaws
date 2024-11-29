package org.cs3343.safepaws.entity;

/**
 * Represents an account with username, password, role, and id.
 * <p>
 * This class serves as a base class for all types of accounts, providing common
 * fields and methods for managing account details.
 * </p>
 */
public class Account {

    private int id;
    private String username;
    private String password;
    private String role;

    /**
     * Constructs a new Account with the given parameters.
     *
     * @param newId       the unique identifier of the account
     * @param newUsername the username for the account
     * @param newPassword the password for the account
     * @param newRole     the role of the account (e.g., "admin", "user")
     */
    public Account(final int newId, final String newUsername, final String newPassword, final String newRole) {
        this.id = newId;
        this.username = newUsername;
        this.password = newPassword;
        this.role = newRole;
    }

    /**
     * Constructs a new Account with the given parameters.
     *
     * @param newUsername the username for the account
     * @param newPassword the password for the account
     * @param newRole     the role of the account (e.g., "admin", "user")
     */
    public Account(final String newUsername, final String newPassword, final String newRole) {
        this.id = -1; //default value
        this.username = newUsername;
        this.password = newPassword;
        this.role = newRole;
    }

    /**
     * Gets the unique identifier of the account.
     *
     * @return the account's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the account.
     *
     * @param newId the new id to set
     */
    public void setId(final int newId) {
        this.id = newId;
    }

    /**
     * Gets the username of the account.
     *
     * @return the account's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the account.
     *
     * @param newUsername the new username to set
     */
    public void setUsername(final String newUsername) {
        this.username = newUsername;
    }

    /**
     * Gets the password of the account.
     *
     * @return the account's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the account.
     *
     * @param newPassword the new password to set
     */
    public void setPassword(final String newPassword) {
        this.password = newPassword;
    }

    /**
     * Gets the role of the account.
     *
     * @return the account's role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the account.
     *
     * @param newRole the new role to set
     */
    public void setRole(final String newRole) {
        this.role = newRole;
    }
}
