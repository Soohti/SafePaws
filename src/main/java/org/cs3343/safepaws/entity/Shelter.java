package org.cs3343.safepaws.entity;

import org.cs3343.safepaws.util.OneToOne;
import org.cs3343.safepaws.util.TableSchema;

/**
 * Represents a shelter with a username, password, role, and location point.
 */
public class Shelter extends Account {
    /**
     * The location point of the shelter.
     */
    @OneToOne(columnName = TableSchema.Column.Id,
            tableName = TableSchema.Name.SHELTER_LOCATION)
    private ShelterLocation locationPoint;

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
                   final ShelterLocation newLocationPoint) {
        super(newId, newUsername, newPassword, newRole);
        this.locationPoint = newLocationPoint;
    }

    /**
     * Gets the location point of the shelter.
     *
     * @return the location point of the shelter
     */
    public ShelterLocation getLocationPoint() {
        return locationPoint;
    }

    /**
     * Sets the location point of the shelter.
     *
     * @param location the location point of the shelter
     */
    public void setLocationPoint(final ShelterLocation location) {
        this.locationPoint = location;
    }

    /**
     * Clones the Shelter object.
     *
     * @return a deep copy of the Shelter object
     */
    @Override
    public Shelter clone() {
        Shelter account = (Shelter) super.clone();
        account.setLocationPoint(this.locationPoint.clone());
        return account;
    }
}
