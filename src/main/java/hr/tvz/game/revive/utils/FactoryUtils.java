package hr.tvz.game.revive.utils;

import hr.tvz.game.revive.*;
import hr.tvz.game.revive.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FactoryUtils {

    private static final Random RANDOM = new Random();

    private FactoryUtils() {
    }

    public static List<Radnik> kreirajRadnike(){

        List<Radnik> radnici = new ArrayList<>();

        radnici.add(new Builder());
        radnici.add(new Explorer());
        radnici.add(new Scholar());
        radnici.add(new Scientist());

        return radnici;
    }

    public static List<Karta> kreirajPocetniSpil(){

        List<Karta> spil = new ArrayList<>();

        spil.add(new Karta("Istraživanje sjevera", Action.ISTRAZIVANJE, Bonus.BODOVI, KulturniEfekt.NIJEDAN));
        spil.add(new Karta("Gradnja temelja", Action.GRADNJA, Bonus.MASINA, KulturniEfekt.NIJEDAN));
        spil.add(new Karta("Znanstvena ekspedicija", Action.ZNANOST, Bonus.KARTA, KulturniEfekt.DOBIVAS_BOD));
        spil.add(new Karta("Širenje teritorija", Action.EKSPANZIJA, Bonus.EKSPANZIJA, KulturniEfekt.NIJEDAN));
        spil.add(new Karta("Stara mašina", Action.GRADNJA, Bonus.BODOVI, KulturniEfekt.NIJEDAN));
        spil.add(new Karta("Lov na artefakte", Action.ISTRAZIVANJE, Bonus.KARTA, KulturniEfekt.OTKRIVAS_POLJE));
        spil.add(new Karta("Tehnološki napredak", Action.ZNANOST, Bonus.MASINA, KulturniEfekt.DODATNI_POTEZ));
        spil.add(new Karta("Naseljavanje doline", Action.EKSPANZIJA, Bonus.BODOVI, KulturniEfekt.NIJEDAN));

        return spil;
    }

    public static PermaFrostPloca kreirajPermaFrostPlocu(){
        PermaFrostPolje [][] matrica = new PermaFrostPolje[4][4];  //ovo ce biti za live coding da ispravim nasumicni odabir:
         //TipBonusa[] sviBonusi = TipBonusa.values();
        for (int i = 0; i<4; i++){
            for (int j = 0; j < 4; j++){
                //TipBonusa nasumicniBonus = sviBonusi[random.nextInt(sviBonusi.length)];
                // ili int vrijednost = random.nextInt(5) + 1;
                matrica[i][j] = new PermaFrostPolje(i,j, Bonus.values()[RANDOM.nextInt(Bonus.values().length)],RANDOM.nextInt(5)+1);
            }
        }
        return new PermaFrostPloca(matrica);
    }

    public static KartaSvijeta kreirajKartuSvijeta(){
        return new KartaSvijeta();
    }


}
