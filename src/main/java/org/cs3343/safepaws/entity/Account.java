package org.cs3343.safepaws.entity;

/**
 * Represents an account with username, password, role, and id.
 * <p>
 * This class serves as a base class for all types of accounts, providing common
 * fields and methods for managing account details.
 * </p>
 */
public class Account {

    /**
     * The unique identifier for a user.
     * <p>
     * This field represents the primary key for identifying a user
     * in the system.
     * </p>
     */
    private int id;

    /**
     * The username of the user.
     * <p>
     * This field stores the unique username chosen by the user for
     * authentication and identification purposes.
     * </p>
     */
    private String username;

    /**
     * The password of the user.
     * <p>
     * This field stores the user's password in a secure format for
     * authentication purposes. It is recommended to use a hashed
     * representation for security.
     * </p>
     */
    private String password;

    /**
     * The role of the user.
     * <p>
     * This field indicates the user's role in the system, such as
     * "admin," "user," or other predefined roles. The role is used
     * to determine the user's access permissions.
     * </p>
     */
    private String role;


    /**
     * Constructs a new Account with the given parameters.
     *
     * @param newId       the unique identifier of the account
     * @param newUsername the username for the account
     * @param newPassword the password for the account
     * @param newRole     the role of the account (e.g., "admin", "user")
     */
    public Account(final int newId, final String newUsername,
                   final String newPassword, final String newRole) {
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
    public Account(final String newUsername, final String newPassword,
                   final String newRole) {
        this.id = -1; //default value
        this.username = newUsername;
        this.password = newPassword;
        this.role = newRole;
    }

    /**
     * Constructs a new Account with the given parameters.
     *
     */
    public Account() {
        this.id = -1; //default value
        this.username = "";
        this.password = "";
        this.role = "";
    }
    /**
     * Copy constructor for creating a new {@code Account} object that is a copy
     * of an existing {@code Account} object.
     *
     * <p>This constructor initializes a new {@code Account} instance with the
     * same ID, username, password, and role as the specified {@code account}.
     * Note that this is a shallow copy; if the original {@code account} contains
     * references to mutable objects, those references will be copied, not the
     * objects themselves.
     *
     * @param account The {@code Account} object to be copied. Must not be
     *                {@code null}.
     * @throws NullPointerException if {@code account} is {@code null}.
     */
    public Account(final Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.role = account.getRole();
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
