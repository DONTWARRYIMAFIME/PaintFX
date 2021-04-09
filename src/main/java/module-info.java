import org.paintFX.core.IService;

module core {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    opens org.paintFX.createCanvasWindow to javafx.fxml;
    opens org.paintFX.mainWindow to javafx.fxml;
    exports org.paintFX;
    exports org.paintFX.core;

    uses IService;
}