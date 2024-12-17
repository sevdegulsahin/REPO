import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedList<Customer> customers = new LinkedList<>();
        PriorityQueueCargo priorityQueue = new PriorityQueueCargo();
        RecentCargoStack recentCargoStack = new RecentCargoStack();
        DeliveryTree deliveryTree = new DeliveryTree("Central Office");

        while (true) {
            System.out.println("\nCargo Management System:");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Cargo to Customer");
            System.out.println("3. View Delivery Tree");
            System.out.println("4. Process Next Priority Cargo");
            System.out.println("5. Show Recent Cargos");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Surname: ");
                    String surname = scanner.nextLine();
                    customers.add(new Customer(id, name, surname));
                    break;
                case 2:
                    System.out.print("Enter Customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Cargo ID: ");
                    int cargoId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Cargo Date: ");
                    String date = scanner.nextLine();
                    System.out.print("Enter Cargo Status: ");
                    String status = scanner.nextLine();
                    System.out.print("Enter Delivery Time: ");
                    int deliveryTime = scanner.nextInt();
                    scanner.nextLine();
                    for (Customer customer : customers) {
                        if (customer.id == customerId) {
                            Cargo newCargo = new Cargo(cargoId, date, status, deliveryTime);
                            customer.addCargo(newCargo);
                            priorityQueue.addCargo(newCargo);
                            recentCargoStack.pushCargo(newCargo);
                            System.out.println("Cargo added successfully.");
                        }
                    }
                    break;
                case 3:
                    deliveryTree.display();
                    break;
                case 4:
                    if (!priorityQueue.isEmpty()) {
                        System.out.println("Processing: " + priorityQueue.processNextCargo());
                    } else {
                        System.out.println("No cargos to process.");
                    }
                    break;
                case 5:
                    recentCargoStack.showRecentCargos();
                    break;
                case 6:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
