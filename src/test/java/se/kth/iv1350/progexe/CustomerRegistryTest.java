package se.kth.iv1350.progexe;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.progexe.integration.CustomerDTO;
import se.kth.iv1350.progexe.integration.CustomerRegistry;
import se.kth.iv1350.progexe.integration.DatabaseFailureException;
import se.kth.iv1350.progexe.model.CustomerNotFoundException;

/**
 * Verifierar att CustomerRegistry hanterar kundsökningar korrekt
 */
public class CustomerRegistryTest {
    private CustomerRegistry registry;
    
    /**
     * Skapar nytt CustomerRegistry innan varje test.
     */
    @BeforeEach
    public void setUp(){
        registry = new CustomerRegistry();
    }
    /**
     * Testar att en kund hittas när telefonnummret finns i registret
     * @throws CustomerNotFoundException Om testets telefonnummer av misstag inte finns i registret
     */
    @Test
    public void testFindExistingCustomer() throws CustomerNotFoundException{
        CustomerDTO customer = registry.findCustomer("0761234567");
        assertNotNull(customer, "Kunden borde hittas");
        assertEquals("Jima Khadrou", customer.getName(), "Fel kund hittades");

    }

    
    /**
     * Testar att CustomerNotFoundException kastas när kunden inte finns.
     */
    @Test
    public void testFindNonExistingCustomer() {
        String invalidPhoneNumber = "0000000000";

        try {
            registry.findCustomer(invalidPhoneNumber);
            fail("CustomerNotFoundException kastades inte.");
        } catch (CustomerNotFoundException e) {
            assertEquals(invalidPhoneNumber, e.getPhoneNumber(), "Undantaget innehåller fel telefonnummer.");
        }
    }
    /**
     * Testar att DatabaseFailureException kastas när kundregistret inte nås.
     */
    @Test
    public void testDatabaseFailureException(){
    String databaseFailureNumber = "1111111111";
    try{
        registry.findCustomer(databaseFailureNumber);
        fail("DatabaseFailureException kastades inte.");
    }
    catch(DatabaseFailureException e){
        assertTrue(e.getMessage().contains("CustomerRegistry"), "Undantaget innehåller inte information om vilket register som inte nås.");

    }
     catch(CustomerNotFoundException e){
        fail("Fel typ av undantag har kastats");

    }
}

} 




