package org.cs3343.safepaws.entity;

import org.cs3343.safepaws.util.DbManager;

/**
 * Represents an application with user, pet, state, and id.
 */
public class Application {
    /**
     * The user associated with the application.
     */
    private Member user;

    /**
     * The pet associated with the application.
     */
    private Pet pet;

    /**
     * The state of the application.
     */
    private int state;

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
    public Application(final Member account, final Pet p, final int st) {
        this.user = new Member(account);
        this.pet = new Pet(p);
        this.state = st;
    }

    /**
     * Checks if the given state is valid.
     *
     * @param state the state to check
     * @return true if the state is valid, false otherwise
     */
    public static boolean isValidState(final int state) {
        if (state == 1 || state == 2) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the given application ID is valid.
     *
     * @param aid the application ID to check
     * @return true if the application ID is valid,
     * false otherwise
     */
    public static boolean isValidAid(final int aid) {
        if (DbManager.selectApplication(aid) == null
                || DbManager.selectApplication(aid).state != 0) {
            return false;
        }
        return true;
    }

    /**
     * Gets the user.
     *
     * @return a copy of the user
     */
    public Member getUser() {
        return new Member(user);
    }

    /**
     * Sets the user.
     *
     * @param newUser the user to set
     */
    public void setUser(final Member newUser) {
        this.user = new Member(newUser);
    }

    /**
     * Gets the pet.
     *
     * @return a copy of the pet
     */
    public Pet getPet() {
        return new Pet(pet);
    }

    /**
     * Sets the pet.
     *
     * @param newPet the pet to set
     */
    public void setPet(final Pet newPet) {
        this.pet = new Pet(newPet);
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param newState the state to set
     */
    public void setState(final int newState) {
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
     * Checks if the given pet ID is valid.
     *
     * @param pid the pet ID to check
     * @return true if the pet ID is valid, false otherwise
     */
    public static int isValidPid(final int pid) {
        if (DbManager.selectPetById(pid) == null) {
            return 0;
        } else if (DbManager.selectPetById(pid).getState() == 1) {
        	return 1;
        }
        return 2;
    }
}
