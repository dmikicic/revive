package hr.tvz.game.revive;

import hr.tvz.game.revive.model.Radnik;
import hr.tvz.game.revive.model.TipRadnika;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Igrac implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String ime;
    private final PlayerBoard playerBoard;
    private final List<Radnik> radnici;
    private int bodovi;

    public Igrac(String ime, PlayerBoard playerBoard, List<Radnik> radnici) {
        this.ime = ime;
        this.playerBoard = playerBoard;
        this.radnici = new ArrayList<>(radnici);
        this.bodovi = 0;
    }

    public Radnik dohvatiRadnika(TipRadnika tip){
        for (Radnik radnik : radnici) {
            if (radnik.getTipRadnika() == tip) {
                return radnik;
            }
        }
        return null;
    }

    public boolean neiskoristenRadnik(){
        for (Radnik radnik : radnici) {
            if (!radnik.isIskoristen() ){
                return true;
            }
        }
        return false;
    }

    public void dodajBodove(int bodovi){
        this.bodovi += bodovi;
    }

    public void resetirajRundu(){
        for (Radnik radnik : radnici) {
            radnik.resetiraj();
        }
    }

    public String getIme() {
        return ime;
    }
    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }
    public List<Radnik> getRadnici() {
        return radnici;
    }
    public int getBodovi() {
        return bodovi;
    }


}
