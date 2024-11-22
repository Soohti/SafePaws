package org.cs3343.safepaws.entity;

public final class FlowEdge {
    /**
     * v.
     */
    private final int v;
    /**
     * capacity.
     */
    private int capacity;
    /**
     * weight.
     */
    private final double weight;
    /**
     * next.
     */
    private final int next;

    /**
     * Constructor for FlowEdge.
     *
     * @param iV        iV
     * @param iCapacity iCapacity
     * @param dWeight   dWeight
     * @param iNext     iNext
     */
    public FlowEdge(final int iV, final int iCapacity, final double dWeight,
                    final int iNext) {
        this.v = iV;
        this.capacity = iCapacity;
        this.weight = dWeight;
        this.next = iNext;
    }

    /**
     * Getter for v.
     *
     * @return v
     */
    public int getV() {
        return v;
    }

    /**
     * Getter for capacity.
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Setter for capacity.
     *
     * @param iCapacity iCapacity
     */
    public void setCapacity(final int iCapacity) {
        this.capacity = iCapacity;
    }

    /**
     * Getter for weight.
     *
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Getter for next.
     *
     * @return next
     */
    public int getNext() {
        return next;
    }
}
