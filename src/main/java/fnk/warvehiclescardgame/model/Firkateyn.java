package fnk.warvehiclescardgame.model;

import javax.swing.*;

public class Firkateyn extends DenizAraclari {
    public Firkateyn(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int havaVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, sinif, altSinif, havaVurusAvantaji);
    }

    @Override
    public void durumGuncelle(int hasar, int seviyePuanArtis) {
        super.durumGuncelle(hasar, seviyePuanArtis);
    }

    @Override
    public void kartPuaniGoster(JTextArea textArea) {
        super.kartPuaniGoster(textArea);
    }

}