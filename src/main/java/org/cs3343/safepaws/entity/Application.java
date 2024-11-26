package org.cs3343.safepaws.entity;

import org.cs3343.safepaws.util.DbManager;

/**
 * Represents an application with user, pet, state, and id.
 */
public class Application {
    /**
     * The user associated with the application.
     */
    private final Member user;

    /**
     * The pet associated with the application.
     */
    private final Pet pet;

    /**
     * The state of the application.
     */
    private final State state;

    /**
     * The id of the application.
     */
    private int id;

    /**
     * Constructs an Application with the specified user, pet, and state.
     *
     * @param account the user account
     * @param p       the pet
     * @param st      the state
     */
    public Application(final Member account, final Pet p, final State st) {
        this.user = account;
        this.pet = p;
        this.state = st;
    }

    /**
     * Checks if the given state is valid.
     *
     * @param state the state to check
     * @return true if the state is valid, false otherwise
     */
    public static boolean isValidState(final State state) {
        return state == State.APPROVED || state == State.REJECTED;
    }

    /**
     * Checks if the given application ID is valid.
     *
     * @param aid the application ID to check
     * @return true if the application ID is valid,
     * false otherwise
     */
    public static boolean isValidAid(final int aid) {
        return DbManager.selectApplication(aid) != null;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public Member getUser() {
        return user;
    }

    /**
     * Gets the pet.
     *
     * @return the pet
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public State getState() {
        return state;
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
     * Sets the id.
     *
     * @param newId the id to set
     */
    public void setId(final int newId) {
        this.id = newId;
    }

    /**
     * Checks if the given pet ID is valid.
     *
     * @param pid the pet ID to check
     * @return 0 if the pet ID is invalid, 1 if the pet is adopted,
     * 2 if the pet is not adopted
     */
    public static int isValidPid(final int pid) {
        if (DbManager.selectPetById(pid) == null) {
            return 0;
        } else if (DbManager.selectPetById(pid).getState() == 1) {
            return 1;
        }
        return 2;
    }

    public enum State {
        /**
         * The application is pending.
         */
        PENDING,

        /**
         * The application is approved.
         */
        APPROVED,

        /**
         * The application is rejected.
         */
        REJECTED
    }
}
