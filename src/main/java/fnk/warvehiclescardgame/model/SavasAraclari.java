package fnk.warvehiclescardgame.model;

public abstract class SavasAraclari {

    protected int dayaniklilik;
    protected int seviyePuani;
    protected int vurus;

    public int getDayaniklilik() {
        return dayaniklilik;
    }

    public void setDayaniklilik(int dayaniklilik) {
        this.dayaniklilik = dayaniklilik;
    }

    public int getSeviyePuani() {
        return seviyePuani;
    }

    public void setSeviyePuani(int seviyePuani) {
        this.seviyePuani = seviyePuani;
    }

    public int getVurus() {
        return vurus;
    }

    public void setVurus(int vurus) {
        this.vurus = vurus;
    }

    public String getSinif() {
        return sinif;
    }

    public void setSinif(String sinif) {
        this.sinif = sinif;
    }

    protected String sinif;

    // Constructor
    public SavasAraclari(int dayaniklilik, int seviyePuani, int vurus, String sinif) {
        this.dayaniklilik = dayaniklilik;
        this.seviyePuani = seviyePuani;
        this.vurus = vurus;
        this.sinif = sinif;
    }

    // Kart puanlarını gösteren metot
    public void kartPuaniGoster() {
        System.out.println("Dayanıklılık: " + dayaniklilik + ", Seviye Puanı: " + seviyePuani);
    }

    // Abstract metot - Her alt sınıf bu metodu kendine göre uyarlayacak
    public abstract void durumGuncelle(int hasar);
}
