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
     * @param species 
     * @param breed 
     * @param gen 
     * @param numeric 
     * @param preferredSpecies
     * @param preferredBreed
     * @param gender
     * @param numericAttributes
     */
    public MemberProfile(final String species, final String breed, final String gen, final int[] numeric) {
        this.preferredSpecies = species;
        this.preferredBreed = breed;
        this.gender = gen;
        this.extroversionLevel = numeric[0];
        this.dailyActivityLevel = numeric[1];
        this.houseSize = numeric[2];
        this.workHours = numeric[3];
        this.numberOfFamilyMembers = numeric[4];
        this.previousPetExperience = numeric[5];
        this.financialBudget = numeric[6];
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
