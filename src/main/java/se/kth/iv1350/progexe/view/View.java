package se.kth.iv1350.progexe.view;

import se.kth.iv1350.progexe.controller.Controller;
import se.kth.iv1350.progexe.integration.BikeDTO;
import se.kth.iv1350.progexe.integration.CustomerDTO;
import se.kth.iv1350.progexe.integration.DatabaseFailureException;
import se.kth.iv1350.progexe.integration.OrderDTO;
import se.kth.iv1350.progexe.integration.RepairTaskDTO;
import se.kth.iv1350.progexe.logging.FileLogger;
import se.kth.iv1350.progexe.logging.Logger;
import se.kth.iv1350.progexe.model.CustomerNotFoundException;

/**
 * Detta är en platshållare för den riktiga vyn. Den innehåller en hårdkodad körning
 * med anrop till alla operationer i controllern
 */
public class View {
    private Controller controller;
    private Logger logger = new FileLogger();
    /**
     * Skapar en ny instans av View
     * @param controller Den controller som hanterar kommunikationen mellan vyn och modellagret
     */
    public View(Controller controller) {
        this.controller = controller;
        
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
    
    /**
     * Utför en fake reparationsorder av en elcykel genom att kalla på alla systemoperationer i controller.
     */
    public void sampleExecution(){
        
        System.out.println("\nHittar kunden:\n");

        try{
        CustomerDTO customer = controller.findCustomer("0761234567");
        System.out.println(formatCustomer(customer));  
        }
        catch(CustomerNotFoundException e){
            System.out.println("Ingen kund hittades med det angivna telefonnumret: " + e.getPhoneNumber());
        }
        catch(DatabaseFailureException e){
            System.out.println("Kundregistret kunde inte nås, försök igen senare.");
            logger.log(e);
            return; 
        }     
        OrderDTO order;
        System.out.println("\nKunden beskriver problemet och en order skapas:\n");
        try{
        order = controller.createRepairOrder("Däcken har punkterats", "0761234567");
        System.out.println(formatOrder(order));
        }
        catch(CustomerNotFoundException e){
        System.out.println("Ordern kunde inte skapas eftersom ingen kund hittades med telefonnumret: " + e.getPhoneNumber());
        return; 
        }
        catch(DatabaseFailureException e){
            System.out.println("Ordern kunde ej skapas eftersom kundregistret inte kunde nås, försök igen senare.");
            logger.log(e);
            return;
        } 
        
        System.out.println("\nLägger till Diagnosresultatet:\n");
        order = controller.addDiagnosticResult(order, "Punktering på båda däcken");
        System.out.println(formatOrder(order));
        System.out.println("\nSkapar reparationsåtgärder och sätter pris:\n");
        order = controller.addRepairTask(order, "Täta hålen och pumpa däcken", 500.0);
        order = controller.addRepairTask(order, "Byta slang framme", 250.0);
        System.out.println(formatOrder(order));
        System.out.println("\n*Kunden accepterar ordern*\n");
        order = controller.acceptRepairOrder(order);
        System.out.println(formatOrder(order));
        double totalCost = controller.calculateTotalCost(order);
        controller.printReceipt(formatOrder(order), totalCost);
    }
}
