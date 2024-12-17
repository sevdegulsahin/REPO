public class KargoTakipSistemi {

    public static void main(String[] args) {
        // Müşteri yönetimi
        Musteri musteri1 = new Musteri("123", "Fatma Nur");
        musteri1.gonderiEkle("G01", "2024-12-15", true, 3);
        musteri1.gonderiEkle("G02", "2024-12-13", false, 0);
        musteri1.gonderiEkle("G03", "2024-12-16", true, 2);
        musteri1.gonderiGecmisiniGoster();

        // Kargo rotalama sistemi (şehirler ağacı)
        Sehir merkez = new Sehir("Merkez", "M00");
        Sehir sehir1 = new Sehir("Ankara", "S01");
        Sehir sehir2 = new Sehir("İstanbul", "S02");
        Sehir sehir3 = new Sehir("İzmir", "S03");
        Sehir sehir4 = new Sehir("Bursa", "S04");

        // Ağaç yapısına alt şehirler ekle
        merkez.altSehirEkle(sehir1);
        merkez.altSehirEkle(sehir2);
        sehir1.altSehirEkle(sehir3);
        sehir2.altSehirEkle(sehir4);

        // Kargo rotalama sistemi
        KargoRotalama rotalama = new KargoRotalama(merkez);
        rotalama.agaciYazdir();

        // En kısa teslimat süresini bulma
        int enKisaSure = rotalama.enKisaTeslimatSuresi();
        System.out.println("En kısa teslimat süresi: " + enKisaSure + " gün");
    }
}
