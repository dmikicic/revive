package hr.tvz.game.revive;

import hr.tvz.game.revive.model.PermaFrostPloca;
import hr.tvz.game.revive.model.PermaFrostPolje;
import hr.tvz.game.revive.model.Radnik;
import hr.tvz.game.revive.model.TipRadnika;

public class Explorer extends Radnik {

    private static final String SPOSOBNOST_EXPLORER = "Daje +1 uz vrijednost polja, istražuje nepoznata područja";
    private static final String UVJET_EXPLORER = "Polje mora biti zaleđeno.";

    public Explorer(){
        super(TipRadnika.EXPLORER);
    }

    @Override
    public int aktivirajRadnika (PermaFrostPloca ploca, PermaFrostPolje polje){
        return polje.getVrijednost() + 1;
    }

    @Override
    public String getSposobnostRadnika(){
        return SPOSOBNOST_EXPLORER;
    }
    @Override
    public String getUvjetRadnika(){
        return UVJET_EXPLORER;
    }

}
