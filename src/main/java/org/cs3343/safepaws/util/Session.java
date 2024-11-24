package org.cs3343.safepaws.util;

//import org.cs3343.safepaws.entity.Pet;
//import org.cs3343.safepaws.entity.User;

import org.cs3343.safepaws.entity.Account;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;

/**
 * Represents a session with a client.
 */
public final class Session {

    /**
     * Reader for the session that reads input from the client.
     */
    private final BufferedReader reader;
    /**
     * Writer for the session that writes output to the client.
     */
    private final PrintWriter writer;

    /**
     * The account associated with the session.
     */
    private Account account;

    /**
     * Request input from the client.
     *
     * @return The input from the client.
     * @throws IOException If an error occurs.
     */
    public String requestInput() {
        try {
            writer.println();
            writer.println("SYSTEM: READY_FOR_INPUT");
            return reader.readLine();
        } catch (Exception e) {
            writer.println("Error during requestInput of session.");
        }
        return null;
    }

    /**
     * Create a new session with the given input and output streams.
     *
     * @param in The input stream.
     * @param out The output stream.
     */
    public Session(final InputStream in, final OutputStream out) {
        this.reader = new BufferedReader(
            new InputStreamReader(in, StandardCharsets.UTF_8)
        );
        this.writer = new PrintWriter(
            new OutputStreamWriter(out, StandardCharsets.UTF_8), true
        );
    }

    /**
     * Copy constructor for Session.
     *
     * @param other The session to copy.
     */
    public Session(Session other) {
        this.reader = other.reader;
        this.writer = other.writer;
        this.account = other.account;
    }

    /**
     * Write a message to the client.
     *
     * @param message The message to write.
     */
    public void print(final String message) {
        writer.print(message);
    }

    /**
     * Write a newline to the client.
     */
    public void println() {
        writer.println();
    }

    /**
     * Formats and writes a message to the client.
     *
     * @param format The format string.
     * @param args   The arguments referenced
     * by the format specifiers in the format
     * string.
     */
    public void printf(final String format, final Object... args) {
        StringBuilder output = new StringBuilder();
        Formatter formatter = new Formatter(output);
        formatter.format(format, args);
        writer.println(output.toString());
        formatter.close();
    }

    /**
     * Write a message to the client followed by a newline.
     *
     * @param message The message to write.
     */
    public void println(final String message) {
        writer.println(message);
    }

    /**
     * Sets the account.
     *
     * @param newAccount the new account
     */
    public void setAccount(final Account newAccount) {
        if (newAccount != null) {
            this.account = new Account(newAccount);
        } else {
            this.account = null;
        }
    }

    /**
     * Gets the account associated with the session.
     *
     * @return a copy of the account associated with the session.
     */
    public Account getAccount() {
        return (this.account != null) ? new Account(this.account) : null;
    }

    /**
     * Clear.
     */
    public void clear() {
        this.account = null;
    }
}
