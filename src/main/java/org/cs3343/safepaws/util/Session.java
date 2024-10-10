package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.entity.User;

import java.io.*;

public class Session {
    private User user;
    private Pet pet;

    private final BufferedReader in;
    public final PrintWriter out;

    public String requestInput() throws IOException {
        out.println();
        out.println("SYSTEM: READY_FOR_INPUT");
        return in.readLine();
    }

    public Session(InputStream in, OutputStream out) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = new PrintWriter(out, true);
    }
}
