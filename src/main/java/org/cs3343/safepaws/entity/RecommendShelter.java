package org.cs3343.safepaws.entity;

/**
 * Represents a shelter that can contain multiple locations where
 * animals can be found.
 */
public class RecommendShelter {

    /**
     * A list of locations where the shelter is situated.
     * Each location is represented by a {@link LocationPoint} object.
     */
    private final LocationPoint shelterLocationPoint;

    /**
     * Initializes a new instance of the RecommendShelter
     * class with the specified list of shelter locations.
     *
     * @param shelterLocationValue a list of {@link LocationPoint} objects
     *                             representing the shelter locations
     */
    public RecommendShelter(final LocationPoint shelterLocationValue) {
        this.shelterLocationPoint = shelterLocationValue;
    }

    /**
     * Displays the list of shelter locations to the standard output.
     * Each location is printed on a new line with its x and y coordinates.
     *
     * @return the string of location point
     */
    public String toString() {
        return String.format("X: " + shelterLocationPoint.getxValue() + ", Y: "
                + shelterLocationPoint.getyValue() + "\n");
    }
}
