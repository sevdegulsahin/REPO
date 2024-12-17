public class Cargo {
    int id;
    String date;
    String status;
    int deliveryTime;

    public Cargo(int id, String date, String status, int deliveryTime) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return "Cargo ID: " + id + ", Date: " + date + ", Status: " + status + ", Delivery Time: " + deliveryTime + " days";
    }
}
