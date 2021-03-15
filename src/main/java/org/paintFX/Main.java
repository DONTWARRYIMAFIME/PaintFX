package org.paintFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private final String title = "Version - 0.06";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(Loader.loadFXML("Scenes/MainScene"));

        stage.setTitle(title);
        stage.getIcons().add(Loader.loadImage("icon.png"));
        stage.setScene(scene);
        stage.show();
    }

}