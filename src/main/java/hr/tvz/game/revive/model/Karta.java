package hr.tvz.game.revive.model;

import java.io.Serial;
import java.io.Serializable;

public class Karta implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String naziv;
    private final Action action;
    private final Bonus bonus;
    private final KulturniEfekt kulturniEfekt;

    public Karta(String naziv, Action action, Bonus bonus, KulturniEfekt kulturniEfekt) {
        this.naziv = naziv;
        this.action = action;
        this.bonus = bonus;
        this.kulturniEfekt = kulturniEfekt;
    }

    public String getNaziv() {
        return naziv;
    }
    public Action getAction() {
        return action;
    }
    public Bonus getBonus() {
        return bonus;
    }
    public KulturniEfekt getKulturniEfekt() {
        return kulturniEfekt;
    }

    @Override
    public String toString() {
        return naziv + " " + action + " " + bonus + " " + kulturniEfekt;
    }


}
