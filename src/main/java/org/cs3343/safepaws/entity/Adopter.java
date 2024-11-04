package org.cs3343.safepaws.entity;

public class Adopter {

	public int extroversionLevel;
	public int dailyActivityLevel;
	public int houseSize;
	public int workHours;
	public int numberOfFamilyMembers;
	public int previousPetExperience;
	public int financialBudget;
	public String preferredBreed;
	public String gender;
	public Adopter(String preferredBreed, String gender, int extroversionLevel, int dailyActivityLevel,
            int houseSize, int workHours, int numberOfFamilyMembers, 
            int previousPetExperience, int financialBudget) {
		 this.preferredBreed = preferredBreed;
		 this.extroversionLevel = extroversionLevel;
		 this.dailyActivityLevel = dailyActivityLevel;
		 this.houseSize = houseSize;
		 this.workHours = workHours;
		 this.numberOfFamilyMembers = numberOfFamilyMembers;
		 this.previousPetExperience = previousPetExperience;
		 this.financialBudget = financialBudget;
		 this.gender=gender;
	}
	
}

