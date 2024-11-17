package org.cs3343.safepaws.entity;


public class Member extends Account{

	public MemberProfile profile;
	
	public Member(String username, String password, String role) {
		super(username, password, role);
	}
	
	public Member() {
		super();
	}
	
	public void SetProfile(MemberProfile memberProfile) {
		this.profile=memberProfile;
	}
	
	public void setProfile(String preferedSpecies, String preferredBreed, String gender, int extroversionLevel, int dailyActivityLevel,
            int houseSize, int workHours, int numberOfFamilyMembers, 
            int previousPetExperience, int financialBudget) {
		 profile=new MemberProfile(preferedSpecies, preferredBreed,gender,extroversionLevel,dailyActivityLevel,houseSize,workHours,numberOfFamilyMembers,previousPetExperience,financialBudget);
	}
	
	public int getId() {
		return super.getId();
	}
	
	public void setId(int id) {
		super.setId(id);
	}
	
	public MemberProfile getProfile() {
		return profile;
	}

}
