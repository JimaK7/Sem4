package se.kth.iv1350.progexe.logging;
/**
 * Representerar ett objekt som kan skriva loggmeddelanden, dvs felrapportera till utvecklaren.
 */
public interface Logger {
    /**
     * Skriver ett undantag inklusive hela stack trace till loggen.
     * 
     * @param exception Undantaget som ska loggas
     */
    void log(Exception exception);   
}  