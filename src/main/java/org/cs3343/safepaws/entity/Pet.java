package org.cs3343.safepaws.entity;

/**
 * Represents a pet with various attributes such as name,
 * species, breed, age, weight, gender, activity level, and health status.
 */
public final class Pet {


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
        this.healthStatus = 0; // Default value for health status
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
     * Constructs a new {@code Pet} entity with physical and
     * behavioral attributes.
     *
     */
    public Pet() {
        this.id = -1;
        this.name = ""; // Default value for name
        this.species = ""; // Default value for species
        this.breed = ""; // Default value for breed
        this.gender = ""; // Default value for gender
        this.age = 0;
        this.weight = 0;
        this.activityLevel = 0;
        this.healthStatus = 0;
        this.state = 0;
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
