package org.cs3343.safepaws.entity;

public final class MatchingPair {
    /**
     * first.
     */
    private final int first;
    /**
     * second.
     */
    private final double second;

    /**
     * Creates a new MatchingPair.
     *
     * @param iFirst  first
     * @param dSecond second
     */
    public MatchingPair(final int iFirst, final double dSecond) {
        this.first = iFirst;
        this.second = dSecond;
    }

    /**
     * Get the first.
     *
     * @return first
     */
    public int getFirst() {
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
