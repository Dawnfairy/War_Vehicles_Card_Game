package fnk.warvehiclescardgame.model;

public class Obus extends KaraAraclari {

    public Obus(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int denizVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, sinif, altSinif, denizVurusAvantaji);
    }

    @Override
    public void durumGuncelle(int hasar, int seviyePuanArtis) {
        super.durumGuncelle(hasar, seviyePuanArtis);
    }

    @Override
    public void kartPuaniGoster() {
        super.kartPuaniGoster();
    }
}