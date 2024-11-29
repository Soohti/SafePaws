package org.cs3343.safepaws.entity;

/**
 * Represents a pet with various attributes such as name,
 * species, breed, age, weight, gender, activity level, and health status.
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
     * Constructs a new {@code Pet} entity with identity
     * and descriptive attributes.
     *
     * @param newId      the ID of the pet
     * @param newName    the name of the pet
     * @param newSpecies the species of the pet
     * @param newBreed   the breed of the pet
     * @param newGender  the gender of the pet
     */
    public Pet(final int newId,
               final String newName,
               final String newSpecies,
               final String newBreed,
               final String newGender) {
        this.id = newId;
        this.name = newName;
        this.species = newSpecies;
        this.breed = newBreed;
        this.gender = newGender;
        this.age = 0; // Default value for age
        this.weight = 0; // Default value for weight
        this.activityLevel = 0; // Default value for activity level
        this.healthStatus = INDEX_HEALTH; // Default value for health status
        this.state = 0; // Default value for state
    }

    /**
     * Constructs a new {@code Pet} entity with physical and
     * behavioral attributes.
     *
     * @param newId            the ID of the pet
     * @param newAge           the age of the pet
     * @param newWeight        the weight of the pet
     * @param newActivityLevel the activity level of the pet
     * @param newHealthStatus  the health status of the pet
     * @param newState         the adoption state of the pet
     */
    public Pet(final int newId,
               final int newAge,
               final int newWeight,
               final int newActivityLevel,
               final int newHealthStatus,
               final int newState) {
        this.id = newId;
        this.name = ""; // Default value for name
        this.species = ""; // Default value for species
        this.breed = ""; // Default value for breed
        this.gender = ""; // Default value for gender
        this.age = newAge;
        this.weight = newWeight;
        this.activityLevel = newActivityLevel;
        this.healthStatus = newHealthStatus;
        this.state = newState;
    }

    /**
     * Validates the name of the pet.
     *
     * @param newName the name to validate
     * @return true if the name is valid, false otherwise
     */
    public static boolean isValidName(final String newName) {
        return newName.length() <= MAX_LEN;
    }

    /**
     * Validates the species of the pet.
     *
     * @param newSpecies the species to validate
     * @return true if the species is valid, false otherwise
     */
    public static boolean isValidSpecies(final String newSpecies) {
        return newSpecies.length() <= MAX_LEN;
    }

    /**
     * Validates the breed of the pet.
     *
     * @param newBreed the breed to validate
     * @return true if the breed is valid, false otherwise
     */
    public static boolean isValidBreed(final String newBreed) {
        return newBreed.length() <= MAX_LEN;
    }

    /**
     * Validates the gender of the pet.
     *
     * @param newGender the gender to validate
     * @return true if the gender is valid, false otherwise
     */
    public static boolean isValidGender(final String newGender) {
        return "m".equalsIgnoreCase(newGender)
                || "f".equalsIgnoreCase(newGender);
    }

    /**
     * Gets the ID of the pet.
     *
     * @return the ID of the pet
     */
    public int getId() {
        return id;
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
}
