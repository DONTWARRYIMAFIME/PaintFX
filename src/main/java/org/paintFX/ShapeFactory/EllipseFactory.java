package org.paintFX.ShapeFactory;

import org.paintFX.PaintMode;
import org.paintFX.SColor;
import org.paintFX.Shapes.Ellipse;
import org.paintFX.Shapes.Shape;

public class EllipseFactory implements ShapeFactory {
    @Override
    public Shape createShape(double[] points, double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Ellipse(points, borderSize, fillColor, borderColor, paintMode);
    }
}
