package se.kth.iv1350.progexe.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.progexe.errorhandling.CustomerNotFoundException;
import se.kth.iv1350.progexe.errorhandling.DatabaseFailureException;


public class CustomerRegistry {

    private List<CustomerDTO> customers;
    private static final String DATABASE_FAILURE_NUMBER= "1111111111";

    /**
     * Skapar ett kundregister med hårdkodad data bestående av kundens kontaktuppgifter och cykel.
     */
    public CustomerRegistry() {

        customers = new ArrayList<>();

        BikeDTO bike1 = new BikeDTO("Trek", "Mountain Bike", "ABC123");

        CustomerDTO customer1 = new CustomerDTO("Jima Khadrou","jima@gmail.com","0761234567",bike1);

        customers.add(customer1);
    }

   /**
   * Söker efter en kund via angivet telefonnummer.
   * 
   * @param phoneNumber Numret till kunden som söks.
   * @return Kunden om telefonnumret matchar.
   * @throws CustomerNotFoundException Om ingen kund med det angivna telefonnumret finns.
   * @throws DatabaseFailureException Om kundregistret inte kan nås.
   */
    public CustomerDTO findCustomer(String phoneNumber) throws CustomerNotFoundException{ 

        if (phoneNumber.equals(DATABASE_FAILURE_NUMBER)) {
            throw new DatabaseFailureException("CustomerRegistry kunde inte nås");
        }

        for (CustomerDTO customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
            
        }
        throw new CustomerNotFoundException(phoneNumber);
    }
}
