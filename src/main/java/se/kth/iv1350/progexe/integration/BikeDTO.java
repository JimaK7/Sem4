package se.kth.iv1350.progexe.integration;

/**
 * Innehåller data om en cykel.
 */
public class BikeDTO {
    private String brand;
    private String model;
    private String serialNumber;
    /**
     * Skapar en ny instans av BikeDTO.
     * @param brand Cykelns märke.
     * @param model Cykelns modell.
     * @param serialNumber Cykelns serienummer
     */
    public BikeDTO(String brand, String model, String serialNumber){
    this.brand = brand;
    this.model = model;
    this.serialNumber = serialNumber;
    }
    
    /**
     * @return Cykelns märke.
     */
    public String getBrand(){
        return brand;
    }
    
    /**
     * @return Cykelns modell.
     */
    public String getModel(){
        return model;
    }
    
    /**
     * @return Cykelns serienummer
     */
    public String getSerialNumber(){
        return serialNumber;
    }
   
}
