package fnk.warvehiclescardgame.model;

public class KFS extends KaraAraclari {

    private int havaVurusAvantaji;

    public KFS(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int denizVurusAvantaji, int havaVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, sinif, altSinif, denizVurusAvantaji);
        this.havaVurusAvantaji = havaVurusAvantaji;
    }

    @Override
    public void durumGuncelle(int hasar, int seviyePuanArtis) {
        super.durumGuncelle(hasar, seviyePuanArtis);
    }

    @Override
    public void kartPuaniGoster() {
        super.kartPuaniGoster();
    }

    public int getHavaVurusAvantaji() {
        return havaVurusAvantaji;
    }
}