package hr.tvz.game.revive.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PermaFrostPloca implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final PermaFrostPolje[][] polja;
    private static final int VELICINA = 4;

    public PermaFrostPloca(PermaFrostPolje[][] polja) {
        this.polja = polja;
    }
    public PermaFrostPolje getPolje(int red, int stupac){
        return polja[red][stupac];
    }
    public PermaFrostPolje[][] getSvaPolja() {
        return polja;
    }

    public boolean mozePostaviti(int red, int stupac){
        if ( red >= VELICINA && stupac >= VELICINA) {  // live coding: treba biti  if (red < 0 || red >= VELICINA || stupac < 0 || stupac >= VELICINA) { return false}
            return false;
        }
        return !polja[red][stupac].isOtopljeno();
    }

    public void postaviNaPolje(int red, int stupac){
        polja[red][stupac].otopi();
    }

    public int brojOtopljenihPolja(){
        int broj = 0;
        for (int i = 0; i<VELICINA; i++) {
            for (int j = 0; j < VELICINA; j++) {
                if (polja[i][j].isOtopljeno()) {
                    broj++;
                }
            }
        }
        return broj;
    }


}
