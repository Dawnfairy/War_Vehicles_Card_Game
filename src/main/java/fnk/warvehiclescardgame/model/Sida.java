package fnk.warvehiclescardgame.model;

public class Sida extends DenizAraclari {

    private int karaVurusAvantaji;

    public Sida(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int havaVurusAvantaji, int karaVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, sinif, altSinif, havaVurusAvantaji);
        this.karaVurusAvantaji = karaVurusAvantaji;
    }

    @Override
    public void durumGuncelle(int hasar, int seviyePuanArtis) {
        super.durumGuncelle(hasar, seviyePuanArtis);
    }

    @Override
    public void kartPuaniGoster() {
        super.kartPuaniGoster();
    }

    public int getKaraVurusAvantaji() {
        return karaVurusAvantaji;
    }
}