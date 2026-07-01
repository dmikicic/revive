package hr.tvz.game.revive.model;

import java.io.Serializable;

public class PermaFrostPolje implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int red;
    private final int stupac;
    private final Bonus bonus;
    private final int vrijednost;
    private boolean otopljeno;

    public PermaFrostPolje(int red, int stupac, Bonus bonus, int vrijednost) {
        this.red = red;
        this.stupac = stupac;
        this.bonus = bonus;
        this.vrijednost = vrijednost;
        this.otopljeno = false;
    }
    public int getRed() {
        return red;
    }
    public int getStupac() {
        return stupac;
    }
    public Bonus getBonus() {
        return bonus;
    }
    public int getVrijednost() {
        return vrijednost;
    }
    public boolean isOtopljeno() {
        return otopljeno;
    }

    public void otopi(){
        this.otopljeno = true;

    }
    @Override
    public String toString(){
        return "PermaFrostPolje: " + red + " " + stupac + " " + bonus + " " + vrijednost + " " + otopljeno;
    }
}
