package fnk.warvehiclescardgame.model;

public class Ucak extends Hava {

    public Ucak() {
        super(20, 0, 15);
    }

    @Override
    protected boolean karaVurusAvantaji() {
        return true; // Uçak, kara araçlarına karşı avantajlı
    }

    @Override
    public void durumGuncelle(int hasar) {
        dayaniklilik -= hasar;
        if (dayaniklilik < 0) dayaniklilik = 0;
    }
}