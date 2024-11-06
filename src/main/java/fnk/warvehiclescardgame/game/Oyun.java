package fnk.warvehiclescardgame.game;

import fnk.warvehiclescardgame.player.Oyuncu;
import fnk.warvehiclescardgame.model.Hava;
import fnk.warvehiclescardgame.model.Kara;
import fnk.warvehiclescardgame.model.SavasAraclari;

public class Oyun {
    private Oyuncu oyuncu;
    private Oyuncu bilgisayar;

    public Oyun() {
        this.oyuncu = new Oyuncu(1, "player.Oyuncu");
        this.bilgisayar = new Oyuncu(2, "Bilgisayar");
    }

    public void saldiriHesapla(SavasAraclari oyuncuKart, SavasAraclari bilgisayarKart) {
        int oyuncuHasar = oyuncuKart.getVurus();
        int bilgisayarHasar = bilgisayarKart.getVurus();

        // Avantaj durumlarına göre saldırı değerleri hesaplanır.
        if (oyuncuKart instanceof Hava && bilgisayarKart instanceof Kara) {
            oyuncuHasar += 10;
        } else if (bilgisayarKart instanceof Hava && oyuncuKart instanceof Kara) {
            bilgisayarHasar += 10;
        }
        // Burada diğer avantaj hesaplamalarını da yapabilirsiniz

        bilgisayarKart.durumGuncelle(oyuncuHasar);
        oyuncuKart.durumGuncelle(bilgisayarHasar);
    }
}