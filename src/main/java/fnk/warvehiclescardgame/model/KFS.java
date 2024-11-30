package fnk.warvehiclescardgame.model;

import javax.swing.*;

public class KFS extends KaraAraclari {

    private final int havaVurusAvantaji;

    public KFS(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int denizVurusAvantaji, int havaVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, sinif, altSinif, denizVurusAvantaji);
        this.havaVurusAvantaji = havaVurusAvantaji;
    }

    @Override
    public void durumGuncelle(int hasar, int seviyePuanArtis) {
        super.durumGuncelle(hasar, seviyePuanArtis);
    }

    @Override
    public void kartPuaniGoster(JTextArea textArea) {
        super.kartPuaniGoster(textArea);
    }

    public int getHavaVurusAvantaji() {
        return havaVurusAvantaji;
    }
}