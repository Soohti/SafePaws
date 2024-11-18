package org.cs3343.safepaws.entity;

/**
 * Represents the  location in a two-dimensional space.
 */
public class LocationPoint {

    /**
     * The x-coordinate of the location.
     */
    private final double x;

    /**
     * The y-coordinate of the location.
     */
    private final double y;

    /**
     * Initializes a new instance of the LocationPoint class with the specified
     * x and y coordinates.
     *
     * @param xValue the x-coordinate of the location
     * @param yValue the y-coordinate of the location
     */
    public LocationPoint(final double xValue, final double yValue) {
        this.x = xValue;
        this.y = yValue;
    }

    /**
     * Gets the x-coordinate of the location.
     *
     * @return the x-coordinate of the location
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate of the location.
     *
     * @return the y-coordinate of the location
     */
    public double getY() {
        return this.y;
    }

    /**
     * Calculates the Euclidean distance to another LocationPoint.
     *
     * @param other the other LocationPoint to calculate the distance to
     * @return the distance to the other LocationPoint
     */
    public double distanceTo(final LocationPoint other) {
        return Math.sqrt(
                Math.pow(this.x - other.x, 2.0) + Math.pow(this.y - other.y, 2.0
                ));
    }
}
