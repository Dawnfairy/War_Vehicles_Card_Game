package fnk.warvehiclescardgame.model;

public abstract class HavaAraclari extends SavasAraclari {

    private int karaVurusAvantaji;
    private String altSinif;

    public HavaAraclari(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif, int karaVurusAvantaji) {
        super(seviyePuani, dayaniklilik, vurus, "Hava");
        this.karaVurusAvantaji = karaVurusAvantaji;
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

    public int getKaraVurusAvantaji() {
        return karaVurusAvantaji;
    }

    public String getAltSinif() {
        return altSinif;
    }
}
