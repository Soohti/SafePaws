package org.cs3343.safepaws.entity;
/**
 * Represents a member profile with various attributes.
 */
public final class MemberProfile {
    /**
     * The id of the member.
     */
    private int id;

    /**
     * The extroversion level of the member.
     */
    private int extroversionLevel;

    /**
     * The daily activity level of the member.
     */
    private int dailyActivityLevel;

    /**
     * The house size of the member.
     */
    private int houseSize;

    /**
     * The work hours of the member.
     */
    private int workHours;

    /**
     * The number of family members.
     */
    private int numberOfFamilyMembers;

    /**
     * The previous pet experience of the member.
     */
    private int previousPetExperience;

    /**
     * The financial budget of the member.
     */
    private int financialBudget;

    /**
     * The preferred species of the member.
     */
    private String preferredSpecies;

    /**
     * The preferred breed of the member.
     */
    private String preferredBreed;

    /**
     * The gender of the member.
     */
    private String gender;

    /**
     * Constructs a new MemberProfile using the Builder.
     *
     * @param builder the builder to construct the MemberProfile
     */
    private MemberProfile(final Builder builder) {
        this.id = builder.id;
        this.extroversionLevel = builder.extroversionLevel;
        this.dailyActivityLevel = builder.dailyActivityLevel;
        this.houseSize = builder.houseSize;
        this.workHours = builder.workHours;
        this.numberOfFamilyMembers = builder.numberOfFamilyMembers;
        this.previousPetExperience = builder.previousPetExperience;
        this.financialBudget = builder.financialBudget;
        this.preferredSpecies = builder.preferredSpecies;
        this.preferredBreed = builder.preferredBreed;
        this.gender = builder.gender;
    }

    /**
     * Gets the id of the member.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the extroversion level of the member.
     *
     * @return the extroversion level
     */
    public int getExtroversionLevel() {
        return extroversionLevel;
    }

    /**
     * Gets the daily activity level of the member.
     *
     * @return the daily activity level
     */
    public int getDailyActivityLevel() {
        return dailyActivityLevel;
    }

    /**
     * Gets the house size of the member.
     *
     * @return the house size
     */
    public int getHouseSize() {
        return houseSize;
    }

    /**
     * Gets the work hours of the member.
     *
     * @return the work hours
     */
    public int getWorkHours() {
        return workHours;
    }

    /**
     * Gets the number of family members.
     *
     * @return the number of family members
     */
    public int getNumberOfFamilyMembers() {
        return numberOfFamilyMembers;
    }

    /**
     * Gets the previous pet experience of the member.
     *
     * @return the previous pet experience
     */
    public int getPreviousPetExperience() {
        return previousPetExperience;
    }

    /**
     * Gets the financial budget of the member.
     *
     * @return the financial budget
     */
    public int getFinancialBudget() {
        return financialBudget;
    }

    /**
     * Gets the preferred species of the member.
     *
     * @return the preferred species
     */
    public String getPreferredSpecies() {
        return preferredSpecies;
    }

    /**
     * Gets the preferred breed of the member.
     *
     * @return the preferred breed
     */
    public String getPreferredBreed() {
        return preferredBreed;
    }

    /**
     * Gets the gender of the member.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Builder class for constructing MemberProfile instances.
     */
    public static class Builder {
        /**
         * The id of the member.
         */
        private int id;

        /**
         * The extroversion level of the member.
         */
        private int extroversionLevel;

        /**
         * The daily activity level of the member.
         */
        private int dailyActivityLevel;

        /**
         * The house size of the member.
         */
        private int houseSize;

        /**
         * The work hours of the member.
         */
        private int workHours;

        /**
         * The number of family members.
         */
        private int numberOfFamilyMembers;

        /**
         * The previous pet experience of the member.
         */
        private int previousPetExperience;

        /**
         * The financial budget of the member.
         */
        private int financialBudget;

        /**
         * The preferred species of the member.
         */
        private String preferredSpecies;

        /**
         * The preferred breed of the member.
         */
        private String preferredBreed;

        /**
         * The gender of the member.
         */
        private String gender;

        /**
         * Sets the id of the member.
         *
         * @param newId the id
         * @return the builder
         */
        public Builder setId(final int newId) {
            this.id = newId;
            return this;
        }

        /**
         * Sets the extroversion level of the member.
         *
         * @param newExtroversionLevel the extroversion level
         * @return the builder
         */
        public Builder setExtroversionLevel(final int newExtroversionLevel) {
            this.extroversionLevel = newExtroversionLevel;
            return this;
        }

        /**
         * Sets the daily activity level of the member.
         *
         * @param newDailyActivityLevel the daily activity level
         * @return the builder
         */
        public Builder setDailyActivityLevel(final int newDailyActivityLevel) {
            this.dailyActivityLevel = newDailyActivityLevel;
            return this;
        }

        /**
         * Sets the house size of the member.
         *
         * @param newHouseSize the house size
         * @return the builder
         */
        public Builder setHouseSize(final int newHouseSize) {
            this.houseSize = newHouseSize;
            return this;
        }

        /**
         * Sets the work hours of the member.
         *
         * @param newWorkHours the work hours
         * @return the builder
         */
        public Builder setWorkHours(final int newWorkHours) {
            this.workHours = newWorkHours;
            return this;
        }

        /**
         * Sets the number of family members.
         *
         * @param newNumberOfFamilyMembers the number of family members
         * @return the builder
         */
        public Builder setNumberOfFamilyMembers(
                final int newNumberOfFamilyMembers) {
            this.numberOfFamilyMembers = newNumberOfFamilyMembers;
            return this;
        }

        /**
         * Sets the previous pet experience of the member.
         *
         * @param newPreviousPetExperience the previous pet experience
         * @return the builder
         */
        public Builder setPreviousPetExperience(
                final int newPreviousPetExperience) {
            this.previousPetExperience = newPreviousPetExperience;
            return this;
        }

        /**
         * Sets the financial budget of the member.
         *
         * @param newFinancialBudget the financial budget
         * @return the builder
         */
        public Builder setFinancialBudget(final int newFinancialBudget) {
            this.financialBudget = newFinancialBudget;
            return this;
        }

        /**
         * Sets the preferred species of the member.
         *
         * @param newPreferredSpecies the preferred species
         * @return the builder
         */
        public Builder setPreferredSpecies(final String newPreferredSpecies) {
            this.preferredSpecies = newPreferredSpecies;
            return this;
        }

        /**
         * Sets the preferred breed of the member.
         *
         * @param newPreferredBreed the preferred breed
         * @return the builder
         */
        public Builder setPreferredBreed(final String newPreferredBreed) {
            this.preferredBreed = newPreferredBreed;
            return this;
        }

        /**
         * Sets the gender of the member.
         *
         * @param newGender the gender
         * @return the builder
         */
        public Builder setGender(final String newGender) {
            this.gender = newGender;
            return this;
        }

        /**
         * Builds and returns a MemberProfile instance.
         *
         * @return the member profile
         */
        public MemberProfile build() {
            return new MemberProfile(this);
        }
    }
}
