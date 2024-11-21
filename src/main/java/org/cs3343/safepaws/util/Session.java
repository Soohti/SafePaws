package org.cs3343.safepaws.util;

//import org.cs3343.safepaws.entity.Pet;
//import org.cs3343.safepaws.entity.User;

import org.cs3343.safepaws.entity.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;

/**
 * 
 */
public final class Session {
//    private User user;
//    private Pet pet;

    /**
     * Reader for the session that reads input from the client.
     */
    private final BufferedReader reader;
    /**
     * Writer for the session that writes output to the client.
     */
    private final PrintWriter writer;

    private Account account;

    /**
     * Request input from the client.
     *
     * @return The input from the client.
     * @throws IOException If an error occurs.
     */
    public String requestInput(){
        try {
        	writer.println();
            writer.println("SYSTEM: READY_FOR_INPUT");
            return reader.readLine();
        }catch(Exception e) {
        	writer.println("Error during requestInput of session.");
        }
    	return null;
    }

    /**
     * Create a new session with the given input and output streams.
     *
     * @param in  The input stream.
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

    public void printf(String format, Object... args) {
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
     * @param account the new account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }

    /**
     * Clear.
     */
    public void clear() {
        this.account = null;
    }
}
