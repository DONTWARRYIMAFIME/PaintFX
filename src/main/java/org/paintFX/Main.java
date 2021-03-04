package org.paintFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * JavaFX App
 */
public class Main extends Application {

    private static Scene scene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = loadFXML("Main");
        scene = new Scene(root);

        String pathToIcon = "src/resources/org/paintFX/icon.png";

        try {
            Image icon = new Image(pathToIcon);
            stage.getIcons().add(icon);
        } catch (IllegalArgumentException e) {
            System.out.println("Something goes wrong with loading your icon");
        }

        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}