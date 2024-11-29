package org.cs3343.safepaws.entity;

/**
 * Represents a shelter with a username, password, role, and location point.
 */
public final class Shelter extends Account {
    /**
     * The location point of the shelter.
     */
    private final LocationPoint locationPoint;

    /**
     * Constructs a new Shelter with the specified parameters.
     *
     * @param newId            the id of the shelter
     * @param newUsername      the username of the shelter
     * @param newPassword      the password of the shelter
     * @param newRole          the role of the shelter
     * @param newLocationPoint the location point of the shelter
     */
    public Shelter(final int newId,
                   final String newUsername,
                   final String newPassword,
                   final String newRole,
                   final LocationPoint newLocationPoint) {
        super(newId, newUsername, newPassword, newRole);
        this.locationPoint = newLocationPoint;
    }

    /**
     * Gets the location point of the shelter.
     *
     * @return the location point of the shelter
     */
    public LocationPoint getLocationPoint() {
        return locationPoint;
    }
}
