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
    public Member(final String username, final String password, final String role) {
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
    public void setProfile(final MemberProfile memberProfile) {
        this.profile = memberProfile;
    }

    /**
     * @param preferredSpecies
     * @param preferredBreed
     * @param gender
     * @param numericAttributes
     */
    public void setProfile(final String preferredSpecies, final String preferredBreed,
            final String gender, final int[] numericAttributes) {
        this.profile = new MemberProfile(preferredSpecies, 
                preferredBreed, gender, numericAttributes);
    }

    public int getId() {
        return super.getId();
    }

    public void setId(final int id) {
        super.setId(id);
    }

    /**
     * @return profile
     */
    public MemberProfile getProfile() {
        return this.profile;
    }

}
