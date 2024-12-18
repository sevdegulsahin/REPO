
import java.util.ArrayList;
import java.util.List;

public class CityNode {
    private String cityName; // Şehir adı
    private int cityID; // Şehir ID'si
    private List<CityNode> children; // Alt düğümler (bağlantılı şehirler)

    // Yapıcı
    public CityNode(String cityName, int cityID) {
        this.cityName = cityName;
        this.cityID = cityID;
        this.children = new ArrayList<>();
    }

    // Şehir adını döndürür
    public String getCityName() {
        return cityName;
    }

    // Şehir ID'sini döndürür
    public int getCityID() {
        return cityID;
    }

    // Bağlı alt düğümleri döndürür
    public List<CityNode> getChildren() {
        return children;
    }

    // Alt düğüm ekler
    public void addChild(CityNode city) {
        if (city == null) {
            throw new IllegalArgumentException("Alt şehir düğümü null olamaz.");
        }
        children.add(city);
    }

    // Şehir adına göre doğrudan bağlı bir düğüm bulur
    public CityNode findChildByName(String cityName) {
        for (CityNode child : children) {
            if (child.getCityName().equalsIgnoreCase(cityName)) {
                return child;
            }
        }
        return null;
    }

    // Şehir ID'sine göre doğrudan bağlı bir düğüm bulur
    public CityNode findChildByID(int cityID) {
        for (CityNode child : children) {
            if (child.getCityID() == cityID) {
                return child;
            }
        }
        return null;
    }

    // Şehir bilgilerini okunabilir bir formatta döndürür
    @Override
    public String toString() {
        return String.format("Şehir: %s (ID: %d)", cityName, cityID);
    }

    // Alt düğümleri listelemek için yardımcı metod
    public String getChildrenInfo() {
        StringBuilder info = new StringBuilder();
        if (children.isEmpty()) {
            info.append("Bu şehre bağlı alt şehir yok.");
        } else {
            for (CityNode child : children) {
                info.append(child.toString()).append("\n");
            }
        }
        return info.toString();
    }
}

