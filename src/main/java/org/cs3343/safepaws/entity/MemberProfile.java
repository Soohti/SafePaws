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
     * The id of the member.
     */
    private int id;
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
     * @param thisId the id
     * @param species the preferred species
     * @param breed   the preferred breed
     * @param gen     the gender
     * @param numeric an array of numeric attributes
     */
    public MemberProfile(final int thisId,
                         final String species,
                         final String breed,
                         final String gen,
                         final int[] numeric) {
        this.id = thisId;
        this.preferredSpecies = species;
        this.preferredBreed = breed;
        this.gender = gen;
        this.numericAttributes = numeric.clone();
    }
    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
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
        return numericAttributes[INDEX_EXTROVERSION];
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
     * Gets the house size.
     *
     * @return the house size
     */
    public int getHouseSize() {
        return numericAttributes[INDEX_HOUSE_SIZE];
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
     * Gets the number of family members.
     *
     * @return the number of family members
     */
    public int getNumberOfFamilyMembers() {
        return numericAttributes[INDEX_FAM];
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
     * Gets the financial budget.
     *
     * @return the financial budget
     */
    public int getFinancialBudget() {
        return numericAttributes[INDEX_BUDG];
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
