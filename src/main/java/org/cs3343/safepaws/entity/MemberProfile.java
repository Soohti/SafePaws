package org.cs3343.safepaws.entity;

public class MemberProfile {
	public int extroversionLevel;
	public int dailyActivityLevel;
	public int houseSize;
	public int workHours;
	public int numberOfFamilyMembers;
	public int previousPetExperience;
	public int financialBudget;
	public String preferredSpecies;
	public String preferredBreed;
	public String gender;
	
	public MemberProfile(String preferredSpecies, String preferredBreed, String gender, int extroversionLevel, int dailyActivityLevel,
            int houseSize, int workHours, int numberOfFamilyMembers, 
            int previousPetExperience, int financialBudget) {
		 this.preferredSpecies=preferredSpecies;
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

	public String getPreferredSpecies() {
		return preferredSpecies;
	}
	
	public int getExtroversionLevel() {
		return extroversionLevel;
	}

	public int getDailyActivityLevel() {
		return dailyActivityLevel;
	}

	public int getHouseSize() {
		return houseSize;
	}

	public int getWorkHours() {
		return workHours;
	}

	public int getNumberOfFamilyMembers() {
		return numberOfFamilyMembers;
	}

	public int getPreviousPetExperience() {
		return previousPetExperience;
	}

	public int getFinancialBudget() {
		return financialBudget;
	}

	public String getPreferredBreed() {
		return preferredBreed;
	}

	public String getGender() {
		return gender;
	}
	
	
	
}
