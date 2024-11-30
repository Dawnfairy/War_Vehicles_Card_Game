package fnk.warvehiclescardgame.model;

import javax.swing.*;

public abstract class KaraAraclari extends SavasAraclari {

    private final int denizVurusAvantaji;
    private final String altSinif;

    public KaraAraclari(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int denizVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, sinif);
        this.denizVurusAvantaji = denizVurusAvantaji;
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

    public int getDenizVurusAvantaji() {
        return denizVurusAvantaji;
    }

    public String getAltSinif() {
        return altSinif;
    }
}