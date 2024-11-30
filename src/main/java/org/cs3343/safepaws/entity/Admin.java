package org.cs3343.safepaws.entity;

/**
 * Represents an admin account in the SafePaws system.
 * <p>
 * The Admin class inherits from the Account class and may include additional
 * functionalities specific to admin users in the SafePaws system.
 * </p>
 */
public class Admin extends Account implements Cloneable {

    /**
     * Constructs a new Admin account with the given parameters.
     *
     * @param newId       the unique identifier of the admin account
     * @param newUsername the username for the admin account
     * @param newPassword the password for the admin account
     * @param newRole     the role of the account, typically "admin"
     */
    public Admin(final int newId, final String newUsername,
                 final String newPassword, final String newRole) {
        super(newId, newUsername, newPassword, newRole);
    }
    /**
     * Clone method that creates and returns a deep copy of this {@link Admin}
     * object.
     *
     * @return A deep copy of this {@link Admin} object.
     * @throws CloneNotSupportedException if the object's class does not
     *                                  support the {@code Cloneable} interface.
     */
    @Override
    public Admin clone() throws CloneNotSupportedException {
        return (Admin) super.clone();
    }
}
