package org.paintFX.ShapeFactory;

import org.paintFX.core.*;
import org.paintFX.Shapes.Polygon;

public class PolygonFactory implements ShapeFactory {
    @Override
    public Shape createShape(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Polygon(borderSize, fillColor, borderColor, paintMode);
    }
}
