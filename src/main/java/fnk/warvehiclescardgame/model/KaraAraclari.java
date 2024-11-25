package fnk.warvehiclescardgame.model;

public abstract class KaraAraclari extends SavasAraclari {

    private int denizVurusAvantaji;
    private String altSinif;

    public KaraAraclari(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int denizVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, sinif);
        this.denizVurusAvantaji = denizVurusAvantaji;
        this.altSinif = altSinif;
    }

    //gerekirse kullanılacak
    @Override
    public void kartPuaniGoster() {
        super.kartPuaniGoster();
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