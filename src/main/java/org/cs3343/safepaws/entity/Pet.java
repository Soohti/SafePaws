package org.cs3343.safepaws.entity;

public class Pet {

	public int activityLevel;
	public int age;
	public String size;
	public double healthStatus;
	public String breed;
	public String gender;
	public Pet(String breed, int age, String size, String gender, 
            int activityLevel, int healthStatus) {
	    this.breed = breed;
	    this.age = age;
	    this.size = size;
	    this.gender = gender;
	    this.activityLevel = activityLevel;
	    this.healthStatus = healthStatus;
	}

}
