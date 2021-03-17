package org.paintFX.ShapeFactory;

import org.paintFX.PaintMode;
import org.paintFX.SColor;
import org.paintFX.Shapes.Polygon;
import org.paintFX.Shapes.Shape;

public class PolygonFactory implements ShapeFactory {
    @Override
    public Shape createShape(double[] points, double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Polygon(points, borderSize, fillColor, borderColor, paintMode);
    }
}
