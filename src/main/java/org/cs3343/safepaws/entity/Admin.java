package org.cs3343.safepaws.entity;

/**
 * Represents an admin in the SafePaws system.
 */
public final class Admin extends Account {

    /**
     * Constructs a new Admin using the builder.
     *
     * @param builder the builder to construct the admin
     */
    private Admin(final Builder builder) {
        super(builder);
    }

    /**
     * Builder class for constructing Admin instances.
     */
    public static class Builder extends Account.Builder<Builder> {

        /**
         * Returns the builder instance.
         *
         * @return the builder instance
         */
        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Builds and returns an Admin instance.
         *
         * @return the admin
         */
        @Override
        public Admin build() {
            return new Admin(this);
        }
    }
}
