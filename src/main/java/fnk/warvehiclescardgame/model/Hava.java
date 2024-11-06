package fnk.warvehiclescardgame.model;

public abstract class Hava extends SavasAraclari {
    // model.Hava araçlarının kara araçlarına karşı vuruş avantajı
    protected abstract boolean karaVurusAvantaji();

    public Hava(int dayaniklilik, int seviyePuani, int vurus) {
        super(dayaniklilik, seviyePuani, vurus, "hava");
    }
}
