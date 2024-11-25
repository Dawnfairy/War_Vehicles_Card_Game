package fnk.warvehiclescardgame.model;

public abstract class SavasAraclari {

    protected int seviyePuani;
    protected int dayaniklilik;
    protected int vurus;
    protected String sinif;

    public SavasAraclari(int seviyePuani, int dayaniklilik, int vurus, String sinif) {
        this.seviyePuani = seviyePuani;
        this.dayaniklilik = dayaniklilik;
        this.vurus = vurus;
        this.sinif = sinif;
    }

    // Kart puanlarını gösteren metot
    public void kartPuaniGoster() {//dayanıklılık ve seviye puanı ¨ozelliklerini oyunda g¨ostermek i¸cin
        System.out.println("Dayanıklılık: " + dayaniklilik + ", Seviye Puanı: " + seviyePuani);
    }

    public abstract void durumGuncelle(int hasar, int seviyePuanArtis);

    public int getDayaniklilik() {
        return dayaniklilik;
    }


    public int getSeviyePuani() {
        return seviyePuani;
    }

    public int getVurus() {
        return vurus;
    }

    public String getSinif() {
        return sinif;
    }
}
