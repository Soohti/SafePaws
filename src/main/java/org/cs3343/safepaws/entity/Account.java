package org.cs3343.safepaws.entity;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Represents an account with username, password, role, and id.
 */
public class Account {
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
    
    public static boolean isValidUsername(String username) {
        if (username.length() < 8 || username.length() > 30) {
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 8 || password.length() > 16) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidRole(String role) {
        if("A".equals(role)||"M".equals(role)||"S".equals(role)) {return true;}
        return false;
    }

    /**
     * Instantiates a new account with the specified username, password, and role.
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
     * @param id the new id
     */
    public void setId(final int id) {
        this.id = id;
    }
}
