package se.kth.iv1350.progexe.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Skriver felrapporter till en loggfil.
 */
public class FileLogger implements Logger {
    private PrintWriter logStream;

    /**
     * Skapar en ny logger som skriver till filen log.txt.
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("log.txt", true), true);
        } catch (IOException e) {
            System.out.println("Loggning misslyckades.");
            e.printStackTrace();
        }
    }

    /**
     * Skriver ett undantag inklusive hela stack trace till loggfilen.
     *
     * @param exception Undantaget som ska loggas.
     */
    @Override
    public void log(Exception exception) {
        if (logStream != null) {
            exception.printStackTrace(logStream);
        }
    }
}
