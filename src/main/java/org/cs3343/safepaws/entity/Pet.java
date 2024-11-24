
package org.cs3343.safepaws.entity;

/**
 * Represents a pet with various attributes such as name,
 * species, breed, age,
 * weight, gender, activity level,
 * and health status.
 */
public final class Pet {
    /**
     * The index for healthstatus.
     */
    private static final int INDEX_HEALTH = 3;

    /**
     * The maximum length for certain attributes.
     */
    private static final int MAX_LEN = 30;

    /**
     * The maximum value for certain numeric attributes.
     */
    private static final int MAX_VAL = 10;

    /**
     * The ID of the pet.
     */
    private int id;

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
     * Constructs a new Pet instance.
     *
     * @param nm      the name of the pet
     * @param spec    the species of the pet
     * @param br      the breed of the pet
     * @param gen     the gender of the pet
     * @param numeric an array of numeric attributes
     * (age, weight, activity level, health status)
     */
    public Pet(final String nm, final String spec,
            final String br, final String gen,
            final int[] numeric) {
        this.name = nm;
        this.species = spec;
        this.breed = br;
        this.gender = gen;
        this.age = numeric[0];
        this.weight = numeric[1];
        this.activityLevel = numeric[2];
        this.healthStatus = numeric[INDEX_HEALTH];
    }
    


    /**
     * Copy constructor for creating a new Pet object that is a copy of the given Pet object.
     *
     * @param other the Pet object to copy
     */
    public Pet(Pet other) {
        this.id = other.id;
        this.activityLevel = other.activityLevel;
        this.age = other.age;
        this.weight = other.weight;
        this.name = other.name;
        this.healthStatus = other.healthStatus;
        this.species = other.species;
        this.breed = other.breed;
        this.gender = other.gender;
    }



    /**
     * Validates the name of the pet.
     *
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    public static boolean isValidName(final String name) {
        if (name.length() > MAX_LEN) {
            return false;
        }
        return true;
    }

    /**
     * Validates the species of the pet.
     *
     * @param species the species to validate
     * @return true if the species is valid, false otherwise
     */
    public static boolean isValidSpecies(final String species) {
        if (species.length() > MAX_LEN) {
            return false;
        }
        return true;
    }

    /**
     * Validates the breed of the pet.
     *
     * @param breed the breed to validate
     * @return true if the breed is valid, false otherwise
     */
    public static boolean isValidBreed(final String breed) {
        if (breed.length() > MAX_LEN) {
            return false;
        }
        return true;
    }

    /**
     * Validates the age of the pet.
     *
     * @param age the age to validate
     * @return true if the age is valid, false otherwise
     */
    public static boolean isValidAge(final int age) {
        if (age < 0) {
            return false;
        }
        return true;
    }

    /**
     * Validates the weight of the pet.
     *
     * @param weight the weight to validate
     * @return true if the weight is valid, false otherwise
     */
    public static boolean isValidWeight(final int weight) {
        if (weight < 0) {
            return false;
        }
        return true;
    }

    /**
     * Validates the gender of the pet.
     *
     * @param gender the gender to validate
     * @return true if the gender is valid, false otherwise
     */
    public static boolean isValidGender(final String gender) {
        if ("m".equals(gender) || "f".equals(gender)) {
            return true;
        }
        return false;
    }

    /**
     * Validates the activity level of the pet.
     *
     * @param activityLevel the activity level to validate
     * @return true if the activity level is valid, false otherwise
     */
    public static boolean isValidActivityLevel(final int activityLevel) {
        if (activityLevel < 0 || activityLevel > MAX_VAL) {
            return false;
        }
        return true;
    }

    /**
     * Validates the health status of the pet.
     *
     * @param healthStatus the health status to validate
     * @return true if the health status is valid, false otherwise
     */
    public static boolean isValidHealthStatus(final int healthStatus) {
        if (healthStatus < 0 || healthStatus > MAX_VAL) {
            return false;
        }
        return true;
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
     * Gets the ID of the pet.
     *
     * @return the ID of the pet
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the pet.
     *
     * @param newId the new ID of the pet
     */
    public void setId(final int newId) {
        this.id = newId;
    }

}
