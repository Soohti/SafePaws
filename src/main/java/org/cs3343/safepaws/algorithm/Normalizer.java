package org.cs3343.safepaws.algorithm;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public final class Normalizer implements Algorithm {
    /**
     * The normalized to original.
     */
    private HashMap<Integer, Integer> normalizedToOriginal;
    /**
     * The original to be normalized.
     */
    private HashMap<Integer, Integer> originalToNormalized;

    /**
     * Initializes the normalizer.
     */
    public void init() {
        normalizedToOriginal = new HashMap<>();
        originalToNormalized = new HashMap<>();
    }

    /**
     * Normalizes the vector.
     *
     * @param vector The vector.
     */
    public void normalize(final Vector<Integer> vector) {

        Set<Integer> sortedSet = new TreeSet<>(vector);

        int i = 0;
        for (Integer value : sortedSet) {
            i++;
            normalizedToOriginal.put(i, value);
            originalToNormalized.put(value, i);
        }

    }

    /**
     * Normalizes the value.
     *
     * @param value The value.
     * @return The normalized value.
     */
    public Integer getOriginal(final int value) {
        return normalizedToOriginal.get(value);
    }

    /**
     * Normalizes the value.
     *
     * @param value The value.
     * @return The normalized value.
     */
    public Integer getNormalized(final int value) {
        return originalToNormalized.get(value);
    }

    /**
     * Gets the size.
     *
     * @return The size.
     */
    public Integer getSize() {
        return normalizedToOriginal.size();
    }
}
