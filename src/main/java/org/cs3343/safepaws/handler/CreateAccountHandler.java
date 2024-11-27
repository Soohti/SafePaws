package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.util.DbManager;

import java.sql.SQLException;

public class CreateAccountHandler {

    private static CreateAccountHandler instance;

    private CreateAccountHandler() {
    }

    public static CreateAccountHandler getInstance() {
        if (instance == null) {
            instance = new CreateAccountHandler();
        }
        return instance;
    }
    /**
     * Inserts an account into the database.
     *
     * @param thisAccount the account to insert.
     * @throws SQLException if a database error occurs.
     */
    public void createAccount(final Account thisAccount)
            throws Exception {
        DbManager.insert(thisAccount, "ACCOUNT");
        System.out.println("Account inserted successfully");
    }
}
