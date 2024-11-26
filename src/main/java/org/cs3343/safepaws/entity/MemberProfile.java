package org.cs3343.safepaws.entity;

/**
 * Represents a member profile with various attributes.
 */
public class MemberProfile {
    /**
     * The index for extroversion level.
     */
    private static final int INDEX_EXTROVERSION = 0;

    /**
     * The index for daily activity level.
     */
    private static final int INDEX_DAILY_ACTIVITY = 1;

    /**
     * The index for house size.
     */
    private static final int INDEX_HOUSE_SIZE = 2;

    /**
     * The index for work hours.
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
     * The numeric attributes of the member.
     */
    private int[] numericAttributes;

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
    public MemberProfile(final String species,
                         final String breed, final String gen,
                         final int[] numeric) {
        this.preferredSpecies = species;
        this.preferredBreed = breed;
        this.gender = gen;
        this.numericAttributes = numeric.clone();
    }

    /**
     * Constructs a new Member by copying the specified Member.
     *
     * @param other the Member to copy
     */
    public MemberProfile(final MemberProfile other) {
        this.preferredSpecies = other.getPreferredSpecies();
        this.preferredBreed = other.getPreferredBreed();
        this.gender = other.getGender();
        this.numericAttributes = other.getNumericAttributes().clone();
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
    public void setPreferredSpecies(final String newPreferredSpecies) {
        this.preferredSpecies = newPreferredSpecies;
    }


    /**
     * Gets the numeric attributes.
     *
     * @return the numeric attributes
     */
    public int[] getNumericAttributes() {
        return numericAttributes.clone();
    }


    /**
     * Gets the extroversion level.
     *
     * @return the extroversion level
     */
    public int getExtroversionLevel() {
        return numericAttributes[INDEX_EXTROVERSION];
    }

    /**
     * Sets the extroversion level.
     *
     * @param newExtroversionLevel the new extroversion level
     */
    public void setExtroversionLevel(final int newExtroversionLevel) {
        this.numericAttributes[INDEX_EXTROVERSION] = newExtroversionLevel;
    }

    /**
     * Gets the daily activity level.
     *
     * @return the daily activity level
     */
    public int getDailyActivityLevel() {
        return numericAttributes[INDEX_DAILY_ACTIVITY];
    }

    /**
     * Sets the daily activity level.
     *
     * @param newDailyActivityLevel the new daily activity level
     */
    public void setDailyActivityLevel(final int newDailyActivityLevel) {
        this.numericAttributes[INDEX_DAILY_ACTIVITY] = newDailyActivityLevel;
    }

    /**
     * Gets the house size.
     *
     * @return the house size
     */
    public int getHouseSize() {
        return numericAttributes[INDEX_HOUSE_SIZE];
    }

    /**
     * Sets the house size.
     *
     * @param newHouseSize the new house size
     */
    public void setHouseSize(final int newHouseSize) {
        this.numericAttributes[INDEX_HOUSE_SIZE] = newHouseSize;
    }

    /**
     * Gets the work hours.
     *
     * @return the work hours
     */
    public int getWorkHours() {
        return numericAttributes[INDEX_WORK];
    }

    /**
     * Sets the work hours.
     *
     * @param newWorkHours the new work hours
     */
    public void setWorkHours(final int newWorkHours) {
        this.numericAttributes[INDEX_WORK] = newWorkHours;
    }

    /**
     * Gets the number of family members.
     *
     * @return the number of family members
     */
    public int getNumberOfFamilyMembers() {
        return numericAttributes[INDEX_FAM];
    }

    /**
     * Sets the number of family members.
     *
     * @param newNumberOfFamilyMembers the new number of family members
     */
    public void setNumberOfFamilyMembers(final int newNumberOfFamilyMembers) {
        this.numericAttributes[INDEX_FAM] = newNumberOfFamilyMembers;
    }

    /**
     * Gets the previous pet experience.
     *
     * @return the previous pet experience
     */
    public int getPreviousPetExperience() {
        return numericAttributes[INDEX_EXP];
    }

    /**
     * Sets the previous pet experience.
     *
     * @param newPreviousPetExperience the new previous pet experience
     */
    public void setPreviousPetExperience(final int newPreviousPetExperience) {
        this.numericAttributes[INDEX_EXP] = newPreviousPetExperience;
    }

    /**
     * Gets the financial budget.
     *
     * @return the financial budget
     */
    public int getFinancialBudget() {
        return numericAttributes[INDEX_BUDG];
    }

    /**
     * Sets the financial budget.
     *
     * @param newFinancialBudget the new financial budget
     */
    public void setFinancialBudget(final int newFinancialBudget) {
        this.numericAttributes[INDEX_BUDG] = newFinancialBudget;
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
    public void setPreferredBreed(final String newPreferredBreed) {
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
