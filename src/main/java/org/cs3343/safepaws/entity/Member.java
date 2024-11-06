package org.cs3343.safepaws.entity;

public class Member extends Account{

	public MemberProfile profile;
	
	
	public Member(String username, String password, String role) {
		super(username, password, role);
	}
	
	public void SetProfile(String preferredBreed, String gender, int extroversionLevel, int dailyActivityLevel,
            int houseSize, int workHours, int numberOfFamilyMembers, 
            int previousPetExperience, int financialBudget) {
		 profile=new MemberProfile(preferredBreed,gender,extroversionLevel,dailyActivityLevel,houseSize,workHours,numberOfFamilyMembers,previousPetExperience,financialBudget);
	}

}
