package org.paintFX.ShapeFactory;

import org.paintFX.MainWindow.PaintMode;
import org.paintFX.MainWindow.SColor;
import org.paintFX.Shapes.Rectangle;
import org.paintFX.Shapes.Shape;

public class RectangleFactory implements ShapeFactory {
    @Override
    public Shape createShape(double[] points, double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Rectangle(points, borderSize, fillColor, borderColor, paintMode);
    }
}
