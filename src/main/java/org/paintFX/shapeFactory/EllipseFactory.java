package org.paintFX.shapeFactory;

import org.paintFX.core.*;
import org.paintFX.shapes.Ellipse;

public class EllipseFactory implements ShapeFactory {
    @Override
    public Shape createShape(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Ellipse(borderSize, fillColor, borderColor, paintMode);
    }
}
