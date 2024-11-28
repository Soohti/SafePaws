package org.cs3343.safepaws.entity;

import org.cs3343.safepaws.util.OneToOne;

/**
 * Represents a member in the SafePaws system.
 */
public final class Member extends Account {

    /**
     * The profile of the member.
     */
    @OneToOne(columnName = "Id", tableName = "MEMBER_PROFILE")
    private MemberProfile profile;

    /**
     * Constructs a new Member using the builder.
     *
     * @param builder the builder to construct the member
     */
    private Member(final Builder builder) {
        super(builder);
        this.profile = builder.profile;
    }

    /**
     * Gets the profile of the member.
     *
     * @return the profile of the member
     */
    public MemberProfile getProfile() {
        return profile;
    }

    /**
     * Builder class for constructing Member instances.
     */
    public static class Builder extends Account.Builder<Builder> {
        /**
         * The profile of the member.
         */
        @OneToOne(columnName = "Id", tableName = "MEMBER_PROFILE")
        private MemberProfile profile;

        /**
         * Sets the profile of the member.
         *
         * @param newProfile the profile
         * @return the builder
         */
        public Builder setProfile(final MemberProfile newProfile) {
            this.profile = newProfile;
            return this;
        }

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
        public Member build() {
            return new Member(this);
        }
    }
}
