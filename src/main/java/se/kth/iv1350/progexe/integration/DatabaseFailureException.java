package se.kth.iv1350.progexe.integration;

/**
 * Kastats när databasen eller ett register av någon anledning inte kan anropas.
 */
public class DatabaseFailureException extends RuntimeException {

    /**
     * Skapar ett nytt undantag för ett databasfel.
     *
     * @param message Det tekniska felmeddelandet som ska användas i loggen.
     */
    public DatabaseFailureException(String message) {
        super(message);
    }
}