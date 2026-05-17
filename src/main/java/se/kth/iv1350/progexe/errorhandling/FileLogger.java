package se.kth.iv1350.progexe.errorhandling;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Skriver loggmeddelanden till en fil.
 */
public class FileLogger implements Logger {
    private PrintWriter logStream;
    /**
     * Skapar en ny logger som skriver till filen: log.txt
     */
    public FileLogger(){
        try{
            logStream = new PrintWriter(new FileWriter("log.txt", true), true);
        }
        catch(IOException e){
            System.out.println("Loggning misslyckades");
            e.printStackTrace();
        }
    }

    /**
     * Skriver ett meddelande till loggfilen
     * @param message Meddelandet som ska skrivas in i loggfilen
     */
    @Override
    public void log(String message) {  
        if(logStream != null){
            logStream.println(message);
        }
    }
    
}
