package org.paintFX;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;

public class PaintModel {

    public void onSave(Canvas canvas) {
        try {
            String path = "out";

            new File(path).mkdir();

            Image snapshot = canvas.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File(path + "/" + "paint.png"));

            System.out.println("File saved successfully");
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e);
        }
    }

    public void onExit() {
        Platform.exit();
    }

}
