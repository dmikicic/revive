package hr.tvz.game.revive;
import hr.tvz.game.revive.model.Karta;
import hr.tvz.game.revive.model.PermaFrostPloca;
import hr.tvz.game.revive.model.PermaFrostPolje;
import hr.tvz.game.revive.model.Radnik;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.MenuItem;

public class GameController {

    private static final int VELICINA_PLOCE = 4;

    @FXML
    private GridPane permafrostGrid;
    @FXML
    private HBox kartePrvogIgracaBox;
    @FXML
    private HBox radniciPrvogIgracaBox;
    @FXML
    private HBox karteDrugogIgracaBox;
    @FXML
    private HBox radniciDrugogIgracaBox;
    @FXML
    private VBox igrac1Panel;
    @FXML
    private VBox igrac2Panel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label bodoviLabel;
    @FXML
    private MenuItem mainMenu;

    private ReviveEngine reviveEngine;
    private Karta odabranaKarta;
    private Radnik odabranRadnik;


    @FXML
    public void initialize() {
            onNewGame();
    }

    @FXML
    private void onNewGame(){
        reviveEngine = new ReviveEngine();
        odabranaKarta = null;
        odabranRadnik = null;
        osvjeziUI();
        statusLabel.setText("Nova igra započela. " + reviveEngine.getTrenutniIgrac().getIme() + " na potezu.");
    }

    private void osvjeziUI(){
        napraviPermafrost();
        napraviKarte();
        napraviRadnike();
        prikaziBodove();
        prikaziRundu();
    }

    private void napraviPermafrost(){
        PermaFrostPloca ploca = reviveEngine.getPloca();
        permafrostGrid.getChildren().clear();

        for (int red = 0; red < VELICINA_PLOCE; red++) {
            for (int stupac = 0; stupac < VELICINA_PLOCE; stupac++) {
               PermaFrostPolje polje = ploca.getPolje(red, stupac);
               Button button = new Button();
               button.setPrefWidth(80);
               button.setPrefHeight(60);

               if (polje.isOtopljeno()) {
                   String bonusText = polje.getBonus().toString();
                   button.setText(bonusText + polje.getVrijednost());
               } else {
                   button.setText("?");
               }
                final int r = red;
                final int s = stupac;
                button.setOnAction(e -> klikNaPolje(r, s));
                permafrostGrid.add(button, stupac, red);
            }
        }
    }
    private void napraviKarte(){
        kartePrvogIgracaBox.getChildren().clear();
        karteDrugogIgracaBox.getChildren().clear();
        Igrac igrac1 = reviveEngine.getIgraci().get(0);
        Igrac igrac2 = reviveEngine.getIgraci().get(1);
        for (Karta karta : igrac1.getPlayerBoard().getKarteIgraca()) {
            Button button = new Button(karta.getNaziv());
            button.setPrefWidth(80);
            button.setPrefHeight(60);
            button.setOnAction(e -> klikNaKartu(karta, igrac1));
            kartePrvogIgracaBox.getChildren().add(button);
        }
        for (Karta karta : igrac2.getPlayerBoard().getKarteIgraca()) {
            Button button = new Button(karta.getNaziv());
            button.setPrefWidth(80);
            button.setPrefHeight(60);
            button.setOnAction(e -> klikNaKartu(karta, igrac2));
            karteDrugogIgracaBox.getChildren().add(button);
        }
    }
    private void napraviRadnike(){
        radniciPrvogIgracaBox.getChildren().clear();
        radniciDrugogIgracaBox.getChildren().clear();
        Igrac igrac1 = reviveEngine.getIgraci().get(0);
        Igrac igrac2= reviveEngine.getIgraci().get(1);
        for (Radnik radnik : igrac1.getRadnici()){
            Button button = new Button(radnik.getTipRadnika().toString());
            button.setPrefWidth(80);
            button.setOnAction(e -> klikNaRadnik(radnik, igrac1));
            radniciPrvogIgracaBox.getChildren().add(button);
        }
        for (Radnik radnik : igrac2.getRadnici()){
            Button button = new Button(radnik.getTipRadnika().toString());
            button.setPrefWidth(80);
            button.setOnAction(e -> klikNaRadnik(radnik, igrac2));
            radniciDrugogIgracaBox.getChildren().add(button);
        }
    }
    private void prikaziBodove(){
        Igrac igrac1 = reviveEngine.getIgraci().get(0);
        Igrac igrac2= reviveEngine.getIgraci().get(1);
        bodoviLabel.setText("Bodovi: " + igrac1.getBodovi() + " | " + igrac2.getBodovi());
    }
    private void prikaziRundu() {
        statusLabel.setText("Runda " + reviveEngine.getTrenutnaRunda()
                + " | Na potezu: " + reviveEngine.getTrenutniIgrac().getIme());
    }

    private void klikNaKartu (Karta karta, Igrac igrac){
        if (igrac != reviveEngine.getTrenutniIgrac()) {
            statusLabel.setText("Nije tvoj red! Na potezu: " + reviveEngine.getTrenutniIgrac().getIme());
            return;
        }
        odabranaKarta = karta;
        statusLabel.setText("Odabrana karta: " + karta.getNaziv() + ". Sada odaberi radnika.");
    }
    private void klikNaRadnik (Radnik radnik, Igrac igrac){
        if(igrac != reviveEngine.getTrenutniIgrac()) {
            statusLabel.setText("Nije tvoj red! Na potezu: " + reviveEngine.getTrenutniIgrac().getIme());
            return;
        }
        odabranRadnik = radnik;
        statusLabel.setText("Odabrani radnik: " + radnik.getTipRadnika().toString() + ". Klikni na polje.");
    }
    private void klikNaPolje (int red, int stupac){
        if (odabranaKarta == null || odabranRadnik == null) {
            statusLabel.setText("Odaberi kartu i radnika.");
            return;
        }
        Igrac igrac = reviveEngine.getTrenutniIgrac();
        reviveEngine.odigrajPotez(igrac, odabranaKarta, odabranRadnik, red, stupac);
        odabranaKarta = null;
        odabranRadnik = null;
        osvjeziUI();
    }

}   
