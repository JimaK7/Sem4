package se.kth.iv1350.progexe.integration;
/**
 * Representerar en reparationsåtgärd kan utföras på en cykel.
 */
public class RepairTaskDTO {
    private String description;
    private double cost;
    /**
     * SKapar en ny instans av RepairTask
     * @param description Beskrivning av reparationsåtgärden
     * @param cost Kostnaden för reparationsåtgärden
     */
    public RepairTaskDTO(String description, double cost){
        this.description = description;
        this.cost = cost;
    }
    /**
     * 
     * @return Beskrivningen av reparationsåtgärden
     */
    public String getDescription(){
        return description;
    }
    /**
     * 
     * @return Kostnaden för reparationsåtgärden
     */
    public double getCost(){
        return cost;
    }

}
