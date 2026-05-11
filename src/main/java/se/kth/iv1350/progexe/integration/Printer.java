package se.kth.iv1350.progexe.integration;


/**
 * Simulerar en kvitto skrivare
 */
public class Printer {
    /**
     * Skriver ut kvittot
     * @param order Ordern som ska skrivas ut
     */
    public void printReceipt(String formattedOrder, double totalCost){
    System.out.println("\n---- KVITTO ----\n");
    System.out.println(formattedOrder);
    System.out.println("\nUppskattad färdig inom 1-2 arbetsdagar");
    System.out.println("Total kostnad: " + totalCost + " kr");
}
}

