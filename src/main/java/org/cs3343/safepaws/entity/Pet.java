package org.cs3343.safepaws.entity;

/**
 * Represents a pet with various attributes such as name, species, breed, age,
 * weight, gender, activity level, and health status.
 */
public final class Pet {

    /**
     * The ID of the pet.
     */
    private int id;

    /**
     * The activity level of the pet.
     */
    private int activityLevel;

    /**
     * The age of the pet.
     */
    private int age;

    /**
     * The weight of the pet.
     */
    private int weight;

    /**
     * The name of the pet.
     */
    private String name;

    /**
     * The health status of the pet.
     */
    private int healthStatus;

    /**
     * The species of the pet.
     */
    private String species;

    /**
     * The breed of the pet.
     */
    private String breed;

    /**
     * The gender of the pet.
     */
    private String gender;

    /**
     * Constructs a new Pet instance.
     *
     * @param nm        the name of the pet
     * @param spec      the species of the pet
     * @param br        the breed of the pet
     * @param ag        the age of the pet
     * @param wt        the weight of the pet
     * @param gen       the gender of the pet
     * @param actLevel  the activity level of the pet
     * @param heaStatus the health status of the pet
     */
    public Pet(final String nm, final String spec, final String br, final int ag, final int wt, final String gen,
            final int actLevel, final int heaStatus) {
        this.name = nm;
        this.species = spec;
        this.breed = br;
        this.age = ag;
        this.weight = wt;
        this.gender = gen;
        this.activityLevel = actLevel;
        this.healthStatus = heaStatus;
    }

    public static boolean isValidName(String name) {
        if (name.length() > 30) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidSpecies(String species) {
        if (species.length() > 30) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidBreed(String breed) {
        if (breed.length() > 30) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidAge(int age) {
        if (age < 0) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidWeight(int weight) {
        if (weight < 0) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidGender(String gender) {
        if ("m".equals(gender)||"f".equals(gender)) {
            return true;
        }
        return false;
    }
    
    public static boolean isValidActivityLevel(int activityLevel) {
        if (activityLevel < 0||activityLevel > 10) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidHealthStatus(int healthStatus) {
        if (healthStatus < 0|| healthStatus > 10) {
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
