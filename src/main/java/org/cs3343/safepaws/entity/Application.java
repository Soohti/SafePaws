package org.cs3343.safepaws.entity;

import org.cs3343.safepaws.util.OneToOne;
import org.cs3343.safepaws.util.TableSchema;

/**
 * Represents an application with user, pet, state, and id.
 * <p>
 * This class stores the application details, including the user (account),
 * pet, application state, and unique application ID.
 * </p>
 */
public class Application {

    /**
     * The user associated with the application.
     */
    @OneToOne(columnName = TableSchema.Column.MId,
            tableName = TableSchema.Name.ACCOUNT)
    private Member user;

    /**
     * The pet associated with the application.
     */
    @OneToOne(columnName = TableSchema.Column.PId,
            tableName = TableSchema.Name.PET)
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
     * @param newAccount the user account
     * @param newPet     the pet
     * @param newState   the state
     */
    public Application(final Member newAccount, final Pet newPet,
                       final State newState) {
        this.user = (newAccount == null) ? null : newAccount.clone();
        this.pet = newPet;
        this.state = newState;
    }

    /**
     * Constructs an Application with the specified user, pet, and state.
     */
    public Application() {
        this.user = null;
        this.pet = null;
        this.state = State.PENDING;
    }

    /**
     * Checks if the given state is valid.
     *
     * @param newIState the integer state to check
     * @return true if the state is valid, false otherwise
     */
    public static boolean isValidState(final int newIState) {
        return newIState == State.APPROVED.ordinal()
                || newIState == State.REJECTED.ordinal();
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public Member getUser() {
        return user == null ? null : user.clone();
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
    public void setPet(final Pet newPet) {
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

    /**
     * Enum representing the possible states of an application.
     */
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
