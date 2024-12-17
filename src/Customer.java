import java.util.LinkedList;

public class Customer {
    int id;
    String name;
    String surname;
    LinkedList<Cargo> cargoHistory;

    public Customer(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.cargoHistory = new LinkedList<>();
    }

    public void addCargo(Cargo cargo) {
        int index = 0;
        while (index < cargoHistory.size() && cargoHistory.get(index).date.compareTo(cargo.date) < 0) {
            index++;
        }
        cargoHistory.add(index, cargo);
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name + " " + surname;
    }
}
