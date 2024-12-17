import java.util.ArrayList;
import java.util.List;

public class Sehir {
    private String sehirAdi;
    private String sehirID;
    private List<Sehir> altSehirler;  // Çocuk düğümler (alt şehirler)

    public Sehir(String sehirAdi, String sehirID) {
        this.sehirAdi = sehirAdi;
        this.sehirID = sehirID;
        this.altSehirler = new ArrayList<>();
    }

    // Alt şehir ekleme
    public void altSehirEkle(Sehir altSehir) {
        altSehirler.add(altSehir);
    }

    public List<Sehir> getAltSehirler() {
        return altSehirler;
    }

    @Override
    public String toString() {
        return "Şehir: " + sehirAdi + " (ID: " + sehirID + ")";
    }
}
