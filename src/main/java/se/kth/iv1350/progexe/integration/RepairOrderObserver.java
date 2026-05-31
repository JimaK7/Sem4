package se.kth.iv1350.progexe.integration;
/**
 * Observer för objekt som ska notifieras när en reparationsorder uppdateras. 
 */
public interface RepairOrderObserver {
    
    /**
     * Anropas när en reparationsorder uppdaterats.
     * 
     * @param updatedOrder Den uppdaterade ordern
     */
    void repairOrderUpdated(OrderDTO updatedOrder);
    
}  
    

