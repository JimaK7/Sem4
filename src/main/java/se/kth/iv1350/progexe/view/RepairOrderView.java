package se.kth.iv1350.progexe.view;

import se.kth.iv1350.progexe.integration.BikeDTO;
import se.kth.iv1350.progexe.integration.CustomerDTO;
import se.kth.iv1350.progexe.integration.OrderDTO;
import se.kth.iv1350.progexe.integration.RepairOrderObserver;
import se.kth.iv1350.progexe.integration.RepairTaskDTO;

/**
 * Visar uppdaterade reparationsordrar för tekniker och receptionist.
 */
public class RepairOrderView implements RepairOrderObserver {

    /**
     * Skriver ut den uppdaterade reparationsordern till System.out.
     *
     * @param updatedOrder Den uppdaterade reparationsordern.
     */
    @Override
    public void repairOrderUpdated(OrderDTO updatedOrder) {
        System.out.println("\n--- Uppdaterad reparationsorder ---");
        System.out.println(formatOrder(updatedOrder));
        System.out.println("");
    }

    private String formatOrder(OrderDTO order){
    return "Order id: " + order.getId() +
           "\nDatum: " + order.getDateCreated() +
           "\nProblem: " + order.getProblemDescr() +
           "\nKunduppgifter:\n" + formatCustomer(order.getCustomer()) +
           "\nDiagnos: " + order.getDiagnosticResult() +
           "\nÅtgärder: " + formatTasks(order) +
           "\nAccepterad: " + order.isAccepted();
}
    
    private String formatCustomer(CustomerDTO customer){
        return "Namn: " + customer.getName() + 
        "\nEmail: " + customer.getEmail() +
        "\nCykeldetaljer:\n" + formatBike(customer.getBike());
    }

    private String formatBike(BikeDTO bike){
        return "Varumärke: " + bike.getBrand() + 
        ", Modell: " + bike.getModel() +
        ", Serienummer: " + bike.getSerialNumber(); 
    }
    
    private String formatTasks(OrderDTO order) {
    String tasks = "";

    for (RepairTaskDTO task : order.getRepairTasks()) {
        tasks += "\n" + task.getDescription() + 
                 " (" + task.getCost() + "kr)";
    }

    return tasks;
}
}