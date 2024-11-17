package org.cs3343.safepaws.entity;

import org.mindrot.jbcrypt.BCrypt;

/**
 * 
 */
public class Account {
    private String username;
    private String password;
    private String role;
    private int id;

    /**
     * Encrypt password.
     *
     * @param password the password
     * @return the string
     */
    public static String encryptPassword(final String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Instantiates a new account.
     *
     * @param username the username
     * @param password the password
     * @param role     the role
     */
    public Account(final String username, final String password, final String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * 
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
     * @param username the new username
     */
    public void setUsername(final String username) {
        this.username = username;
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
     * @param password the new password
     */
    public void setPassword(final String password) {
        this.password = password;
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
     * @param role the new role
     */
    public void setRole(final String role) {
        this.role = role;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }
}
