package org.paintFX.ShapeFactory;

import org.paintFX.MainWindow.PaintMode;
import org.paintFX.MainWindow.SColor;
import org.paintFX.Shapes.Shape;

@FunctionalInterface
public interface ShapeFactory {
    Shape createShape(double[] points, double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode);
}
