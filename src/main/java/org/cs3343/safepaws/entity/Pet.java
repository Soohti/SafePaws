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
     * Constructs a new Pet instance.
     *
     * @param pId     the ID of the pet
     * @param nm      the name of the pet
     * @param spec    the species of the pet
     * @param br      the breed of the pet
     * @param gen     the gender of the pet
     * @param sta     the adoption state of the pet
     * @param numeric an array of numeric attributes
     *                (age, weight, activity level, health status)
     */
    public Pet(final int pId, final String nm, final String spec,
               final String br, final String gen,
               final int[] numeric, final int sta) {
        this.id = pId;
        this.name = nm;
        this.species = spec;
        this.breed = br;
        this.gender = gen;
        this.age = numeric[0];
        this.weight = numeric[1];
        this.activityLevel = numeric[2];
        this.healthStatus = numeric[INDEX_HEALTH];
        this.state = sta;
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
