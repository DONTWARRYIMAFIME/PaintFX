package org.paintFX.ShapeFactory;

import org.paintFX.core.*;
import org.paintFX.Shapes.Rectangle;

public class RectangleFactory implements ShapeFactory {
    @Override
    public Shape createShape(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Rectangle(borderSize, fillColor, borderColor, paintMode);
    }
}
