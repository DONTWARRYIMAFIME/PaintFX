package org.paintFX.ShapeFactory;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.paintFX.Shapes.Rectangle;
import org.paintFX.Shapes.Shape;

public class RectangleFactory implements ShapeFactory{
    @Override
    public Shape createShape(Paint borderColor, Paint fillColor, double lineWidth) {
        return new Rectangle(borderColor, fillColor, lineWidth);
    }
}
