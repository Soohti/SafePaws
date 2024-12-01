package org.cs3343.safepaws.entity;

public class ShelterLocation implements Cloneable {
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
     */
    public ShelterLocation() {
        this.id = -1;
        this.xValue = -1;
        this.yValue = -1;
    }

    /**
     * Gets the id of the shelter.
     *
     * @return the id of the shelter
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the xValue-coordinate of the location.
     *
     * @return the xValue-coordinate of the location
     */
    public double getXValue() {
        return this.xValue;
    }

    /**
     * Gets the yValue-coordinate of the location.
     *
     * @return the yValue-coordinate of the location
     */
    public double getYValue() {
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

    /**
     * Returns a clone of the LocationPoint.
     *
     * @return a deep copy of the LocationPoint
     */
    @Override
    public ShelterLocation clone() {
        try {
            return (ShelterLocation) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
