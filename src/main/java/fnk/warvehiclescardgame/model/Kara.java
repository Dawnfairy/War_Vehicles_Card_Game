package fnk.warvehiclescardgame.model;

public abstract class Kara extends SavasAraclari {
    // model.Kara araçlarının deniz araçlarına karşı vuruş avantajı
    protected abstract boolean denizVurusAvantaji();

    public Kara(int dayaniklilik, int seviyePuani, int vurus) {
        super(dayaniklilik, seviyePuani, vurus, "model.Kara");
    }
}