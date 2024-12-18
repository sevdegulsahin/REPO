
import java.util.LinkedList;
import java.util.List;

public class ShipmentHistory {
    private LinkedList<Shipment> shipments;

    public ShipmentHistory() {
        this.shipments = new LinkedList<>();
    }

    public void addShipment(Shipment shipment) {
        // Insert shipments sorted by date (ascending order)
        int index = 0;
        for (Shipment s : shipments) {
            if (shipment.getDate().compareTo(s.getDate()) < 0) {
                break;
            }
            index++;
        }
        shipments.add(index, shipment);
    }

    // Tüm gönderileri döndüren metot
    public List<Shipment> getAllShipments() {
        return new LinkedList<>(shipments); // Listeyi değiştirilmez kopya olarak döner
    }

    public LinkedList<Shipment> getShipments() {
        return shipments;
    }

    public void printHistory() {
        if (shipments.isEmpty()) {
            System.out.println("No shipment history.");
            return;
        }
        for (Shipment s : shipments) {
            System.out.println(s);
        }
    }
}
