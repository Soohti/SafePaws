package org.cs3343.safepaws.entity;

/**
 * Represents a pet with various attributes such as name,
 * species, breed, age,
 * weight, gender, activity level,
 * and health status.
 */
public final class Pet {
    /**
     * The index for health status.
     */
    private static final int INDEX_HEALTH = 3;

    /**
     * The maximum length for certain attributes.
     */
    private static final int MAX_LEN = 30;

    /**
     * The ID of the pet.
     */
    private final int id;

    /**
     * The activity level of the pet.
     */
    private final int activityLevel;

    /**
     * The age of the pet.
     */
    private final int age;

    /**
     * The weight of the pet.
     */
    private final int weight;

    /**
     * The name of the pet.
     */
    private final String name;

    /**
     * The health status of the pet.
     */
    private final int healthStatus;

    /**
     * The species of the pet.
     */
    private final String species;

    /**
     * The breed of the pet.
     */
    private final String breed;

    /**
     * The gender of the pet.
     */
    private final String gender;

    /**
     * The adoption state of the pet.
     */
    private final int state;

    /**
     * Private constructor, accessible only by the Builder.
     *
     * @param builder the Builder instance
     */
    private Pet(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.species = builder.species;
        this.breed = builder.breed;
        this.gender = builder.gender;
        this.age = builder.age;
        this.weight = builder.weight;
        this.activityLevel = builder.activityLevel;
        this.healthStatus = builder.healthStatus;
        this.state = builder.state;
    }

    /**
     * Builder class for constructing Pet instances.
     */
    public static class Builder {
        /**
         * The unique identifier for the animal.
         */
        private int id;

        /**
         * The name of the animal.
         */
        private String name;

        /**
         * The species of the animal (e.g., Dog, Cat, Bird).
         */
        private String species;

        /**
         * The breed of the animal (e.g., Labrador, Siamese, Budgie).
         */
        private String breed;

        /**
         * The gender of the animal (e.g., Male, Female).
         */
        private String gender;

        /**
         * The age of the animal in years.
         */
        private int age;

        /**
         * The weight of the animal in pounds or kilograms.
         */
        private int weight;

        /**
         * The activity level of the animal, represented as an integer.
         * Higher values indicate a more active animal.
         */
        private int activityLevel;

        /**
         * The health status of the animal, represented as an integer.
         * Higher values indicate better health.
         */
        private int healthStatus;

        /**
         * The current state of the animal (e.g., available, adopted, reserved).
         * Represented as an integer where each value corresponds
         * to a specific state.
         */
        private int state;

        /**
         * Sets the ID of the pet.
         *
         * @param newId the ID to set
         * @return this {@code Builder} object
         */
        public Builder setId(final int newId) {
            this.id = newId;
            return this;
        }

        /**
         * Sets the name of the pet.
         *
         * @param newName the name to set
         * @return this {@code Builder} object
         */
        public Builder setName(final String newName) {
            this.name = newName;
            return this;
        }

        /**
         * Sets the species of the pet.
         *
         * @param newSpecies the species to set
         * @return this {@code Builder} object
         */
        public Builder setSpecies(final String newSpecies) {
            this.species = newSpecies;
            return this;
        }

        /**
         * Sets the breed of the pet.
         *
         * @param newBreed the breed to set
         * @return this {@code Builder} object
         */
        public Builder setBreed(final String newBreed) {
            this.breed = newBreed;
            return this;
        }

        /**
         * Sets the gender of the pet.
         *
         * @param newGender the gender to set
         * @return this {@code Builder} object
         */
        public Builder setGender(final String newGender) {
            this.gender = newGender;
            return this;
        }

        /**
         * Sets the age of the pet.
         *
         * @param newAge the age to set
         * @return this {@code Builder} object
         */
        public Builder setAge(final int newAge) {
            this.age = newAge;
            return this;
        }

        /**
         * Sets the weight of the pet.
         *
         * @param newWeight the weight to set
         * @return this {@code Builder} object
         */
        public Builder setWeight(final int newWeight) {
            this.weight = newWeight;
            return this;
        }

        /**
         * Sets the activity level of the pet.
         *
         * @param newActivityLevel the activity level to set
         * @return this {@code Builder} object
         */
        public Builder setActivityLevel(final int newActivityLevel) {
            this.activityLevel = newActivityLevel;
            return this;
        }

        /**
         * Sets the health status of the pet.
         *
         * @param newHealthStatus the health status to set
         * @return this {@code Builder} object
         */
        public Builder setHealthStatus(final int newHealthStatus) {
            this.healthStatus = newHealthStatus;
            return this;
        }

        /**
         * Sets the state of the pet.
         *
         * @param newState the state to set
         * @return this {@code Builder} object
         */
        public Builder setState(final int newState) {
            this.state = newState;
            return this;
        }

        /**
         * Builds a new {@link Pet} instance with the attributes
         * set in this builder.
         * @return a new {@link Pet} instance
         */
        public Pet build() {
            return new Pet(this);
        }
    }

    /**
     * Validates the name of the pet.
     *
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    public static boolean isValidName(final String name) {
        return name.length() <= MAX_LEN;
    }

    /**
     * Validates the species of the pet.
     *
     * @param species the species to validate
     * @return true if the species is valid, false otherwise
     */
    public static boolean isValidSpecies(final String species) {
        return species.length() <= MAX_LEN;
    }

    /**
     * Validates the breed of the pet.
     *
     * @param breed the breed to validate
     * @return true if the breed is valid, false otherwise
     */
    public static boolean isValidBreed(final String breed) {
        return breed.length() <= MAX_LEN;
    }

    /**
     * Validates the gender of the pet.
     *
     * @param gender the gender to validate
     * @return true if the gender is valid, false otherwise
     */
    public static boolean isValidGender(final String gender) {
        return "m".equals(gender) || "f".equals(gender);
    }

    /**
     * Gets the name of the pet.
     *
     * @return the name of the pet
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the species of the pet.
     *
     * @return the species of the pet
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Gets the breed of the pet.
     *
     * @return the breed of the pet
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Gets the age of the pet.
     *
     * @return the age of the pet
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the weight of the pet.
     *
     * @return the weight of the pet
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the gender of the pet.
     *
     * @return the gender of the pet
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the activity level of the pet.
     *
     * @return the activity level of the pet
     */
    public int getActivityLevel() {
        return activityLevel;
    }

    /**
     * Gets the health status of the pet.
     *
     * @return the health status of the pet
     */
    public int getHealthStatus() {
        return healthStatus;
    }

    /**
     * Gets the adoption state of the pet.
     *
     * @return the adoption state of the pet
     */
    public int getState() {
        return state;
    }

    /**
     * Gets the ID of the pet.
     *
     * @return the ID of the pet
     */
    public int getId() {
        return id;
    }
}
