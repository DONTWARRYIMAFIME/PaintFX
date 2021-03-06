package org.paintFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Scene scene;
    private final String title = "Version - 0.02";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle(title);
        scene = new Scene(loadFXML("Main"));

        String pathToIcon = "icon.png";
        try {
            stage.getIcons().add(loadImage(pathToIcon));
        } catch (NullPointerException e) {
            System.out.print("Bad path to icon");
        }

        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static Image loadImage(String img) {
        Image image = null;
        try {
            image = new Image(Main.class.getResource(img).toURI().toString());
        } catch (Exception e) {
            System.out.print("Cannot convert your path to URI");
        }

        return image;
    }
}