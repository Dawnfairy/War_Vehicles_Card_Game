package fnk.warvehiclescardgame.model;

public abstract class Deniz extends SavasAraclari {
    // model.Deniz araçlarının hava araçlarına karşı vuruş avantajı
    protected abstract boolean havaVurusAvantaji();

    public Deniz(int dayaniklilik, int seviyePuani, int vurus) {
        super(dayaniklilik, seviyePuani, vurus, "model.Deniz");
    }
}
