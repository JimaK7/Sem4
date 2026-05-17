package se.kth.iv1350.progexe.errorhandling;

/**
 * Kastats när ingen kund med det angivna telefonnumret finns.
 */
public class CustomerNotFoundException extends BikeException {
    private String phoneNumber;

    /**
     * Skapar ett nytt undantag för ett telefonnummer som inte hittades.
     *
     * @param phoneNumber Telefonnumret som användes vid sökningen.
     */
    public CustomerNotFoundException(String phoneNumber) {
        super("Kunden hittades inte");
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return Telefonnumret som användes vid sökningen.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}