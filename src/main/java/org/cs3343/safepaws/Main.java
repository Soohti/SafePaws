package org.cs3343.safepaws;

import org.cs3343.safepaws.util.ActionExecutor;

public class Main {
    public static void main(String[] args) {
        ActionExecutor actionExecutor = ActionExecutor.getInstance();
        try {
            actionExecutor.start();
        } catch (Exception e) {
            System.out.println("An error occurred! Please report this to SafePaws support.");
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        } finally {
            actionExecutor.stop();
        }
    }
}