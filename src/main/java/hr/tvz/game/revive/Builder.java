package hr.tvz.game.revive;

import hr.tvz.game.revive.model.PermaFrostPloca;
import hr.tvz.game.revive.model.PermaFrostPolje;
import hr.tvz.game.revive.model.Radnik;
import hr.tvz.game.revive.model.TipRadnika;

public class Builder extends Radnik {
    private static final String SPOSOBNOST_BUILDER = "Daje x2 uz vrijednost polja, gradi temeljito.";
    private static final String UVJET_BUILDER = "Polje mora biti zaleđeno.";

    public Builder() {
        super (TipRadnika.BUILDER);
    }

    @Override
    public int aktivirajRadnika (PermaFrostPloca ploca, PermaFrostPolje polje){
        return polje.getVrijednost() * 2;
    }

    @Override
    public String getSposobnostRadnika(){
        return SPOSOBNOST_BUILDER;
    }

    @Override
    public String getUvjetRadnika(){
        return UVJET_BUILDER;
    }

}
