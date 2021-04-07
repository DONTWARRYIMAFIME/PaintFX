import org.paintFX.core.IService;

module core {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    opens org.paintFX.CreateCanvasWindow to javafx.fxml;
    opens org.paintFX.MainWindow to javafx.fxml;
    exports org.paintFX;
    exports org.paintFX.core;

    uses IService;
}