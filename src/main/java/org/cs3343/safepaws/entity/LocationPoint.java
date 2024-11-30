package org.cs3343.safepaws.entity;

/**
 * Represents the  location in a two-dimensional space.
 */
public final class LocationPoint {

    /**
     * The xValue-coordinate of the location.
     */
    private final double xValue;

    /**
     * The yValue-coordinate of the location.
     */
    private final double yValue;

    /**
     * Initializes a new instance of the LocationPoint class with the specified
     * xValue and yValue coordinates.
     *
     * @param newXValue the xValue-coordinate of the location
     * @param newYValue the yValue-coordinate of the location
     */
    public LocationPoint(final double newXValue, final double newYValue) {
        this.xValue = newXValue;
        this.yValue = newYValue;
    }
    /**
     * Initializes a new instance of the LocationPoint class with the specified
     * xValue and yValue coordinates.
     *
     */
    public LocationPoint() {
        this.xValue = -1;
        this.yValue = -1;
    }

    /**
     * Gets the xValue-coordinate of the location.
     *
     * @return the xValue-coordinate of the location
     */
    public double getxValue() {
        return this.xValue;
    }

    /**
     * Gets the yValue-coordinate of the location.
     *
     * @return the yValue-coordinate of the location
     */
    public double getyValue() {
        return this.yValue;
    }

    /**
     * Calculates the Euclidean distance to another LocationPoint.
     *
     * @param other the other LocationPoint to calculate the distance to
     * @return the distance to the other LocationPoint
     */
    public double distanceTo(final LocationPoint other) {
        return Math.sqrt(
                Math.pow(this.xValue - other.xValue, 2.0) + Math.pow(this.yValue
                        - other.yValue, 2.0
                ));
    }

    @Override
    public String toString() {
        return "(" + this.xValue + ", " + this.yValue + ")";
    }
}
