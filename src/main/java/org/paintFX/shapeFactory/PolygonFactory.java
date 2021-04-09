package org.paintFX.shapeFactory;

import org.paintFX.core.*;
import org.paintFX.shapes.Polygon;

public class PolygonFactory implements ShapeFactory {
    @Override
    public Shape createShape(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Polygon(borderSize, fillColor, borderColor, paintMode);
    }
}
