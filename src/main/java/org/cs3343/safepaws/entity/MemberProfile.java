package org.cs3343.safepaws.entity;

/**
 * Represents a member profile with various attributes.
 */
public class MemberProfile {
    /**
     * Level of extroversion.
     */
    private int extroversionLevel;

    /**
     * Level of daily activity.
     */
    private int dailyActivityLevel;

    /**
     * Size of the house.
     */
    private int houseSize;

    /**
     * Number of work hours.
     */
    private int workHours;

    /**
     * Number of family members.
     */
    private int numberOfFamilyMembers;

    /**
     * Experience with previous pets.
     */
    private int previousPetExperience;

    /**
     * Financial budget.
     */
    private int financialBudget;

    /**
     * Preferred species.
     */
    private String preferredSpecies;

    /**
     * Preferred breed.
     */
    private String preferredBreed;

    /**
     * Gender.
     */
    private String gender;

    /**
     * Constructs a MemberProfile with specified attributes.
     *
     * @param species the preferred species
     * @param breed   the preferred breed
     * @param gen     the gender
     * @param numeric an array of numeric attributes
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
     * Gets the preferred species.
     *
     * @return the preferred species
     */
    public String getPreferredSpecies() {
        return preferredSpecies;
    }

    /**
     * Sets the preferred species.
     *
     * @param preferredSpecies the preferred species
     */
    public void setPreferredSpecies(String preferredSpecies) {
        this.preferredSpecies = preferredSpecies;
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
     * Sets the extroversion level.
     *
     * @param extroversionLevel the extroversion level
     */
    public void setExtroversionLevel(int extroversionLevel) {
        this.extroversionLevel = extroversionLevel;
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
     * Sets the daily activity level.
     *
     * @param dailyActivityLevel the daily activity level
     */
    public void setDailyActivityLevel(int dailyActivityLevel) {
        this.dailyActivityLevel = dailyActivityLevel;
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
     * Sets the house size.
     *
     * @param houseSize the house size
     */
    public void setHouseSize(int houseSize) {
        this.houseSize = houseSize;
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
     * Sets the work hours.
     *
     * @param workHours the work hours
     */
    public void setWorkHours(int workHours) {
        this.workHours = workHours;
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
     * Sets the number of family members.
     *
     * @param numberOfFamilyMembers the number of family members
     */
    public void setNumberOfFamilyMembers(int numberOfFamilyMembers) {
        this.numberOfFamilyMembers = numberOfFamilyMembers;
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
     * Sets the previous pet experience.
     *
     * @param previousPetExperience the previous pet experience
     */
    public void setPreviousPetExperience(int previousPetExperience) {
        this.previousPetExperience = previousPetExperience;
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
     * Sets the financial budget.
     *
     * @param financialBudget the financial budget
     */
    public void setFinancialBudget(int financialBudget) {
        this.financialBudget = financialBudget;
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
     * Sets the preferred breed.
     *
     * @param preferredBreed the preferred breed
     */
    public void setPreferredBreed(String preferredBreed) {
        this.preferredBreed = preferredBreed;
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
}
