package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Admin;
import org.cs3343.safepaws.entity.LocationPoint;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Shelter;

/**
 * Factory class for creating Account instances.
 */
public final class AccountFactory {
    private AccountFactory() {
    }
    /**
     * Creates an account based on the role.
     *
     * @param id the id of the account
     * @param username the username of the account
     * @param password the password of the account
     * @param role the role of the account
     * @param memberProfile the profile of the member (if applicable)
     * @param locationPoint the location point of the member (if applicable)
     * @return the created account
     */
    public static Account createAccount(final int id,
                                        final String username,
                                        final String password,
                                        final String role,
                                        final MemberProfile memberProfile,
                                        final LocationPoint locationPoint) {
        switch (role.toLowerCase()) {
            case "m":
                return new Member(id, username,
                        password, role, memberProfile);
            case "a":
                return new Admin(id, username,
                        password, role);
            case "s":
                return  new Shelter(id, username,
                        password, role, locationPoint);
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
}
