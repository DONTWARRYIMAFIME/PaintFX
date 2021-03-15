package org.paintFX;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.io.IOException;
import org.paintFX.Controllers.*;

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

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
