package se.kth.iv1350.progexe.startup;

import se.kth.iv1350.progexe.controller.Controller;
import se.kth.iv1350.progexe.integration.CustomerRegistry;
import se.kth.iv1350.progexe.integration.Printer;
import se.kth.iv1350.progexe.integration.RepairOrderRegistry;
import se.kth.iv1350.progexe.model.RepairManager;
import se.kth.iv1350.progexe.view.View;
import se.kth.iv1350.progexe.view.RepairOrderView;
import se.kth.iv1350.progexe.logging.RepairOrderLogger;
/**
 * Startar programmet, och innehåller main metoden som används för att starta programmet.
 */

public class Main {
    /**
     * Main metoden används för att starta hela programmet
     * @param args Programmet tar inga kommandorads parametrar (command line)
     */
    public static void main(String[] args) {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        repairOrderRegistry.addRepairOrderObserver(new RepairOrderView());
        repairOrderRegistry.addRepairOrderObserver(new RepairOrderLogger());
        
        Printer printer = new Printer();

        RepairManager repairManager = new RepairManager(customerRegistry, repairOrderRegistry, printer);
        Controller controller = new Controller(repairManager);
        View view = new View(controller);

        view.sampleExecution();


    }
    
}
