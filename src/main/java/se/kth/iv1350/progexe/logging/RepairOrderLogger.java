package se.kth.iv1350.progexe.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.iv1350.progexe.integration.BikeDTO;
import se.kth.iv1350.progexe.integration.CustomerDTO;
import se.kth.iv1350.progexe.integration.OrderDTO;
import se.kth.iv1350.progexe.integration.RepairOrderObserver;
import se.kth.iv1350.progexe.integration.RepairTaskDTO;

/**
 * Skriver uppdaterade reparationsordrar till en fil.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    private PrintWriter logStream;

    /**
     * Skapar en logger som skriver uppdaterade reparationsordrar till filen repair.order.log.txt.
     */
    public RepairOrderLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("repair.order.log.txt", true), true);
        } catch (IOException e) {
            System.out.println("Loggning av reparationsorder misslyckades.");
            e.printStackTrace();
        }
    }

    /**
     * Skriver den uppdaterade reparationsordern till loggfilen.
     *
     * @param updatedOrder Den uppdaterade reparationsordern.
     */
    @Override
    public void repairOrderUpdated(OrderDTO updatedOrder) {
        if (logStream != null) {
            logStream.println("\n--- Uppdaterad reparationsorder ---");
            logStream.println(formatOrder(updatedOrder));
            logStream.println("");
        }
    }

    private String formatOrder(OrderDTO order) {
        return "Order id: " + order.getId() +
               "\nDatum: " + order.getDateCreated() +
               "\nProblem: " + order.getProblemDescr() +
               "\nKunduppgifter:\n" + formatCustomer(order.getCustomer()) +
               "\nDiagnos: " + order.getDiagnosticResult() +
               "\nÅtgärder: " + formatTasks(order) +
               "\nAccepterad: " + order.isAccepted();
    }

    private String formatCustomer(CustomerDTO customer) {
        return "Namn: " + customer.getName() +
               "\nEmail: " + customer.getEmail() +
               "\nCykeldetaljer:\n" + formatBike(customer.getBike());
    }

    private String formatBike(BikeDTO bike) {
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