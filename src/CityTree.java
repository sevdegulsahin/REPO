

import java.util.ArrayList;
import java.util.List;

public class CityTree {
    private CityNode root;

    public CityTree() {
        root = new CityNode("Istanbul", 1);
        initializeTree();
    }

    public CityNode getRoot() {
        return root;
    }

    public void setRoot(CityNode root) {
        this.root = root;
    }

    // Teslimat rotasını ağaç yapısı ile başlatır
    private void initializeTree() {
        CityNode ankara = new CityNode("Ankara", 2);
        CityNode izmir = new CityNode("Izmir", 3);
        CityNode bursa = new CityNode("Bursa", 4);

        ankara.addChild(new CityNode("Eskisehir", 5));
        izmir.addChild(new CityNode("Aydın", 6));
        bursa.addChild(new CityNode("Balıkesir", 7));

        root.addChild(ankara);
        root.addChild(izmir);
        root.addChild(bursa);
    }

    // Şehir adı ile şehir düğümünü bulur
    public CityNode findCityByName(String cityName) {
        if (root == null) return null;
        return findCityByNameRecursive(root, cityName);
    }

    private CityNode findCityByNameRecursive(CityNode node, String cityName) {
        if (node.getCityName().equalsIgnoreCase(cityName)) {
            return node;
        }
        for (CityNode child : node.getChildren()) {
            CityNode found = findCityByNameRecursive(child, cityName);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    // Ağacın tüm şehirlerini döndürür
    public List<CityNode> getAllCities() {
        List<CityNode> allCities = new ArrayList<>();
        collectCities(root, allCities);
        return allCities;
    }

    private void collectCities(CityNode node, List<CityNode> allCities) {
        if (node != null) {
            allCities.add(node);
            for (CityNode child : node.getChildren()) {
                collectCities(child, allCities);
            }
        }
    }

    // Verilen şehir adına göre teslimat süresini hesaplar
    public int calculateDeliveryTime(String cityName) {
        CityNode city = findCityByName(cityName);
        if (city == null) {
            throw new IllegalArgumentException("Şehir bulunamadı: " + cityName);
        }
        return calculateDepth(root, city, 0);
    }

    // Verilen şehir düğümüne göre teslimat süresini hesaplar
    public int calculateDeliveryTime(CityNode cityNode) {
        if (cityNode == null) {
            throw new IllegalArgumentException("Şehir düğümü null olamaz.");
        }
        return calculateDepth(root, cityNode, 0);
    }

    // Hedef düğüme kadar olan derinliği hesaplar (Teslimat süresi)
    private int calculateDepth(CityNode current, CityNode target, int depth) {
        if (current == target) {
            return depth;
        }
        for (CityNode child : current.getChildren()) {
            int result = calculateDepth(child, target, depth + 1);
            if (result != -1) {
                return result;
            }
        }
        return -1;
    }

    // Teslimat rotalarını konsol veya arayüz için formatlar
    public String printDeliveryRoutes() {
        StringBuilder routes = new StringBuilder();
        printRoutesRecursive(root, "", routes);
        return routes.toString();
    }

    private void printRoutesRecursive(CityNode node, String prefix, StringBuilder routes) {
        if (node != null) {
            routes.append(prefix).append(node.getCityName()).append("\n");
            for (CityNode child : node.getChildren()) {
                printRoutesRecursive(child, prefix + "--", routes);
            }
        }
    }
}

