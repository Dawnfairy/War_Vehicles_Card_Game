package fnk.warvehiclescardgame.model;

public class Firkateyn extends Deniz {

    public Firkateyn() {
        super(25, 0, 15);
    }

    @Override
    protected boolean havaVurusAvantaji() {
        return true; // Fırkateyn, hava araçlarına karşı avantajlı
    }

    @Override
    public void durumGuncelle(int hasar) {
        dayaniklilik -= hasar;
        if (dayaniklilik < 0) dayaniklilik = 0;
    }
}