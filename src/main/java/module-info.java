module hr.tvz.game.revive {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens hr.tvz.game.revive to javafx.fxml;
    exports hr.tvz.game.revive;
}