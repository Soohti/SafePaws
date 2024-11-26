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
    /**
     * Constructs a new Admin by copying the specified Admin.
     *
     * @param other the Admin to copy
     */
    public Admin(final Admin other) {
        super(other.getUsername(), other.getPassword(), other.getRole());
        this.setId(other.getId());
    }

}
