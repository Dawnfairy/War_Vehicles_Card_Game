package fnk.warvehiclescardgame.model;

import javax.swing.*;

public class Ucak extends HavaAraclari {


    public Ucak(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int karaVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, sinif, altSinif, karaVurusAvantaji);
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