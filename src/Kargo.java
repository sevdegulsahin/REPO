public class Kargo implements Comparable<Kargo> {
    private String gonderiID;
    private int teslimatSuresi; // gün cinsinden
    private String kargoDurumu; // İşleme Alındı / Teslimatta / Teslim Edildi

    public Kargo(String gonderiID, int teslimatSuresi, String kargoDurumu) {
        this.gonderiID = gonderiID;
        this.teslimatSuresi = teslimatSuresi;
        this.kargoDurumu = kargoDurumu;
    }

    public String getGonderiID() {
        return gonderiID;
    }

    public int getTeslimatSuresi() {
        return teslimatSuresi;
    }

    public String getKargoDurumu() {
        return kargoDurumu;
    }

    public void setKargoDurumu(String kargoDurumu) {
        this.kargoDurumu = kargoDurumu;
    }

    @Override
    public int compareTo(Kargo other) {
        return Integer.compare(this.teslimatSuresi, other.teslimatSuresi); // Teslimat süresi kısa olanlar öncelikli
    }

    @Override
    public String toString() {
        return "Gönderi ID: " + gonderiID + ", Teslimat Süresi: " + teslimatSuresi +
                " gün, Durum: " + kargoDurumu;
    }
}
