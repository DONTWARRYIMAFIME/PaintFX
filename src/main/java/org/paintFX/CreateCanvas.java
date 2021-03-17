package org.paintFX;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class CreateCanvas {

    public CreateCanvas(Canvas canvas) throws Exception {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scenes/CreateCanvasScene.fxml"));
        Scene scene = new Scene(loader.load());
        CreateCanvasSceneController controller = loader.getController();
        controller.setCanvas(canvas);

        stage.setTitle("Create canvas");
        stage.setScene(scene);
        stage.show();

        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

}
