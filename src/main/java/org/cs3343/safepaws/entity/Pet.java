package org.cs3343.safepaws.entity;

/**
 *
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
     * @param name
     * @param species
     * @param breed
     * @param age
     * @param weight
     * @param gender
     * @param activityLevel
     * @param healthStatus
     */
    public Pet(final String nm, final String spec, final String br, int ag, int wt, String gen, int actLevel,
            int heaStatus) {
        this.name = nm;
        this.species = spec;
        this.breed = br;
        this.age = wt;
        this.weight = wt;
        this.gender = gen;
        this.activityLevel = actLevel;
        this.healthStatus = heaStatus;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public String getGender() {
        return gender;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public int getHealthStatus() {
        return healthStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

}
