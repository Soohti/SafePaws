
package org.cs3343.safepaws.entity;

/**
 * Represents a pet with various attributes such as name, species, breed,
 * age, weight, gender, activity level, and health status.
 */
public final class Pet {

    private int id;
    public int activityLevel;
    public int age;
    public int weight;
    public String name;
    public int healthStatus;
    public String species;
    public String breed;
    public String gender;

    /**
     * Constructs a new Pet instance.
     *
     * @param nm the name of the pet
     * @param spec the species of the pet
     * @param br the breed of the pet
     * @param ag the age of the pet
     * @param wt the weight of the pet
     * @param gen the gender of the pet
     * @param actLevel the activity level of the pet
     * @param heaStatus the health status of the pet
     */
    public Pet(final String nm, final String spec, final String br, int ag,
               int wt, String gen, int actLevel, int heaStatus) {
        this.name = nm;
        this.species = spec;
        this.breed = br;
        this.age = ag;
        this.weight = wt;
        this.gender = gen;
        this.activityLevel = actLevel;
        this.healthStatus = heaStatus;
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
     * @param id the new ID of the pet
     */
    public void setId(final int id) {
        this.id = id;
    }

}
