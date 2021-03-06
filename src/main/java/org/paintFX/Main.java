package org.paintFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        String title = "Version - 1.0";
        Scene scene = new Scene(Loader.loadFXML("scenes/MainScene").load());

        stage.setTitle(title);
        stage.getIcons().add(Loader.loadImage("icon.png"));
        stage.setScene(scene);
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

}