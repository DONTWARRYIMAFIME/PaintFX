package org.paintFX.ShapeFactory;

import org.paintFX.core.*;
import org.paintFX.Shapes.Ellipse;

public class EllipseFactory implements ShapeFactory {
    @Override
    public Shape createShape(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Ellipse(borderSize, fillColor, borderColor, paintMode);
    }
}
