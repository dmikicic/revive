package hr.tvz.game.revive;

import hr.tvz.game.revive.model.Karta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerBoard implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Karta> spil;
    private final List<Karta> karteIgraca;
    private final List<Karta> odlagaliste;

    public PlayerBoard(List<Karta> pocetneKarte) {

        this.spil = new ArrayList<>(pocetneKarte);
        this.karteIgraca = new ArrayList<>();
        this.odlagaliste = new ArrayList<>();
    }

    public Karta vuciKartu(){
       if (spil.isEmpty() && !odlagaliste.isEmpty()) {
           Collections.shuffle(odlagaliste);
           spil.addAll(odlagaliste);
           odlagaliste.clear();
       }
       if (spil.isEmpty()) {
           return null;
       }
       Karta karta = spil.removeFirst();
        karteIgraca.add(karta);
        return karta;
    }

    public void odigrajKartu(Karta karta){
        karteIgraca.remove(karta);
        odlagaliste.add(karta);
    }

    private void promijesajOdlagalisteUSpil(){
        spil.addAll(odlagaliste);
        odlagaliste.clear();
        Collections.shuffle(spil);
    }

    public void dodajKartuSpilu(Karta karta){
        spil.add(karta);
    }

    public List<Karta> getSpil() {
        return spil;
    }
    public List<Karta> getKarteIgraca() {
        return karteIgraca;
    }
    public List<Karta> getOdlagaliste() {
        return odlagaliste;
    }
    public int getBrojKartiIgraca () {
        return karteIgraca.size();
    }
    public int getVelicinaSpila () {
        return spil.size();
    }

}
