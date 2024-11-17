package org.cs3343.safepaws.entity;

/**
 * 
 */
public class Member extends Account {

    /**
     * 
     */
    public MemberProfile profile;

    /**
     * @param username
     * @param password
     * @param role
     */
    public Member(String username, String password, String role) {
        super(username, password, role);
    }

    /**
     * 
     */
    public Member() {
        super();
    }

    /**
     * @param memberProfile
     */
    public void setProfile(MemberProfile memberProfile) {
        this.profile = memberProfile;
    }

    /**
     * @param preferredSpecies
     * @param preferredBreed
     * @param gender
     * @param numericAttributes
     */
    public void setProfile(String preferredSpecies, String preferredBreed, String gender, int[] numericAttributes) {
        if (numericAttributes == null || numericAttributes.length != 7) {
            throw new IllegalArgumentException("numericAttributes array must have a length of 7");
        }

        this.profile = new MemberProfile(preferredSpecies, preferredBreed, gender, numericAttributes);
    }

    public int getId() {
        return super.getId();
    }

    public void setId(int id) {
        super.setId(id);
    }

    /**
     * @return profile
     */
    public MemberProfile getProfile() {
        return this.profile;
    }

}
