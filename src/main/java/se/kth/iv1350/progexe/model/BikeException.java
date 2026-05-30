package se.kth.iv1350.progexe.model;

/**
 * Superklass för programmets egna checked exceptions.
 */
public class BikeException extends Exception {
    public BikeException(String message) {
        super(message);
    }

}
