package se.kth.iv1350.progexe.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.progexe.integration.CustomerDTO;
import se.kth.iv1350.progexe.integration.CustomerRegistry;
import se.kth.iv1350.progexe.integration.OrderDTO;
import se.kth.iv1350.progexe.integration.Printer;
import se.kth.iv1350.progexe.integration.RepairOrderRegistry;
import se.kth.iv1350.progexe.integration.RepairTaskDTO;


/**
 * Ansvarar för affärslogiken kring reparationen av elcyklar
 */
public class RepairManager {
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    private Printer printer;

    /**
     * Skapar en ny instans av klassen
     * @param customerRegistry Register som innehåller kundinformation
     * @param repairOrderRegistry Register som lagrar reparationsordrar
     * @param printer Används för att skriva ut reparationsordrar, kvittot.
     */
    public RepairManager(CustomerRegistry customerRegistry, RepairOrderRegistry repairOrderRegistry, Printer printer) {
        this.customerRegistry = customerRegistry;
        this.repairOrderRegistry = repairOrderRegistry;
        this.printer = printer;
        
    }
    /**
     * Söker efter en kund via telefonnummer
     * @param phoneNumber Telefonnumret till kunden som sökes
     * @return kunden om telefonnumret matchar
     */
    public CustomerDTO findCustomer(String phoneNumber){
        return customerRegistry.findCustomer(phoneNumber);
    }
    /**
    * Skapar en reparationsorder för kunden med angivet telefonnummer.
    *
    * @param problemDescription Kundens beskrivning av problemet.
    * @param phoneNumber Telefonnummer till kunden som lämnar in cykeln.
    * @return Den skapade reparationsordern.
    */
    public OrderDTO createRepairOrder(String problemDescription, String phoneNumber){

        CustomerDTO customer = customerRegistry.findCustomer(phoneNumber);
        return repairOrderRegistry.createRepairOrder(problemDescription, customer);
}

/**
 * Lägger till diagnos på en reparationsorder.
 *
 * @param order Den order som ska uppdateras.
 * @param diagnosticResult Resultatet av diagnosen.
 */
public void addDiagnosticResult(OrderDTO order, String diagnosticResult){
    repairOrderRegistry.addDiagnosticResult(order, diagnosticResult);
}

/**
 * Lägger till en reparationsåtgärd på en order
 * @param order Ordern som ska åtgärdas
 * @param description Åtgärdsbeskrivningen
 * @param cost Kostnad för åtgärden
 */
public OrderDTO addRepairTask(OrderDTO order, String description, double cost){

    RepairTaskDTO task = new RepairTaskDTO(description, cost);

    List<RepairTaskDTO> updatedTasks = new ArrayList<>(order.getRepairTasks());

    updatedTasks.add(task);

    OrderDTO updatedOrder = new OrderDTO(
            order.getId(),
            order.getDateCreated(),
            order.getProblemDescr(),
            order.getCustomer(),
            order.getDiagnosticResult(),
            updatedTasks,
            order.isAccepted());
            return updatedOrder;
}
/**
 * Markerar en reparationsorder som accepterad
 * @param order Ordern som ska accepteras
 */
public void acceptRepairOrder(OrderDTO order){
    repairOrderRegistry.acceptRepairOrder(order);

}
/**
 * Beräknar den totala kostnaden för en reparationsorder.
 * @param order order Reparationsordern vars totala kostnad ska beräknas
 * @return Den totala kostnaden för alla reparationsåtgärder.
 */
public double calculateTotalCost(OrderDTO order){
    double totalCost = 0;
    for(RepairTaskDTO task : order.getRepairTasks()){
        totalCost += task.getCost();
    }
    return totalCost;
}

/**
 * Skriver ut ett kvitto.
 *
 * @param formattedOrder Den formaterade ordertexten.
 * @param totalCost Den totala kostnaden för ordern.
 */
public void printReceipt(String formattedOrder, double totalCost){
    printer.printReceipt(formattedOrder, totalCost);
}



}
