package se.kth.iv1350.progexe.integration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Hanterar lagring av reparationsordrar
 */
public class RepairOrderRegistry {
private List<OrderDTO> orders;
private int nextOrderId = 1;
private List<RepairOrderObserver> observers = new ArrayList<>();


/**
 * Skapar ett nytt register för reparationsordrar
 */
public RepairOrderRegistry(){
    this.orders = new ArrayList<OrderDTO>();
}

/**
* Lägger till en observer som ska informeras när en reparationsorder uppdateras.
*
* @param observer Observern som ska läggas till.
*/
public void addRepairOrderObserver(RepairOrderObserver observer) {
    observers.add(observer);
}

/**
 * Skapar en ny reparationsorder och sparar den i registret
 * @param problemDescription Kundens beskrivning av problemet
 * @param customer Kunden som lämnat in cykeln
 * @return Den skapade reparationsordern
 */
public OrderDTO createRepairOrder(String problemDescription, CustomerDTO customer){
    String id = String.valueOf(nextOrderId++);
    LocalDateTime now = LocalDateTime.now();
    OrderDTO order = new OrderDTO(id, now, problemDescription, customer,null);
    orders.add(order);
    return order;
}

/**
 * Sparar en uppdaterad order i registret.
 * Om ordern redan finns ersätts den gamla versionen, annars läggs ordern till i registret. 
 * Observers notifieras efter att ordern sparats.
 * @param updatedOrder Den uppdaterade ordern som ska sparas
 */
public void saveOrder(OrderDTO updatedOrder) {
    for (int i = 0; i < orders.size(); i++) {
        if (orders.get(i).getId().equals(updatedOrder.getId())) {
            orders.set(i, updatedOrder);
            notifyObservers(updatedOrder);
            return;
        }
    }

    orders.add(updatedOrder);
    notifyObservers(updatedOrder);
}

private void notifyObservers(OrderDTO updatedOrder) {
    for(RepairOrderObserver observer : observers){
        observer.repairOrderUpdated(updatedOrder);
    }
}

}
