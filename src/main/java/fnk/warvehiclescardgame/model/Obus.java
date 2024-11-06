package fnk.warvehiclescardgame.model;

public class Obus extends Kara {

    public Obus() {
        super(20, 0, 15);
    }

    @Override
    protected boolean denizVurusAvantaji() {
        return true; // Obüs, deniz araçlarına karşı avantajlı
    }

    @Override
    public void durumGuncelle(int hasar) {
        dayaniklilik -= hasar;
        if (dayaniklilik < 0) dayaniklilik = 0;
    }
}