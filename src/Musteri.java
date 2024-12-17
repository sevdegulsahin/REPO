import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Musteri {
    private String musteriID;
    private String isimSoyisim;
    private LinkedList<Gonderi> gonderiGecmisi; // Sıralı ekleme için LinkedList
    private Stack<Gonderi> gonderiStack; // Son 5 gönderi için Stack

    public Musteri(String musteriID, String isimSoyisim) {
        this.musteriID = musteriID;
        this.isimSoyisim = isimSoyisim;
        this.gonderiGecmisi = new LinkedList<>();
        this.gonderiStack = new Stack<>();
    }

    // Gönderi ekleme
    public void gonderiEkle(String gonderiID, String tarih, boolean teslimDurumu, int teslimSuresi) {
        Gonderi yeniGonderi = new Gonderi(gonderiID, tarih, teslimDurumu, teslimSuresi);
        // Gönderiyi sıralı listeye ekleme
        gonderiGecmisi.add(yeniGonderi);
        gonderiGecmisi.sort((g1, g2) -> g1.getTarih().compareTo(g2.getTarih()));

        // Gönderiyi stack'e ekleme
        gonderiStack.push(yeniGonderi);
        if (gonderiStack.size() > 5) {
            gonderiStack.remove(0);  // Stack'te sadece son 5 gönderi kalacak
        }
    }

    // Gönderim geçmişini göster
    public void gonderiGecmisiniGoster() {
        if (gonderiGecmisi.isEmpty()) {
            System.out.println("Gönderim geçmişi bulunmamaktadır.");
            return;
        }
        System.out.println(isimSoyisim + " için gönderim geçmişi:");
        for (Gonderi gonderi : gonderiGecmisi) {
            System.out.println(gonderi);
        }
    }

    // Son 5 gönderiyi gösterme
    public void sonBesGonderiyiGoster() {
        if (gonderiStack.isEmpty()) {
            System.out.println("Gönderim geçmişi boş.");
            return;
        }
        System.out.println(isimSoyisim + " için son 5 gönderi:");
        for (Gonderi gonderi : gonderiStack) {
            System.out.println(gonderi);
        }
    }

    // Teslim Edilmiş Kargo Arama (Binary Search)
    public Gonderi teslimEdilmisKargoAra(String gonderiID) {
        // Teslim edilmiş kargoları filtrele ve sıralı liste oluştur
        List<Gonderi> teslimEdilmisKargolar = new ArrayList<>();
        for (Gonderi gonderi : gonderiGecmisi) {
            if (gonderi.isTeslimDurumu()) {
                teslimEdilmisKargolar.add(gonderi);
            }
        }

        // Kargo ID'ye göre sıralama
        Collections.sort(teslimEdilmisKargolar, Comparator.comparing(Gonderi::getGonderiID));

        // Binary Search uygulama
        int index = Collections.binarySearch(teslimEdilmisKargolar, new Gonderi(gonderiID), Comparator.comparing(Gonderi::getGonderiID));
        if (index >= 0) {
            return teslimEdilmisKargolar.get(index);
        } else {
            System.out.println("Kargo bulunamadı.");
            return null;
        }
    }

    // Teslim Edilmemiş Kargoları Sıralama (Quick Sort)
    public List<Gonderi> teslimEdilmemisKargoSirala() {
        List<Gonderi> teslimEdilmemisKargolar = new ArrayList<>();
        for (Gonderi gonderi : gonderiGecmisi) {
            if (!gonderi.isTeslimDurumu()) {
                teslimEdilmemisKargolar.add(gonderi);
            }
        }
        quickSort(teslimEdilmemisKargolar, 0, teslimEdilmemisKargolar.size() - 1);
        return teslimEdilmemisKargolar;
    }

    // Quick Sort algoritması
    private void quickSort(List<Gonderi> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(List<Gonderi> list, int low, int high) {
        Gonderi pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).getTeslimSuresi() < pivot.getTeslimSuresi()) {
                i++;
                Gonderi temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Gonderi temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
}
