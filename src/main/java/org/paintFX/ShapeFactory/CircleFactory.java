package org.paintFX.ShapeFactory;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.paintFX.Shapes.Circle;
import org.paintFX.Shapes.Shape;

public class CircleFactory implements ShapeFactory{
    @Override
    public Shape createShape(Paint borderColor, Paint fillColor, double lineWidth) {
        return new Circle(borderColor, fillColor, lineWidth);
    }
}
