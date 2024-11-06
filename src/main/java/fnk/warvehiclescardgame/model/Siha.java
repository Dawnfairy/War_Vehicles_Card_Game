package fnk.warvehiclescardgame.model;

public class Siha extends Hava {

    public Siha() {
        super(15, 0, 20);
    }

    @Override
    protected boolean karaVurusAvantaji() {
        return false; // SİHA'nın kara vuruş avantajı yok
    }

    @Override
    public void durumGuncelle(int hasar) {
        dayaniklilik -= hasar;
        if (dayaniklilik < 0) dayaniklilik = 0;
    }
}