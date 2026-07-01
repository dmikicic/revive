package hr.tvz.game.revive;

import hr.tvz.game.revive.model.PermaFrostPloca;
import hr.tvz.game.revive.model.PermaFrostPolje;
import hr.tvz.game.revive.model.Radnik;
import hr.tvz.game.revive.model.TipRadnika;

public class Scholar extends Radnik {

    public static final String SPOSOBNOST_SCHOLAR = "Daje +1 uz vrijednost polja, otkriva znanje.";
    public static final String UVJET_SCHOLAR = "Polje mora biti zaleđeno";

    public Scholar(){
        super(TipRadnika.SCHOLAR);
    }

    @Override
    public int aktivirajRadnika(PermaFrostPloca ploca, PermaFrostPolje polje){
        return polje.getVrijednost() + ploca.brojOtopljenihPolja();
    }

    @Override
    public String getSposobnostRadnika(){
        return SPOSOBNOST_SCHOLAR;
    }
    @Override
    public String getUvjetRadnika(){
        return UVJET_SCHOLAR;
    }

}
