package se.kth.iv1350.progexe.errorhandling;
/**
 * Representerar ett objekt som kan  skriva loggmeddelanden.
 */
public interface Logger {
    /**
     * Skriver meddelandet till loggen
     * @param message Meddelandet som ska loggas
     */
    void log(String message);

    
}  