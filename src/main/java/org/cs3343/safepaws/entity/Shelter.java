package org.cs3343.safepaws.entity;

/**
 *
 */
public class Shelter extends Account {
    /**
     * The profile of the member.
     */
    private final LocationPoint locationPoint;
    /**
     * Creates a new admin with the given username, password, and role.
     *
     * @param username the username of the admin
     * @param password the password of the admin
     * @param role     the role of the admin
     * @param thisLocationPoint the location of the shelter
     */
    public Shelter(final String username,
                   final String password, final String role,
                   final LocationPoint thisLocationPoint) {
        super(username, password, role);
        this.locationPoint = thisLocationPoint;
    }

    /**
     * Gets the location point of the shelter.
     * @return the location point of the shelter
     */
    public LocationPoint getLocationPoint() {
        return locationPoint;
    }
}
