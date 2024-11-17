package org.cs3343.safepaws.entity;

/**
 * 
 */
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

    /**
     * @param preferredSpecies
     * @param preferredBreed
     * @param gender
     * @param numericAttributes
     */
    public MemberProfile(String preferredSpecies, String preferredBreed, String gender, int[] numericAttributes) {
        if (numericAttributes == null || numericAttributes.length != 7) {
            throw new IllegalArgumentException("numericAttributes " + "array must have a length of 7");
        }
        this.preferredSpecies = preferredSpecies;
        this.preferredBreed = preferredBreed;
        this.gender = gender;
        this.extroversionLevel = numericAttributes[0];
        this.dailyActivityLevel = numericAttributes[1];
        this.houseSize = numericAttributes[2];
        this.workHours = numericAttributes[3];
        this.numberOfFamilyMembers = numericAttributes[4];
        this.previousPetExperience = numericAttributes[5];
        this.financialBudget = numericAttributes[6];
    }

    /**
     * @return
     */
    public String getPreferredSpecies() {
        return preferredSpecies;
    }

    /**
     * @return
     */
    public int getExtroversionLevel() {
        return extroversionLevel;
    }

    /**
     * @return
     */
    public int getDailyActivityLevel() {
        return dailyActivityLevel;
    }

    /**
     * @return
     */
    public int getHouseSize() {
        return houseSize;
    }

    /**
     * @return
     */
    public int getWorkHours() {
        return workHours;
    }

    /**
     * @return
     */
    public int getNumberOfFamilyMembers() {
        return numberOfFamilyMembers;
    }

    /**
     * @return
     */
    public int getPreviousPetExperience() {
        return previousPetExperience;
    }

    /**
     * @return
     */
    public int getFinancialBudget() {
        return financialBudget;
    }

    /**
     * @return
     */
    public String getPreferredBreed() {
        return preferredBreed;
    }

    /**
     * @return
     */
    public String getGender() {
        return gender;
    }

}
