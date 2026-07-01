package hr.tvz.game.revive;

import hr.tvz.game.revive.model.*;

public class Scientist extends Radnik {

    private static final String SPOSOBNOST_SCIENTIST = "Daje +5 ako polje sadrži mašinu, inače +1 bod, otkriva tehnologiju i znanja.";
    private static final String UVJET_SCIENTIST = "Polje mora biti zaleđeno";

    public Scientist(){
        super(TipRadnika.SCIENTIST);
    }

    @Override
    public int aktivirajRadnika(PermaFrostPloca ploca, PermaFrostPolje polje){
        if (polje.getBonus() == Bonus.MASINA) {
            return polje.getVrijednost() +5;
        }
        return polje.getVrijednost();
    }

    @Override
    public String getSposobnostRadnika(){
        return SPOSOBNOST_SCIENTIST;
    }

    @Override
    public String getUvjetRadnika(){
        return UVJET_SCIENTIST;
    }
}
