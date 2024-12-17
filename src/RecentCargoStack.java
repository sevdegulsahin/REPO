import java.util.Stack;

public class RecentCargoStack {
    private Stack<Cargo> stack;

    public RecentCargoStack() {
        stack = new Stack<>();
    }

    public void pushCargo(Cargo cargo) {
        stack.push(cargo);
    }

    public void showRecentCargos() {
        if (stack.isEmpty()) {
            System.out.println("No recent cargos available.");
            return;
        }
        int count = 0;
        for (Cargo cargo : stack) {
            if (count == 5) break;
            System.out.println(cargo);
            count++;
        }
    }
}
