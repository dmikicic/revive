package hr.tvz.game.revive.model;

import java.io.Serializable;

public abstract class Radnik implements Serializable {
    private static final long serialVersionUID = 1L;

    protected final TipRadnika tipRadnika;
    protected boolean iskoristen;

    protected Radnik (TipRadnika tipRadnika){
        this.tipRadnika = tipRadnika;
        this.iskoristen = false;

    }
    public TipRadnika getTipRadnika() {
        return tipRadnika;
    }
    public boolean isIskoristen() {
        return iskoristen;
    }

    public void oznaciIskoristenim () {
        this.iskoristen = true;
    }

    public void resetiraj(){
        this.iskoristen = false;
    }

    public abstract int aktivirajRadnika (PermaFrostPloca ploca, PermaFrostPolje polje);
    public abstract String getSposobnostRadnika();
    public abstract String getUvjetRadnika();

}
