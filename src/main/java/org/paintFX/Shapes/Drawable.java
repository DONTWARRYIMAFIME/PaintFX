package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;

@FunctionalInterface
public interface Drawable {
    void draw(double[] points, GraphicsContext g);
}
