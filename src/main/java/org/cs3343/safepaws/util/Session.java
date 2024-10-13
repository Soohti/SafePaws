package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Session {
    /**
     * TODO.
     */
    private User user;
    /**
     * TODO.
     */
    private Pet pet;

    /**
     * Reader for the session that reads input from the client.
     */
    private final BufferedReader reader;
    /**
     * Writer for the session that writes output to the client.
     */
    private final PrintWriter writer;

    /**
     * Request input from the client.
     *
     * @return The input from the client.
     * @throws IOException If an error occurs.
     */
    public String requestInput() throws IOException {
        writer.println();
        writer.println("SYSTEM: READY_FOR_INPUT");
        return reader.readLine();
    }

    /**
     * Create a new session with the given input and output streams.
     *
     * @param in  The input stream.
     * @param out The output stream.
     */
    public Session(final InputStream in, final OutputStream out) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.writer = new PrintWriter(out, true);
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
     * Write a message to the client followed by a newline.
     *
     * @param message The message to write.
     */
    public void println(final String message) {
        writer.println(message);
    }
}
