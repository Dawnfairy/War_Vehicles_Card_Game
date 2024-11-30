package fnk.warvehiclescardgame.model;

import javax.swing.*;

public abstract class HavaAraclari extends SavasAraclari {

    private final int karaVurusAvantaji;
    private final String altSinif;

    public HavaAraclari(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int karaVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, "Hava");
        this.karaVurusAvantaji = karaVurusAvantaji;
        this.altSinif = altSinif;
    }

    //gerekirse kullanılacak
    @Override
    public void kartPuaniGoster(JTextArea textArea) {
        super.kartPuaniGoster(textArea);
    }

    @Override
    public void durumGuncelle(int hasar, int seviyePuanArtis) {
        dayaniklilik -= hasar;
        if (dayaniklilik < 0) dayaniklilik = 0;
        seviyePuani += seviyePuanArtis;
    }

    public int getKaraVurusAvantaji() {
        return karaVurusAvantaji;
    }

    public String getAltSinif() {
        return altSinif;
    }
}
