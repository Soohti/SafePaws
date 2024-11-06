package org.cs3343.safepaws.entity;

public class Member extends Account{

	public MemberProfile profile;
	
	
	public Member(String username, String password, String role) {
		super(username, password, role);
	}
	
	public void SetProfile(String preferredBreed, String gender, int extroversionLevel, int dailyActivityLevel,
            int houseSize, int workHours, int numberOfFamilyMembers, 
            int previousPetExperience, int financialBudget) {
		 this.profile.preferredBreed = preferredBreed;
		 this.profile.extroversionLevel = extroversionLevel;
		 this.profile.dailyActivityLevel = dailyActivityLevel;
		 this.profile.houseSize = houseSize;
		 this.profile.workHours = workHours;
		 this.profile.numberOfFamilyMembers = numberOfFamilyMembers;
		 this.profile.previousPetExperience = previousPetExperience;
		 this.profile.financialBudget = financialBudget;
		 this.profile.gender=gender;
	}
	
}
