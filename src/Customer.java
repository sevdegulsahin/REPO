
public class Customer {
    private String customerID;
    private String name;
    private ShipmentHistory shipmentHistory; // Linked list for shipment history

    public Customer(String customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.shipmentHistory = new ShipmentHistory();
    }

    public String getCustomerID() { return customerID; }
    public String getName() { return name; }
    public ShipmentHistory getShipmentHistory() { return shipmentHistory; }

    @Override
    public String toString() {
        return "Customer ID: " + customerID + ", Name: " + name;
    }
}

