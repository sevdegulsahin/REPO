
public class Shipment {
    private String shipmentID;
    private String date;
    private boolean delivered;
    private int deliveryTime;

    public Shipment(String shipmentID, String date, boolean delivered, int deliveryTime) {
        this.shipmentID = shipmentID;
        this.date = date;
        this.delivered = delivered;
        this.deliveryTime = deliveryTime;
    }

    public String getShipmentID() { return shipmentID; }
    public String getDate() { return date; }
    public boolean isDelivered() { return delivered; }
    public int getDeliveryTime() { return deliveryTime; }

    @Override
    public String toString() {
        return "Shipment ID: " + shipmentID + ", Date: " + date +
                ", Delivered: " + delivered + ", Delivery Time: " + deliveryTime;
    }
}


