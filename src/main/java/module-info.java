module org.paintFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    opens org.paintFX to javafx.fxml;
    exports org.paintFX;
}