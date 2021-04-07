package org.paintFX.ShapeFactory;

import org.paintFX.core.*;
import org.paintFX.Shapes.Circle;

public class CircleFactory implements ShapeFactory {
    @Override
    public Shape createShape(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Circle(borderSize, fillColor, borderColor, paintMode);
    }
}
