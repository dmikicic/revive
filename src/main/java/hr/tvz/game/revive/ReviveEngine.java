package hr.tvz.game.revive;

import hr.tvz.game.revive.model.*;
import hr.tvz.game.revive.utils.FactoryUtils;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReviveEngine implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

   private PermaFrostPloca ploca;
   private final KartaSvijeta kartaSvijeta;
   private final List<Igrac> igraci;
   private int trenutnaRunda;
   private int indeksTrenutnogIgraca;
   private FazaIgre fazaIgre;

   private static final int BROJ_RUNDI = 5;
   private static final int BROJ_IGRACA = 2;

   public ReviveEngine() {
       this.ploca = FactoryUtils.kreirajPermaFrostPlocu();
       this.kartaSvijeta = FactoryUtils.kreirajKartuSvijeta();

       this.igraci = new ArrayList<>();
       PlayerBoard playerBoardOne = new PlayerBoard(FactoryUtils.kreirajPocetniSpil());
       PlayerBoard playerBoardTwo = new PlayerBoard(FactoryUtils.kreirajPocetniSpil());
       igraci.add(new Igrac("igrac1", playerBoardOne, FactoryUtils.kreirajRadnike()));
       igraci.add(new Igrac("igrac2", playerBoardTwo, FactoryUtils.kreirajRadnike()));

       this.trenutnaRunda = 1;
       this.indeksTrenutnogIgraca = 0;
       this.fazaIgre = FazaIgre.ODABIR;

       for (Igrac igrac : igraci) {    //svaki igrac vuce 4 karte
           for (int i = 0; i < 4; i++) {
               igrac.getPlayerBoard().vuciKartu();
           }
       }
   }
    public PermaFrostPloca getPloca() {return ploca;}

    public KartaSvijeta getKartaSvijeta() {return kartaSvijeta;}

    public List<Igrac> getIgraci() {return igraci;}

    public int getTrenutnaRunda() {return trenutnaRunda;}

    public Igrac getTrenutniIgrac() {return igraci.get(indeksTrenutnogIgraca);}

    public FazaIgre getFazaIgre() {return fazaIgre;}

   public void odigrajPotez (Igrac igrac, Karta karta, Radnik radnik, int red, int stupac){
       if (!ploca.mozePostaviti(red, stupac)) {
           throw new IllegalStateException("Polje je otopljeno");
       }
       if (radnik.isIskoristen()){
           throw new IllegalStateException("Radnik je iskoristen");
       }
       if (!igrac.getPlayerBoard().getKarteIgraca().contains(karta)) {
           throw  new IllegalStateException("Igrac nema karte");
       }
       PermaFrostPolje polje = ploca.getPolje(red, stupac);
       int bodoviRadnika = radnik.aktivirajRadnika(ploca, polje);
       polje.otopi();

       Bonus bonus = polje.getBonus();
       switch (bonus) {
           case BODOVI -> igrac.dodajBodove(polje.getVrijednost());
           case MASINA -> igrac.dodajBodove(5);
           case KARTA -> igrac.getPlayerBoard().vuciKartu();
           case EKSPANZIJA -> kartaSvijeta.dodajEkspanziju(igrac.getIme());
       }

       igrac.dodajBodove(bodoviRadnika);
       radnik.oznaciIskoristenim();
       igrac.getPlayerBoard().odigrajKartu(karta);

       if (radniciIskoristeni()) {
           zavrsiRundu(); //live coding: dodati indeksTrenutnogIgraca = 0, da svaka nova runda zapocne s igracem 0
       }
       sljedeciIgrac();
   }

   private boolean radniciIskoristeni(){
       for (Igrac igrac : igraci) {
           if (igrac.neiskoristenRadnik()) {
               return false;
           }
       }
       return true;
   }

   private void zavrsiRundu(){
       for (Igrac igrac : igraci) {
           for(Radnik radnik : igrac.getRadnici()){
               radnik.resetiraj();
           }
       }
       for (Igrac igrac : igraci) {
           for(int i = 0; i < 4; i++){
               igrac.getPlayerBoard().vuciKartu(); //live coding: dodati try ako je spil prazan
           }
       }
       this.ploca = FactoryUtils.kreirajPermaFrostPlocu();
       trenutnaRunda++;

       if (trenutnaRunda > BROJ_RUNDI){
            fazaIgre = FazaIgre.KRAJ_IGRE;
       } else {
           fazaIgre = FazaIgre.ODABIR;
       }
   }
   private void sljedeciIgrac(){
      indeksTrenutnogIgraca = (indeksTrenutnogIgraca + 1) % BROJ_IGRACA;
   }

   public boolean isKrajIgre(){
       return trenutnaRunda > BROJ_RUNDI;
   }

   public Igrac getPobjednika() {
       if (igraci.get(0).getBodovi() > igraci.get(1).getBodovi()) {
           return igraci.get(0);

       } else if (igraci.get(0).getBodovi() < igraci.get(1).getBodovi()) {
           return igraci.get(1);
       } else {
           return null;
       }
   }

}
