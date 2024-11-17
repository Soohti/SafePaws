package org.cs3343.safepaws.entity;

public class Pet {

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
    public Pet(String name, String species, String breed, int age, int weight, String gender, int activityLevel,
            int healthStatus) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.healthStatus = healthStatus;
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
	public void setId(int id) {
		this.id=id;
	}
	
}
