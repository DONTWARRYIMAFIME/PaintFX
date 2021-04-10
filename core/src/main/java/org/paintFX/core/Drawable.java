package org.paintFX.core;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

@FunctionalInterface
public interface Drawable extends Serializable {
    void draw(GraphicsContext g);
}
