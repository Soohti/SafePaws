package org.cs3343.safepaws.entity;

/**
 *
 */
public class Admin extends Account {

    /**
     * Creates a new admin with the given username, password, and role.
     *
     * @param username the username of the admin
     * @param password the password of the admin
     * @param role     the role of the admin
     */
    public Admin(final String username,
                 final String password, final String role) {
        super(username, password, role);
    }
}
