package org.cs3343.safepaws.entity;

import org.cs3343.safepaws.handler.ReadApplicationHandler;
import org.cs3343.safepaws.util.OneToOne;

/**
 * Represents an application with user, pet, state, and id.
 */
public class Application {
    /**
     * The user associated with the application.
     */
    @OneToOne(columnName = "Mid", tableName = "ACCOUNT")
    private Member user;

    /**
     * The pet associated with the application.
     */
    @OneToOne(columnName = "PId", tableName = "PET")
    private Pet pet;

    /**
     * The state of the application.
     */
    private State state;

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
     * @param iState the integer state to check
     * @return true if the state is valid, false otherwise
     */
    public static boolean isValidState(final int iState) {
        return iState == State.APPROVED.ordinal()
                || iState == State.REJECTED.ordinal();
    }

    /**
     * Checks if the given application ID is valid.
     *
     * @param aid the application ID to check
     * @return true if the application ID is valid,
     * false otherwise
     */
    public static boolean isValidAid(final int aid) {
        return ReadApplicationHandler.getInstance()
                .findApplicationByAid(aid) != null;
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
     * Sets the Pet.
     *
     * @param newPet the Pet to set
     */
    public void setState(final Pet newPet) {
        this.pet = newPet;
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
     * Sets the state.
     *
     * @param newState the state to set
     */
    public void setState(final State newState) {
        this.state = newState;
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
