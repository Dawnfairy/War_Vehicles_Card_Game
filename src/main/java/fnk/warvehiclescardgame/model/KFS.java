package fnk.warvehiclescardgame.model;

public class KFS extends Kara {

    public KFS() {
        super(10, 0, 20);
    }

    @Override
    protected boolean denizVurusAvantaji() {
        return false; // model.KFS'nin deniz vuruş avantajı yok
    }

    @Override
    public void durumGuncelle(int hasar) {
        dayaniklilik -= hasar;
        if (dayaniklilik < 0) dayaniklilik = 0;
    }
}