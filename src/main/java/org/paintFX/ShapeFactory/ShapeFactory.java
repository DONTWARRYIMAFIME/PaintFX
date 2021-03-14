package org.paintFX.ShapeFactory;

import javafx.scene.paint.Paint;
import org.paintFX.PaintMode;
import org.paintFX.Shapes.Shape;

@FunctionalInterface
public interface ShapeFactory {
    Shape createShape(double[] points, double borderSize, Paint fillColor, Paint borderColor, PaintMode paintMode);
}
