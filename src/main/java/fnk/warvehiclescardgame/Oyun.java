package fnk.warvehiclescardgame;

import fnk.warvehiclescardgame.model.*;
import fnk.warvehiclescardgame.player.Oyuncu;

import java.util.Random;
import java.util.Scanner;

public class Oyun {
    private final Oyuncu oyuncu;
    private final Oyuncu bilgisayar;
    private static final int MAX_HAMLE = 10;
    private static final int MAX_SEVIYE_PUANI = 20;

    public static void main(String[] args) {
        Oyun oyun = new Oyun();
        oyun.baslat();  // Oyun başlatılır ve tüm oyun döngüsü burada çalışır
    }


    public Oyun() {
        this.oyuncu = new Oyuncu(0, "Fatma", 0);
        this.bilgisayar = new Oyuncu(1, "Bilgisayar", 0);
        kartlariDagit();
    }

    // Oyunun ana akışı
    public void baslat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Oyun Başlıyor ===");
        for (int hamle = 1; hamle <= MAX_HAMLE; hamle++) {
            System.out.println("\n==== Hamle " + hamle + " ====");

            oyuncu.kartSec();
            bilgisayar.kartSec();

            for (int i = 0; i < 3; i++) {
                System.out.println("\nKARŞILAŞMA " + (i+1));
                SavasAraclari oyuncuKart = oyuncu.getOynananKartlar().get(i);
                SavasAraclari bilgisayarKart = bilgisayar.getOynananKartlar().get(i);

                System.out.println("Oyuncu Kartı: " + oyuncuKart.getClass().getSimpleName());
                System.out.println("Bilgisayar Kartı: " + bilgisayarKart.getClass().getSimpleName());

                savasSonuc(oyuncuKart, bilgisayarKart);
            }
            if (oyuncu.getKartListesi().size() <= 1 || bilgisayar.getKartListesi().size() <= 1) {
                System.out.println("\nBir tarafın kart sayısı 1'e düştü. Kartlar tamamlanıyor ve son hamle oynanıyor...");
                Random rand = new Random();

                // Kart sayısı 1'e düştüğünde fazladan kart ver ve oyunu bitir
                while (oyuncu.getKartListesi().size() < 3) {
                    oyuncu.kartEkle(rastgeleKartOlustur(rand, oyuncu.getSkor() >= 20));
                }

                while (bilgisayar.getKartListesi().size() < 3) {
                    bilgisayar.kartEkle(rastgeleKartOlustur(rand, bilgisayar.getSkor() >= MAX_SEVIYE_PUANI));
                }

                // Kartlar tamamlandıktan sonra son bir savaş
                System.out.println("\nKartlar tamamlandı, son hamle başlıyor...");
                for (int i = 0; i < 3; i++) {
                    System.out.println("\nKARŞILAŞMA " + (i+1) );

                    SavasAraclari oyuncuKart = oyuncu.getOynananKartlar().get(i);
                    SavasAraclari bilgisayarKart = bilgisayar.getOynananKartlar().get(i);

                    System.out.println("Son Hamle - Oyuncu Kartı: " + oyuncuKart.getClass().getSimpleName());
                    System.out.println("Son Hamle - Bilgisayar Kartı: " + bilgisayarKart.getClass().getSimpleName());

                    savasSonuc(oyuncuKart, bilgisayarKart);
                }

                // Oyunu bitir
                System.out.println("\nSon hamle tamamlandı. Oyun sona eriyor.");
                break;
            }
            Random rand = new Random();

            // Normal durumda her iki tarafa birer kart ekle
            oyuncu.kartEkle(rastgeleKartOlustur(rand, oyuncu.getSkor() >= 20));
            bilgisayar.kartEkle(rastgeleKartOlustur(rand, bilgisayar.getSkor() >= 20));

            System.out.println("\nOyuncuya ve bilgisayara rastgele birer kart verildi.");

            // Hamle sonrası durumu gösterme
            oyuncu.skorGoster();
            bilgisayar.skorGoster();

            // Oyunun sona erip ermediğini kontrol etme
            if (hamle == MAX_HAMLE) {
                System.out.println("\nMaksimum hamle sayısına ulaşıldı. Oyun sona eriyor.");
                break;
            } else if (oyuncu.getKartListesi().size() == 0 || bilgisayar.getKartListesi().size() == 0) {
                System.out.println("\nBir tarafın kartları tamamen tükendi. Oyun sona eriyor.");
                break;
            }
        }

        // Oyunun son durumu
        oyunuSonlandir();
        scanner.close();
    }

    // Başlangıçta oyuncuya ve bilgisayara kart dağıtma (Her birine 6'şar kart)
    private void kartlariDagit() {//tamam
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            oyuncu.kartEkle(rastgeleKartOlustur(rand, false));
            bilgisayar.kartEkle(rastgeleKartOlustur(rand, false));
        }
    }

    private SavasAraclari rastgeleKartOlustur(Random rand, boolean ekAraclarDahil) {
        // Ek araçlar dahil ise seçim havuzunu genişlet
        int maxKartTuru = ekAraclarDahil ? 6 : 3; // 3 temel araç, 6 toplam (temel + ek)
        int kartTuru = rand.nextInt(maxKartTuru); // 0 - (maxKartTuru - 1) arasında değer üretir

        return switch (kartTuru) {
            case 0 -> new Ucak(0, 20, 10, "Hava", "Ucak", 10);
            case 1 -> new Obus(0, 20, 10, "Kara", "Obus", 5);
            case 2 -> new Firkateyn(0, 25, 10, "Deniz", "Firkateyn", 5);
            case 3 -> new Siha(0, 15, 10, "Hava", "Siha",10, 10); // Ek araç
            case 4 -> new Sida(0, 15, 10, "Deniz", "Sida", 10, 10); // Ek araç
            case 5 -> new KFS(0, 10, 10, "Kara", "KFS",10, 20); // Ek araç
            default -> throw new IllegalStateException("Geçersiz kart türü: " + kartTuru);
        };
    }

    private int saldiriHesapla(SavasAraclari saldiranKart, SavasAraclari savunanKart) {
        int hasar = saldiranKart.getVurus();

        // Kara araçları için avantaj kontrolü
        switch (saldiranKart.getSinif()) {
            case "Kara" -> {
                if (savunanKart.getSinif().equals("Deniz")) {
                    hasar += ((KaraAraclari) saldiranKart).getDenizVurusAvantaji();
                    System.out.println(((KaraAraclari) saldiranKart).getAltSinif() +" avantajlı! Ekstra hasar: " + ((KaraAraclari) saldiranKart).getDenizVurusAvantaji());
                } else if (savunanKart.getSinif().equals("Hava") && ((KaraAraclari) saldiranKart).getAltSinif().equals("KFS")) {
                    hasar += ((KFS) saldiranKart).getHavaVurusAvantaji();
                    System.out.println("KFS avantajlı! Ekstra hasar: " + ((KFS) saldiranKart).getHavaVurusAvantaji());
                } else {
                    //System.out.println("Kara aracı avantaj yok.");
                }
            }
            // Hava araçları için avantaj kontrolü
            case "Hava" -> {
                if (savunanKart.getSinif().equals("Kara")) {
                    hasar += ((HavaAraclari) saldiranKart).getKaraVurusAvantaji();
                    System.out.println( ((HavaAraclari) saldiranKart).getAltSinif() +" avantajlı! Ekstra hasar: " + ((HavaAraclari) saldiranKart).getKaraVurusAvantaji());
                } else if (savunanKart.getSinif().equals("Deniz") && ((HavaAraclari) saldiranKart).getAltSinif().equals("Siha")) {
                    hasar += ((Siha) saldiranKart).getDenizVurusAvantaji();
                    System.out.println("Siha avantajlı! Ekstra hasar: " + ((Siha) saldiranKart).getDenizVurusAvantaji());
                } else {
                    //System.out.println("Hava aracı avantaj yok.");
                }
            }
            // Deniz araçları için avantaj kontrolü
            case "Deniz" -> {
                if (savunanKart.getSinif().equals("Hava")) {
                    hasar += ((DenizAraclari) saldiranKart).getHavaVurusAvantaji();
                    System.out.println( ((DenizAraclari) saldiranKart).getAltSinif() + " avantajlı! Ekstra hasar: " + ((DenizAraclari) saldiranKart).getHavaVurusAvantaji());
                } else if (savunanKart.getSinif().equals("Kara") && ((DenizAraclari) saldiranKart).getAltSinif().equals("Sida")) {
                    hasar += ((Sida) saldiranKart).getKaraVurusAvantaji();
                    System.out.println("Sida avantajlı! Ekstra hasar: " + ((Sida) saldiranKart).getKaraVurusAvantaji());
                } else {
                    //System.out.println("Deniz aracı avantaj yok.");
                }
            }
        }

        return hasar;
    }

    public void savasSonuc(SavasAraclari oyuncuKart, SavasAraclari bilgisayarKart) {

        int oyuncuVurus = saldiriHesapla(oyuncuKart, bilgisayarKart);
        int bilgisayarVurus = saldiriHesapla(bilgisayarKart, oyuncuKart);

        System.out.println("oyuncuVurus =" + oyuncuVurus);
        System.out.println("bilgisayarVurus =" + bilgisayarVurus);

        oyuncuKart.durumGuncelle(bilgisayarVurus,0);
        bilgisayarKart.durumGuncelle(oyuncuVurus, 0);

        // Oyuncu kartı dayanıklılık kontrolü
        if (oyuncuKart.getDayaniklilik() <= 0) {
            System.out.println("Oyuncunun \"" + oyuncuKart.getClass().getSimpleName() + "\" kartı elendi!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            int puanArtisi = Math.max(10, oyuncuKart.getSeviyePuani());
            bilgisayarKart.durumGuncelle(0,puanArtisi);
            bilgisayar.setSkor(bilgisayar.getSkor() + puanArtisi);
            oyuncu.getKartListesi().remove(oyuncuKart); // Oyuncunun elenen kartını listeden kaldır
            oyuncu.getSecilenKartlar().remove(oyuncuKart); // Oyuncunun elenen kartını listeden kaldır
            System.out.println("Bilgisayarın skoru " + puanArtisi + " puan arttı!");
        } else {
            System.out.println("Oyuncu-------------------------");
            oyuncuKart.kartPuaniGoster();
        }

        // Bilgisayar kartı dayanıklılık kontrolü
        if (bilgisayarKart.getDayaniklilik() <= 0) {
            System.out.println("Bilgisayarın \"" + bilgisayarKart.getClass().getSimpleName() + "\" kartı elendi!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            int puanArtisi = Math.max(10, bilgisayarKart.getSeviyePuani());
            oyuncuKart.durumGuncelle(0, puanArtisi);
            oyuncu.setSkor(oyuncu.getSkor() + puanArtisi);
            bilgisayar.getKartListesi().remove(bilgisayarKart); // Bilgisayarın elenen kartını listeden kaldır
            bilgisayar.getSecilenKartlar().remove(bilgisayarKart); // Oyuncunun elenen kartını listeden kaldır
            System.out.println("Oyuncunun skoru " + puanArtisi + " puan arttı!");
        } else {

            System.out.println("Bilgisayar----------------------");
            bilgisayarKart.kartPuaniGoster();
        }


    }

    private void oyunuSonlandir() {
        System.out.println("Oyun sona erdi.");
        System.out.println("Son skorlar:");
        oyuncu.skorGoster();
        bilgisayar.skorGoster();


        // Taraflardan birinin kartlarının tükenmesi
        if (oyuncu.getKartListesi().isEmpty() && !bilgisayar.getKartListesi().isEmpty()) {
            System.out.println("Oyuncunun kartları tükendi! Bilgisayar kazandı!");
            return;
        } else if (bilgisayar.getKartListesi().isEmpty() && !oyuncu.getKartListesi().isEmpty()) {
            System.out.println("Bilgisayarın kartları tükendi! Oyuncu kazandı!");
            return;
        }

        if (oyuncu.getSkor() > bilgisayar.getSkor()) {
            System.out.println("Oyuncu kazandı!");
        } else if (bilgisayar.getSkor() > oyuncu.getSkor()) {
            System.out.println("Bilgisayar kazandı!");
        } else {
            // Skorlar eşitse, dayanıklılık değerlerini karşılaştır
            int oyuncuToplamDayaniklilik = oyuncu.getKartListesi().stream()
                    .mapToInt(SavasAraclari::getDayaniklilik)
                    .sum();
            int bilgisayarToplamDayaniklilik = bilgisayar.getKartListesi().stream()
                    .mapToInt(SavasAraclari::getDayaniklilik)
                    .sum();

            System.out.println("Oyuncunun kalan toplam dayanıklılığı: " + oyuncuToplamDayaniklilik);
            System.out.println("Bilgisayarın kalan toplam dayanıklılığı: " + bilgisayarToplamDayaniklilik);

            if (oyuncuToplamDayaniklilik > bilgisayarToplamDayaniklilik) {
                int fark = oyuncuToplamDayaniklilik - bilgisayarToplamDayaniklilik;
                oyuncu.setSkor(oyuncu.getSkor() + fark);
                System.out.println("Oyuncu kazandı! Dayanıklılık farkı " + fark + " puan skora eklendi.");
            } else if (bilgisayarToplamDayaniklilik > oyuncuToplamDayaniklilik) {
                int fark = bilgisayarToplamDayaniklilik - oyuncuToplamDayaniklilik;
                bilgisayar.setSkor(bilgisayar.getSkor() + fark);
                System.out.println("Bilgisayar kazandı! Dayanıklılık farkı " + fark + " puan skora eklendi.");
            } else {
                System.out.println("Oyun berabere! Hem skorlar hem de dayanıklılıklar eşit.");
            }
        }
    }

}
