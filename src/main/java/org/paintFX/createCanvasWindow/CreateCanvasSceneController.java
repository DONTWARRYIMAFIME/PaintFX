package org.paintFX.createCanvasWindow;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class CreateCanvasSceneController {

    private final Pattern pattern = Pattern.compile("\\d{1,4}");
    private Pane canvasPane;
    private Canvas canvas;

    @FXML
    private TextField canvasWidth;

    @FXML
    private TextField canvasHeight;

    @FXML
    private Button btnCreateCanvas;

    public void initialize() {

//        TextFormatter<?> formatter1;
//        formatter1 = new TextFormatter<>(change -> {
//            if (pattern.matcher(change.getControlNewText()).matches()) {
//                return change;
//            } else {
//                return null;
//            }
//        });
//
//        canvasWidth.setTextFormatter(formatter1);
//        canvasHeight.setTextFormatter(formatter1);

    }

    public void setCanvasPane(Pane canvasPane) {
        this.canvasPane = canvasPane;
    }
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void onCreateCanvas() {
        double width = Double.parseDouble(canvasWidth.getText());
        double height = Double.parseDouble(canvasHeight.getText());

        resizePane(width, height);
        resizeCanvas(width, height);
        closeStage();
    }

    private void resizePane(double width, double height) {
        canvasPane.setMaxWidth(width);
        canvasPane.setMaxHeight(height);
    }

    private void resizeCanvas(double width, double height) {
        canvas.setWidth(width);
        canvas.setHeight(height);
    }

    private void closeStage() {
        Stage stage = (Stage) btnCreateCanvas.getScene().getWindow();
        stage.close();
    }

}
