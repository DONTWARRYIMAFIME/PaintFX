package org.paintFX.shapeFactory;

import org.paintFX.core.*;
import org.paintFX.shapes.Line;

public class LineFactory implements ShapeFactory {
    @Override
    public Shape createShape(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Line(borderSize, fillColor, borderColor, paintMode);
    }
}
