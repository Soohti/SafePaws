package org.cs3343.safepaws.entity;

/**
 * Represents an account with username, password, role, and id.
 */
public abstract class Account {
    /**
     * The id of the account.
     */
    private final int id;
    /**
     * The username of the account.
     */
    private final String username;

    /**
     * The password of the account.
     */
    private final String password;

    /**
     * The role of the account.
     */
    private final String role;

    /**
     * Instantiates a new account using the builder.
     *
     * @param builder the builder to construct the account
     */
    protected Account(final Builder<?> builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.role = builder.role;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Builder class for constructing Account instances.
     * @param <T> the subclass of Account
     */
    public abstract static class Builder<T extends Builder<T>> {
        /**
         * The id of the account.
         */
        private int id;
        /**
         * The username of the account.
         */
        private String username;

        /**
         * The password of the account.
         */
        private String password;

        /**
         * The role of the account.
         */
        private String role;

        /**
         * Sets the id of the account.
         *
         * @param newId the id
         * @return the builder
         */
        public T setId(final int newId) {
            this.id = newId;
            return self();
        }

        /**
         * Sets the username of the account.
         *
         * @param newUsername the username
         * @return the builder
         */
        public T setUsername(final String newUsername) {
            this.username = newUsername;
            return self();
        }

        /**
         * Sets the password of the account.
         *
         * @param newPassword the password
         * @return the builder
         */
        public T setPassword(final String newPassword) {
            this.password = newPassword;
            return self();
        }

        /**
         * Sets the role of the account.
         *
         * @param newRole the role
         * @return the builder
         */
        public T setRole(final String newRole) {
            this.role = newRole;
            return self();
        }

        /**
         * Returns the builder instance.
         *
         * @return the builder instance
         */
        protected abstract T self();

        /**
         * Builds and returns an Account instance.
         *
         * @return the account
         */
        public abstract Account build();
    }
}
