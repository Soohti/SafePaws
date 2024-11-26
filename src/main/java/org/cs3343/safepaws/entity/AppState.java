package org.cs3343.safepaws.entity;

/**
 * Represents the state of an application.
 */
public enum AppState {
    /**
     * The application is pending.
     */
    PENDING(0),

    /**
     * The application is approved.
     */
    APPROVED(1),

    /**
     * The application is rejected.
     */
    REJECTED(2);

    /**
     * The integer value associated with the state.
     */
    private final int value;

    /**
     * Constructs an AppState with the specified value.
     *
     * @param value the integer value of the state
     */
    AppState(final int val) {
        this.value = val;
    }

    /**
     * Returns the integer value of the state.
     *
     * @return the integer value of the state
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the AppState corresponding to the given integer value.
     *
     * @param state the integer value representing the state
     * @return the AppState corresponding to the given integer value, or null if the
     *         value does not match any state
     */
    public static AppState fromInt(final int state) {
        switch (state) {
        case 0:
            return PENDING;
        case 1:
            return APPROVED;
        case 2:
            return REJECTED;
        default:
            return null;
        }
    }

}
