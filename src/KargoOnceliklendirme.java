import java.util.PriorityQueue;

public class KargoOnceliklendirme {
    private PriorityQueue<Kargo> kargoKuyrugu;

    public KargoOnceliklendirme() {
        kargoKuyrugu = new PriorityQueue<>();
    }

    // Kargo ekle
    public void kargoEkle(Kargo kargo) {
        kargoKuyrugu.offer(kargo);
        System.out.println("Kargo eklendi: " + kargo);
    }

    // Öncelikli kargoyu işleme al
    public void kargoIslemeAl() {
        if (!kargoKuyrugu.isEmpty()) {
            Kargo oncelikliKargo = kargoKuyrugu.poll();
            oncelikliKargo.setKargoDurumu("Teslimatta");
            System.out.println("İşleme Alınan Kargo: " + oncelikliKargo);
        } else {
            System.out.println("İşleme alınacak kargo yok.");
        }
    }

    // Kargo kuyruğunu göster
    public void kargoKuyrugunuGoster() {
        if (kargoKuyrugu.isEmpty()) {
            System.out.println("Kargo kuyruğu boş.");
        } else {
            System.out.println("Kargo Kuyruğu:");
            for (Kargo kargo : kargoKuyrugu) {
                System.out.println(kargo);
            }
        }
    }
}
