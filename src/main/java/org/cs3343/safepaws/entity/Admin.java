package org.cs3343.safepaws.entity;

import java.util.Objects;

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
    
    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false;
        Admin otherAccount = (Admin) obj;
        return super.equals(otherAccount);
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
