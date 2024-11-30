package fnk.warvehiclescardgame.model;

import javax.swing.*;

public class Siha extends HavaAraclari {


    private final int denizVurusAvantaji;

    public Siha(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int karaVurusAvantaji, int denizVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, sinif, altSinif, karaVurusAvantaji);
        this.denizVurusAvantaji = denizVurusAvantaji;
    }

    @Override
    public void durumGuncelle(int hasar, int seviyePuanArtis) {
        super.durumGuncelle(hasar, seviyePuanArtis);
    }

    @Override
    public void kartPuaniGoster(JTextArea textArea) {
        super.kartPuaniGoster(textArea);
    }

    public int getDenizVurusAvantaji() {
        return denizVurusAvantaji;
    }

}