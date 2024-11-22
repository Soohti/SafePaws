package org.cs3343.safepaws.entity;

/**
 *
 */
public class Admin extends Account {

    /**
     * @param username
     * @param password
     * @param role
     */
    public Admin(final String username,
            final String password, final String role) {
        super(username, password, role);
    }

}
