package org.cs3343.safepaws.entity;

/**
 * Represents a member profile with various attributes.
 */
public class MemberProfile {
    /**
     * Level of extroversion.
     */
    public int extroversionLevel;

    /**
     * Level of daily activity.
     */
    public int dailyActivityLevel;

    /**
     * Size of the house.
     */
    public int houseSize;

    /**
     * Number of work hours.
     */
    public int workHours;

    /**
     * Number of family members.
     */
    public int numberOfFamilyMembers;

    /**
     * Experience with previous pets.
     */
    public int previousPetExperience;

    /**
     * Financial budget.
     */
    public int financialBudget;

    /**
     * Preferred species.
     */
    public String preferredSpecies;

    /**
     * Preferred breed.
     */
    public String preferredBreed;

    /**
     * Gender.
     */
    public String gender;

    /**
     * Constructs a MemberProfile with specified attributes.
     *
     * @param species the preferred species
     * @param breed the preferred breed
     * @param gen the gender
     * @param numeric an array of numeric attributes
     */
    public MemberProfile(final String species, final String breed, 
                         final String gen, final int[] numeric) {
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
     * Gets the preferred species.
     *
     * @return the preferred species
     */
    public String getPreferredSpecies() {
        return preferredSpecies;
    }

    /**
     * Gets the extroversion level.
     *
     * @return the extroversion level
     */
    public int getExtroversionLevel() {
        return extroversionLevel;
    }

    /**
     * Gets the daily activity level.
     *
     * @return the daily activity level
     */
    public int getDailyActivityLevel() {
        return dailyActivityLevel;
    }

    /**
     * Gets the house size.
     *
     * @return the house size
     */
    public int getHouseSize() {
        return houseSize;
    }

    /**
     * Gets the work hours.
     *
     * @return the work hours
     */
    public int getWorkHours() {
        return workHours;
    }

    /**
     * Gets the number of family members.
     *
     * @return the number of family members
     */
    public int getNumberOfFamilyMembers() {
        return numberOfFamilyMembers;
    }

    /**
     * Gets the previous pet experience.
     *
     * @return the previous pet experience
     */
    public int getPreviousPetExperience() {
        return previousPetExperience;
    }

    /**
     * Gets the financial budget.
     *
     * @return the financial budget
     */
    public int getFinancialBudget() {
        return financialBudget;
    }

    /**
     * Gets the preferred breed.
     *
     * @return the preferred breed
     */
    public String getPreferredBreed() {
        return preferredBreed;
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }
}
