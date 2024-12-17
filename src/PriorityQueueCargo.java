import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueCargo {
    private PriorityQueue<Cargo> priorityQueue;

    public PriorityQueueCargo() {
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(c -> c.deliveryTime));
    }

    public void addCargo(Cargo cargo) {
        priorityQueue.add(cargo);
    }

    public Cargo processNextCargo() {
        return priorityQueue.poll();
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }
}
