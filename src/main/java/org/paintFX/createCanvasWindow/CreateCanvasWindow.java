package org.paintFX.createCanvasWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.paintFX.Loader;

public class CreateCanvasWindow {

    public CreateCanvasWindow(Pane canvasPane, Canvas canvas) throws Exception {
        Stage stage = new Stage();

        FXMLLoader loader = Loader.loadFXML("Scenes/CreateCanvasScene");
        Scene scene = new Scene(loader.load());
        CreateCanvasSceneController controller = loader.getController();
        controller.setCanvasPane(canvasPane);
        controller.setCanvas(canvas);

        stage.setTitle("Create canvas");
        stage.setScene(scene);
        stage.show();

        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

}
