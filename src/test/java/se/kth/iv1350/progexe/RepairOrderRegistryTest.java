package se.kth.iv1350.progexe;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.progexe.integration.BikeDTO;
import se.kth.iv1350.progexe.integration.CustomerDTO;
import se.kth.iv1350.progexe.integration.OrderDTO;
import se.kth.iv1350.progexe.integration.RepairOrderRegistry;
import se.kth.iv1350.progexe.integration.RepairTaskDTO;
import se.kth.iv1350.progexe.model.RepairManager;

/**
 * Verifierar att en reparationsorder skapas med korrekt
 * problembeskrivning och kopplas till rätt kund. 
 */
public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;
    private RepairManager repairManager;
    private CustomerDTO customer;
    /**
     * Skapar testdata innan varje test.
     */
    @BeforeEach
    public void setUp() {
        registry = new RepairOrderRegistry();

        BikeDTO bike = new BikeDTO("Trek", "Mountain Bike", "ABC123");
        customer = new CustomerDTO("Jima Khadrou", "jima@gmail.com", "0761234567", bike);
        repairManager = new RepairManager(null, registry, null);
    }

    /**
     * Testar att en reparationsorder skapas med rätt problembeskrivning och kund.
     */
    @Test
    public void testCreateRepairOrder() {
        OrderDTO order = registry.createRepairOrder("Däcken har punkterats", customer);

        assertNotNull(order, "Ordern borde skapas.");
        assertEquals("Däcken har punkterats", order.getProblemDescr(), "Fel problembeskrivning.");
        assertEquals(customer, order.getCustomer(), "Fel kund i ordern.");
    }

    /**
     * Testar att diagnosresultat kan läggas till i en order.
     */
    @Test
    public void testAddDiagnosticResult() {
        OrderDTO order = registry.createRepairOrder("Däcken har punkterats", customer);

        registry.addDiagnosticResult(order, "Punktering på båda däcken");

        assertEquals("Punktering på båda däcken", order.getDiagnosticResult(), "Diagnosen lades inte till.");
    }

    /**
    * Verifierar att en reparationsåtgärd läggs till i orderns lista av åtgärder och att den matchar.
    */
    @Test
    public void testAddRepairTask() {
    OrderDTO order = registry.createRepairOrder("Däcken har punkterats", customer);
    RepairTaskDTO task = new RepairTaskDTO("Byta slang fram", 250.0);

    order = repairManager.addRepairTask(order, task.getDescription(), task.getCost());

    assertEquals(1, order.getRepairTasks().size(), "En åtgärd borde ha lagts till.");
    assertEquals(task.getDescription(),order.getRepairTasks().get(0).getDescription());
    assertEquals(task.getCost(),order.getRepairTasks().get(0).getCost());
}

}