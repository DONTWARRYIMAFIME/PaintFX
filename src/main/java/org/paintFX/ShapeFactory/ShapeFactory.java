package org.paintFX.ShapeFactory;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.paintFX.Shapes.Shape;

@FunctionalInterface
public interface ShapeFactory {
    Shape createShape(Paint borderColor, Paint fillColor, double lineWidth);
}
