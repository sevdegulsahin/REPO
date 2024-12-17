public class Gonderi {
    private String gonderiID;
    private String tarih;
    private boolean teslimDurumu;  // Teslim durumu
    private int teslimSuresi;  // Teslim süresi

    public Gonderi(String gonderiID, String tarih, boolean teslimDurumu, int teslimSuresi) {
        this.gonderiID = gonderiID;
        this.tarih = tarih;
        this.teslimDurumu = teslimDurumu;
        this.teslimSuresi = teslimSuresi;
    }

    public String getGonderiID() {
        return gonderiID;
    }

    public String getTarih() {
        return tarih;
    }

    public boolean isTeslimDurumu() {
        return teslimDurumu;
    }

    public int getTeslimSuresi() {
        return teslimSuresi;
    }

    @Override
    public String toString() {
        return "Gönderi ID: " + gonderiID + ", Tarih: " + tarih + ", Teslim Durumu: " + (teslimDurumu ? "Teslim Edildi" : "Teslim Edilmedi") + ", Teslim Süresi: " + teslimSuresi + " gün";
    }
}
