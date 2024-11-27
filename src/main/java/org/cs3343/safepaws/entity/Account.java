package org.cs3343.safepaws.entity;

import org.cs3343.safepaws.handler.LoginHandler;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Represents an account with username, password, role, and id.
 */
public abstract class Account {
    /**
     * The id of the account.
     */
    private final int id;
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
     * Instantiates a new account with the
     * specified username, password, and role.
     * @param newId   the id
     * @param newUn   the username
     * @param newP    the password
     * @param newRole the role
     */
    public Account(final int newId, final String newUn,
                   final String newP, final String newRole) {
        this.id = newId;
        this.username = newUn;
        this.password = newP;
        this.role = newRole;
    }
    /**
     * Gets the username.
     *
     * @return the id
     */
    public int getId() {
        return id;
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
