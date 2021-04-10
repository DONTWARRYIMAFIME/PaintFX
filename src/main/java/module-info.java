module PaintFx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    requires core;

    opens org.paintFX.createCanvasWindow to javafx.fxml;
    opens org.paintFX.mainWindow to javafx.fxml;
    exports org.paintFX;
}