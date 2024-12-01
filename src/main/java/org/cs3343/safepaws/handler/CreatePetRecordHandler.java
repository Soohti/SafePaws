package org.cs3343.safepaws.handler;

public final class CreatePetRecordHandler {

    private CreatePetRecordHandler() {
    }
    /**
     * The maximum length for certain attributes.
     */
    private static final int MAX_LEN = 30;
    /**
     * Validates the name of the pet.
     *
     * @param newName the name to validate
     * @return true if the name is valid, false otherwise
     */
    public static boolean isValidName(final String newName) {
        return newName.length() <= MAX_LEN;
    }

    /**
     * Validates the species of the pet.
     *
     * @param newSpecies the species to validate
     * @return true if the species is valid, false otherwise
     */
    public static boolean isValidSpecies(final String newSpecies) {
        return newSpecies.length() <= MAX_LEN;
    }

    /**
     * Validates the breed of the pet.
     *
     * @param newBreed the breed to validate
     * @return true if the breed is valid, false otherwise
     */
    public static boolean isValidBreed(final String newBreed) {
        return newBreed.length() <= MAX_LEN;
    }

    /**
     * Validates the gender of the pet.
     *
     * @param newGender the gender to validate
     * @return true if the gender is valid, false otherwise
     */
    public static boolean isValidGender(final String newGender) {
        return "m".equalsIgnoreCase(newGender)
                || "f".equalsIgnoreCase(newGender);
    }
}
