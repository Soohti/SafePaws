package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Admin;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;

public class AccountFactory {
    public static Account createAccount(final int id,
                                        final String username,
                                        final String password,
                                        final String role,
                                        final MemberProfile memberProfile) {
        switch (role.toLowerCase()) {
            case "member":
                return new Member(id, username, password, role, memberProfile);
            case "admin":
                return new Admin(id, username, password, role);
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
}
