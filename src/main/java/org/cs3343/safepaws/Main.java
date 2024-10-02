package org.cs3343.safepaws;

import org.cs3343.safepaws.util.UIExecutor;
import org.cs3343.safepaws.util.DbManager;

public class Main {
    public static void main(String[] args) {
        UIExecutor uiExecutor = UIExecutor.getInstance();
        try {
            DbManager.init();
            uiExecutor.start();
        } catch (Exception e) {
            System.out.println("An error occurred! Please report this to SafePaws support.");
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        } finally {
            uiExecutor.stop();
        }
    }
}