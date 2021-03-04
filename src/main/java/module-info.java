module org.paintFX {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.paintFX to javafx.fxml;
    exports org.paintFX;
}