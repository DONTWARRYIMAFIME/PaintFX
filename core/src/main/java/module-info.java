import org.paintFX.core.IService;

module core {
    requires javafx.controls;

    exports org.paintFX.core;

    uses IService;
}