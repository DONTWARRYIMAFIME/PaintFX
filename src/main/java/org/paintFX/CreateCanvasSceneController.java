package org.paintFX;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class CreateCanvasSceneController {

    private final Pattern pattern = Pattern.compile("\\d{1,4}");
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

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void onCreateCanvas() {
        canvas.setWidth(Double.parseDouble(canvasWidth.getText()));
        canvas.setHeight(Double.parseDouble(canvasHeight.getText()));
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) btnCreateCanvas.getScene().getWindow();
        stage.close();
    }

}
