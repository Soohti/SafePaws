package org.cs3343.safepaws.entity;

import java.util.Iterator;
import java.util.List;

/**
 * Represents a shelter that can contain multiple locations where
 * animals can be found.
 */
public class Shelter {

    /**
     * A list of locations where the shelter is situated.
     * Each location is represented by a {@link LocationPoint} object.
     */
    private final List<LocationPoint> shelterLocationPoints;

    /**
     * Initializes a new instance of the Shelter class with the specified
     * list of shelter locations.
     *
     * @param shelterLocationsValue a list of {@link LocationPoint} objects
     * representing the shelter locations
     */
    public Shelter(final List<LocationPoint> shelterLocationsValue) {
        this.shelterLocationPoints = shelterLocationsValue;
    }

    /**
     * Displays the list of shelter locations to the standard output.
     * Each location is printed on a new line with its x and y coordinates.
     */
    public void displayShelters() {
        System.out.println("Shelter Locations:");
        Iterator<LocationPoint> iterator =
                this.shelterLocationPoints.iterator();

        while (iterator.hasNext()) {
            LocationPoint locationPoint = iterator.next();
            System.out.println("X: " + locationPoint.getX() + ", Y: "
                    + locationPoint.getY());
        }
    }
}
