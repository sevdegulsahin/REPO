
// ShipmentPriorityQueue.java
import java.util.PriorityQueue;

public class ShipmentPriorityQueue {
    private PriorityQueue<Shipment> queue;

    public ShipmentPriorityQueue() {
        this.queue = new PriorityQueue<>((a, b) -> a.getDeliveryTime() - b.getDeliveryTime());
    }

    public void addShipment(Shipment shipment) {
        queue.add(shipment);
    }

    public Shipment processNextShipment() {
        return queue.poll(); // Returns and removes the highest priority shipment
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
