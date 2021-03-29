package org.paintFX;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;

import java.io.IOException;

public class Loader {

    public static Image loadImage(String path) {
        Image image = null;
        try {
            image = new Image(Loader.class.getResource(path).toURI().toString());
        } catch (Exception e) {
            System.out.format("Cannot convert your path(%s) to URI\n", path);
        }

        return image;
    }

    public static FXMLLoader loadFXML(String fxml) throws IOException {
        return new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
    }
}
