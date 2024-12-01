package org.cs3343.safepaws.entity;

public class ShelterLocation {
    /**
     * The id of the shelter.
     */
    private int id;
    /**
     * The xValue-coordinate of the shelter.
     */
    private final double xValue;

    /**
     * The yValue-coordinate of the shelter.
     */
    private final double yValue;

    /**
     * Initializes a new instance of the LocationPoint class with the specified
     * xValue and yValue coordinates.
     *
     * @param newId     the id of the shelter
     * @param newXValue the xValue-coordinate of the shelter
     * @param newYValue the yValue-coordinate of the shelter
     */
    public ShelterLocation(final int newId,
            final double newXValue, final double newYValue) {
        this.id = newId;
        this.xValue = newXValue;
        this.yValue = newYValue;
    }
    /**
     * Initializes a new instance of the LocationPoint class with the specified
     * xValue and yValue coordinates.
     *
     */
    public ShelterLocation() {
        this.id     = -1;
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
     * Returns a string representation of the LocationPoint.
     *
     * @return a string representation of the LocationPoint
     */
    @Override
    public String toString() {
        return "(" + this.xValue + ", " + this.yValue + ")";
    }
}
