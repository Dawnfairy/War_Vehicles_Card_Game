package fnk.warvehiclescardgame.player;

import fnk.warvehiclescardgame.model.SavasAraclari;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Oyuncu {
    private int oyuncuID;
    private String oyuncuAdi;
    private int skor;
    private List<SavasAraclari> kartListesi = new ArrayList<>();// 6 adet (elimizdeki kartlar)
    private List<SavasAraclari> secilenKartlar = new ArrayList<>();//önceden seçilenler
    private List<SavasAraclari> oynananKartlar = new ArrayList<>(); // 3 adet


    public Oyuncu() {
    }

    public Oyuncu(int oyuncuID, String oyuncuAdi, int skor) {
        this.oyuncuID = oyuncuID;
        this.oyuncuAdi = oyuncuAdi;
        this.skor = skor;
    }

    public void skorGoster() {
        System.out.println(oyuncuAdi + " Skor: " + skor);
    }

    public void kartSec() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n==== Oyuncu Kart Seçimi ====");

        getOynananKartlar().clear();
        kartlariGoster();

        if (this.oyuncuID == 0) {//insan
            for (int i = 0; i < 3; i++) {
                // Tüm kartlar seçildiyse listeyi sıfırla
                resetKartListesi();

                int secim = alKullaniciSecimi(scanner);

                secilenKartlar.add(kartListesi.get(secim));
                oynananKartlar.add(kartListesi.get(secim));

            }
            System.out.println("\nOyuncunun Seçtiği Kartlar:");
            for (SavasAraclari kart : oynananKartlar) {
                System.out.println("- " + kart.getClass().getSimpleName());
            }
        } else {//bilgisayar
            Random rand = new Random();
            while (oynananKartlar.size() < 3) {

                resetKartListesi();
                SavasAraclari kart;
                do {
                    kart = kartListesi.get(rand.nextInt(kartListesi.size()));
                } while (secilenKartlar.contains(kart) || oynananKartlar.contains(kart));

                if (!secilenKartlar.contains(kart) && !oynananKartlar.contains(kart)) {
                    secilenKartlar.add(kart);
                    oynananKartlar.add(kart);
                }
            }
            System.out.println("\nBilgisayarın Seçtiği Kartlar:");
            for (SavasAraclari kart : oynananKartlar) {
                System.out.println("- " + kart.getClass().getSimpleName());
            }
            System.out.println("\nBilgisayar kartlarını rastgele seçti.");
        }
    }


    //eklenen metodlar
    private int alKullaniciSecimi(Scanner scanner) {
        int secim;
        while (true) {
            try {
                System.out.print("Bir kart numarası seçin (1-" + kartListesi.size() + "): ");
                secim = scanner.nextInt() - 1;

                if (secim >= 0 && secim < kartListesi.size() && !secilenKartlar.contains(kartListesi.get(secim))) {
                    return secim; // Geçerli bir seçim yapıldığında döndür
                }

                if (secilenKartlar.contains(kartListesi.get(secim)) && !oynananKartlar.contains(kartListesi.get(secim))) {
                    System.out.println("Bu kart daha önce seçildi! Lütfen başka bir kart seçin.");
                } else if (oynananKartlar.contains(kartListesi.get(secim))) {
                    System.out.println("Bu kart zaten seçildi! Lütfen başka bir kart seçin.");
                } else {
                    System.out.println("Geçersiz kart numarası! Yeniden deneyin.");
                }
            } catch (Exception e) {
                System.out.println("Geçersiz giriş! Lütfen bir tam sayı girin.");
                scanner.next(); // Hatalı girdiyi temizle
            }
        }
    }

    private void resetKartListesi() {
        if (secilenKartlar.size() == kartListesi.size()) {
            System.out.println("Tüm kartlar seçildi, liste sıfırlanıyor.");
            secilenKartlar.clear();
        }
    }

    public void kartEkle(SavasAraclari kart) {
        kartListesi.add(kart);
    }

    public void kartlariGoster() {
        System.out.println(oyuncuAdi + " Kartları:");
        for (SavasAraclari kart : kartListesi) {
            System.out.println("- " + kart.getClass().getSimpleName());
        }
    }


    public int getOyuncuID() {
        return oyuncuID;
    }

    public void setOyuncuID(int oyuncuID) {
        this.oyuncuID = oyuncuID;
    }

    public String getOyuncuAdi() {
        return oyuncuAdi;
    }

    public void setOyuncuAdi(String oyuncuAdi) {
        this.oyuncuAdi = oyuncuAdi;
    }


    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public List<SavasAraclari> getKartListesi() {
        return kartListesi;
    }

    public List<SavasAraclari> getSecilenKartlar() {
        return secilenKartlar;
    }

    public List<SavasAraclari> getOynananKartlar() {
        return oynananKartlar;
    }

}
