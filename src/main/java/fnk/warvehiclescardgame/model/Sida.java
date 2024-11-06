package fnk.warvehiclescardgame.model;

public class Sida extends Deniz {

    public Sida() {
        super(15, 0, 20);
    }

    @Override
    protected boolean havaVurusAvantaji() {
        return false; // model.Sida'nın hava vuruş avantajı yok
    }

    @Override
    public void durumGuncelle(int hasar) {
        dayaniklilik -= hasar;
        if (dayaniklilik < 0) dayaniklilik = 0;
    }
}