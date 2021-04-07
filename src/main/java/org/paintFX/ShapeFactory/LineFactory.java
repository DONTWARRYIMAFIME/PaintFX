package org.paintFX.ShapeFactory;

import org.paintFX.core.*;
import org.paintFX.Shapes.Line;

public class LineFactory implements ShapeFactory {
    @Override
    public Shape createShape(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Line(borderSize, borderColor, paintMode);
    }
}
