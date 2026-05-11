package se.kth.iv1350.progexe.integration;

 
/**
 * Innehåller data om kunden
 */
public class CustomerDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private BikeDTO bike;
    
    /**
     * Skapar en instans av klassen
     * @param name Kundens namn
     * @param email Kundens e-postadress
     * @param phoneNumber Kundens telefonnummer
     * @param bike Kundens cykel
     */
    public CustomerDTO(String name, String email, String phoneNumber, BikeDTO bike){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bike = bike;

    }
    /**
     * 
     * @return Kundens namn
     */
    public String getName(){
        return name;
    }
    /**
     * 
     * @return Kundens email
     */
    public String getEmail(){
        return email;
    }
    /**
     * 
     * @return Kundens telefonnummer
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }
    /**
     * 
     * @return Kundens cykel
     */
    public BikeDTO getBike(){
        return bike;
    }
    
}
