package org.cs3343.safepaws.entity;

public class MatchingPair {
    /**
     * first.
     */
    private final String first;
    /**
     * second.
     */
    private final double second;

    /**
     * Creates a new MatchingPair.
     *
     * @param sFirst  first
     * @param dSecond second
     */
    public MatchingPair(final String sFirst, final double dSecond) {
        this.first = sFirst;
        this.second = dSecond;
    }

    /**
     * Get the first.
     *
     * @return first
     */
    public String getFirst() {
        return first;
    }

    /**
     * Get the second.
     *
     * @return second
     */
    public double getSecond() {
        return second;
    }
}
