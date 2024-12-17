import java.util.ArrayList;
import java.util.List;

class DeliveryTreeNode {
    String cityName;
    int cityId;
    List<DeliveryTreeNode> children;

    public DeliveryTreeNode(String cityName, int cityId) {
        this.cityName = cityName;
        this.cityId = cityId;
        this.children = new ArrayList<>();
    }

    public void addChild(DeliveryTreeNode child) {
        children.add(child);
    }
}

public class DeliveryTree {
    DeliveryTreeNode root;

    public DeliveryTree(String centralOffice) {
        root = new DeliveryTreeNode(centralOffice, 0);
    }

    public void printTree(DeliveryTreeNode node, String indent) {
        if (node == null) return;
        System.out.println(indent + node.cityName + " (ID: " + node.cityId + ")");
        for (DeliveryTreeNode child : node.children) {
            printTree(child, indent + "  ");
        }
    }

    public void display() {
        printTree(root, "");
    }
}
