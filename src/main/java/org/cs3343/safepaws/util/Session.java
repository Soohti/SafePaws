package org.cs3343.safepaws.util;

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
     */
    public String requestInput() {
        try {
            writer.println();
            writer.println("SYSTEM: READY_FOR_INPUT");
            return reader.readLine();
        } catch (IOException e) {
            writer.println("Error during requestInput of session.");
        }
        return null;
    }

    /**
     * Request a numeric input from the client, ensuring that the input is
     * within the specified range.
     * <p>
     * If the input is not a number, the client is prompted to enter a
     * number. If the input is a number but not within the specified range,
     * the client is prompted to enter a number within the specified range.
     * This process repeats until the client enters a valid number within
     * the specified range.
     * </p>
     *
     * @param minValue The minimum value of the input.
     *                 The client must enter a number greater than or equal to
     *                 this value.
     * @param maxValue The maximum value of the input.
     *                 The client must enter a number less than or equal to this
     *                 value.
     * @return The numeric input from the client.
     */
    public int requestNumericInput(final int minValue, final int maxValue) {
        while (true) {
            String input = requestInput();
            try {
                int value = Integer.parseInt(input);
                if (value >= minValue && value <= maxValue) {
                    return value;
                } else {
                    println(
                            "Invalid input. Please enter a number between "
                                    + minValue + " and " + maxValue + ".");
                }
            } catch (NumberFormatException e) {
                println("Invalid input. Please enter a number.");
            }
        }
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

    /**
     * Formats and writes a message to the client.
     *
     * @param format The format string.
     * @param args   The arguments referenced
     *               by the format specifiers in the format
     *               string.
     */
    public void printf(final String format, final Object... args) {
        StringBuilder output = new StringBuilder();
        Formatter formatter = new Formatter(output);
        formatter.format(format, args);
        writer.println(output);
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
        this.account = newAccount.deepClone();
    }

    /**
     * Gets the account associated with the session.
     *
     * @return the account associated with the session.
     */
    public Account getAccount() {
        return account != null ? account : null;
    }
}
