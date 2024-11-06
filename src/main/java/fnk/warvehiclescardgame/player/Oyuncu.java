package fnk.warvehiclescardgame.player;

import fnk.warvehiclescardgame.model.SavasAraclari;

import java.util.ArrayList;
import java.util.List;

public class Oyuncu {
    private int oyuncuID;
    private String oyuncuAdi;
    private int skor;
    private List<SavasAraclari> kartListesi;

    public Oyuncu(int oyuncuID, String oyuncuAdi) {
        this.oyuncuID = oyuncuID;
        this.oyuncuAdi = oyuncuAdi;
        this.skor = 0;
        this.kartListesi = new ArrayList<>();
    }

    public void skorGoster() {
        System.out.println("Skor: " + skor);
    }

    public void kartSec(SavasAraclari kart) {
        if (!kartListesi.contains(kart)) {
            kartListesi.add(kart);
        }
    }

    public void kartEkle(SavasAraclari kart) {
        kartListesi.add(kart);
    }

    public List<SavasAraclari> getKartListesi() {
        return kartListesi;
    }
}