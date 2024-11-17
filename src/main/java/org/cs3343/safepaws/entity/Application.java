package org.cs3343.safepaws.entity;

/**
 * 
 */
public class Application {
    public Member user;
    public Pet pet;
    public int state;
    public int id;

    /**
     * @param account
     * @param pet
     * @param state
     */
    public Application(final Member account, final Pet p, final int st) {
        this.user = (Member) account;
        this.pet = p;
        this.state = st;
    }

    /**
     * @return
     */
    public Member getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(final Member user) {
        this.user = user;
    }

    /**
     * @return
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param pet
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    /**
     * @return
     */
    public int getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(final int state) {
        this.state = state;
    }
}
