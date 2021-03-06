package org.paintFX.shapeFactory;

import org.paintFX.core.*;
import org.paintFX.shapes.Rectangle;

public class RectangleFactory implements ShapeFactory {
    @Override
    public Shape createShape(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Rectangle(borderSize, fillColor, borderColor, paintMode);
    }
}
