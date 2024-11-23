
package org.cs3343.safepaws.entity;

/**
 * Represents a member profile with various attributes.
 */
public class MemberProfile {
    /**
     * The index for workhour.
     */
    private static final int INDEX_WORK = 3;

    /**
     * The index for family member.
     */
    private static final int INDEX_FAM = 4;

    /**
     * The index for pet experience.
     */
    private static final int INDEX_EXP = 5;

    /**
     * The index for budget.
     */
    private static final int INDEX_BUDG = 6;

    /**
     * The extroversion level of the member.
     */
    private int extroversionLevel;

    /**
     * The daily activity level of the member.
     */
    private int dailyActivityLevel;

    /**
     * The house size of the member.
     */
    private int houseSize;

    /**
     * The work hours of the member.
     */
    private int workHours;

    /**
     * The number of family members.
     */
    private int numberOfFamilyMembers;

    /**
     * The previous pet experience of the member.
     */
    private int previousPetExperience;

    /**
     * The financial budget of the member.
     */
    private int financialBudget;

    /**
     * The preferred species of the member.
     */
    private String preferredSpecies;

    /**
     * The preferred breed of the member.
     */
    private String preferredBreed;

    /**
     * The gender of the member.
     */
    private String gender;

    /**
     * Constructs a new MemberProfile with the specified attributes.
     *
     * @param species the preferred species
     * @param breed   the preferred breed
     * @param gen     the gender
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
        this.workHours = numeric[INDEX_WORK];
        this.numberOfFamilyMembers = numeric[INDEX_FAM];
        this.previousPetExperience = numeric[INDEX_EXP];
        this.financialBudget = numeric[INDEX_BUDG];
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
     * @param newPreferredSpecies the new preferred species
     */
    public void setPreferredSpecies(final
                                    String newPreferredSpecies) {
        this.preferredSpecies = newPreferredSpecies;
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
     * @param newExtroversionLevel the new extroversion level
     */
    public void setExtroversionLevel(final
                                     int newExtroversionLevel) {
        this.extroversionLevel = newExtroversionLevel;
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
     * @param newDailyActivityLevel the new daily activity level
     */
    public void setDailyActivityLevel(final
                                      int newDailyActivityLevel) {
        this.dailyActivityLevel = newDailyActivityLevel;
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
     * @param newHouseSize the new house size
     */
    public void setHouseSize(final int newHouseSize) {
        this.houseSize = newHouseSize;
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
     * @param newWorkHours the new work hours
     */
    public void setWorkHours(final int newWorkHours) {
        this.workHours = newWorkHours;
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
     * @param newNumberOfFamilyMembers the new number of family members
     */
    public void setNumberOfFamilyMembers(final
                                         int newNumberOfFamilyMembers) {
        this.numberOfFamilyMembers = newNumberOfFamilyMembers;
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
     * @param newPreviousPetExperience the new previous pet experience
     */
    public void setPreviousPetExperience(final
                                         int newPreviousPetExperience) {
        this.previousPetExperience = newPreviousPetExperience;
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
     * @param newFinancialBudget the new financial budget
     */
    public void setFinancialBudget(final int newFinancialBudget) {
        this.financialBudget = newFinancialBudget;
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
     * @param newPreferredBreed the new preferred breed
     */
    public void setPreferredBreed(final
                                  String newPreferredBreed) {
        this.preferredBreed = newPreferredBreed;
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
     * @param newGender the new gender
     */
    public void setGender(final String newGender) {
        this.gender = newGender;
    }
}
