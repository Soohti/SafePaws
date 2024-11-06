package org.cs3343.safepaws.entity;

public class Pet {

	private int id;
	public int activityLevel;
	public int age;
	public int size;
	public int healthStatus;
	public String breed;
	public String gender;
	
	public Pet(String breed, int age, int size, String gender, 
            int activityLevel, int healthStatus) {
	    this.breed = breed;
	    this.age = age;
	    this.size = size;
	    this.gender = gender;
	    this.activityLevel = activityLevel;
	    this.healthStatus = healthStatus;
	}
	public String getBreed() {
		return breed;
	}
	public int getAge() {
		return age;
	}
	public int getSize() {
		return size;
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
