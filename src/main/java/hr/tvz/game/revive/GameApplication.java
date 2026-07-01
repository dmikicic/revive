package hr.tvz.game.revive;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("gameBoard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 750);
        stage.setTitle("Revive");
        stage.setScene(scene);
        stage.show();
    }
}
