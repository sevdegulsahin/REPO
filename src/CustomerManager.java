
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerManager {
    private Map<String, Customer> customers; // Müşteri ID'sine göre saklanan müşteriler

    public CustomerManager() {
        customers = new java.util.HashMap<>();
    }

    public void addCustomer(String customerID, String name) {
        customers.put(customerID, new Customer(customerID, name));
    }

    public Map<String, Customer> getCustomers() {
        return customers;
    }

    // Tüm kargoları listeleyen metot
    public List<Shipment> getAllShipments() {
        List<Shipment> allShipments = new ArrayList<>();

        for (Customer customer : customers.values()) {
            allShipments.addAll(customer.getShipmentHistory().getShipments());
        }

        return allShipments;
    }
}
