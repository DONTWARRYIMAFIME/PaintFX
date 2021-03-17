package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

@FunctionalInterface
public interface Shape extends Serializable {
    void draw(GraphicsContext g);
}
