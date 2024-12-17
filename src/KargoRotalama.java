import java.util.ArrayList;
import java.util.List;

public class KargoRotalama {
    private Sehir merkez;  // Kök düğüm (kargo şirketinin merkezi)

    public KargoRotalama(Sehir merkez) {
        this.merkez = merkez;
    }

    // Ağaç yapısını yazdırma (konsolda görselleştirme)
    public void agaciYazdir() {
        agaciYazdir(merkez, 0);
    }

    private void agaciYazdir(Sehir sehir, int seviye) {
        // Ağaç seviyesine göre içeri kaydırma (indentation)
        for (int i = 0; i < seviye; i++) {
            System.out.print("    ");
        }
        System.out.println(sehir);

        for (Sehir altSehir : sehir.getAltSehirler()) {
            agaciYazdir(altSehir, seviye + 1);
        }
    }

    // En kısa rotayı bulma (ağaç derinliğine göre teslimat süresi)
    public int enKisaTeslimatSuresi() {
        return agacinDerinligi(merkez) - 1;  // Kök düğüm teslimat sayılmadığı için -1
    }

    // Ağaç derinliği hesaplama
    private int agacinDerinligi(Sehir sehir) {
        if (sehir.getAltSehirler().isEmpty()) {
            return 1;
        }
        int maxDerinlik = 0;
        for (Sehir altSehir : sehir.getAltSehirler()) {
            maxDerinlik = Math.max(maxDerinlik, agacinDerinligi(altSehir));
        }
        return maxDerinlik + 1;
    }
}
