package se.kth.iv1350.progexe;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.progexe.integration.CustomerDTO;
import se.kth.iv1350.progexe.integration.CustomerRegistry;

/**
 * Verifierar att findCustomer returnerar en CustomerDTO
 * när ett giltigt telefonnummer finns i registret
 */
public class CustomerRegistryTest {
    private CustomerRegistry registry;
    
    /**
     * Skapar nytt CustomerRegistry innan varje test
     */
    @BeforeEach
    public void setUp(){
        registry = new CustomerRegistry();
    }
    /**
     * Testar att en kund hittas när telefonnummret finns i registret
     */
    @Test
    public void testFindExistingCustomer(){
        CustomerDTO customer = registry.findCustomer("0761234567");
        assertNotNull(customer, "Kunden borde hittas");
        assertEquals("Jima Khadrou", customer.getName(), "Fel kund hittades");

    }

    /**
    * Testar att null returneras när kunden inte finns.
    */
    @Test
    public void testFindNonExistingCustomer() {
        CustomerDTO customer = registry.findCustomer("0000000000");

        assertNull(customer, "Ingen kund borde hittas.");
    }
}    

