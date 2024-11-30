package fnk.warvehiclescardgame.model;

import javax.swing.*;

public abstract class DenizAraclari extends SavasAraclari {

    private final int havaVurusAvantaji;
    private final String altSinif;

    public DenizAraclari(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int havaVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, sinif);
        this.havaVurusAvantaji = havaVurusAvantaji;
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

    public int getHavaVurusAvantaji() {
        return havaVurusAvantaji;
    }

    public String getAltSinif() {
        return altSinif;
    }
}
