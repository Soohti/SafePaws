package org.cs3343.safepaws.entity;

/**
 * Represents a shelter with a username, password, role, and location point.
 */
public final class Shelter extends Account {
    /**
     * The location point of the shelter.
     */
    private final LocationPoint locationPoint;

    /**
     * Constructs a new Shelter using the builder.
     *
     * @param builder the builder to construct the shelter
     */
    private Shelter(final Builder builder) {
        super(builder);
        this.locationPoint = builder.locationPoint;
    }

    /**
     * Gets the location point of the shelter.
     *
     * @return the location point of the shelter
     */
    public LocationPoint getLocationPoint() {
        return locationPoint;
    }

    /**
     * Builder class for constructing Shelter instances.
     */
    public static class Builder extends Account.Builder<Builder> {
        private LocationPoint locationPoint;

        /**
         * Sets the location point of the shelter.
         *
         * @param newLocationPoint the location point
         * @return the builder
         */
        public Builder setLocationPoint(final LocationPoint newLocationPoint) {
            this.locationPoint = newLocationPoint;
            return this;
        }
        /**
         * Returns the builder instance.
         *
         * @return the builder instance
         */
        @Override
        protected Shelter.Builder self() {
            return this;
        }

        /**
         * Builds and returns an Admin instance.
         *
         * @return the admin
         */
        @Override
        public Shelter build() {
            return new Shelter(this);
        }
    }
}
