package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.paintFX.Shapes.Shape;

public class Circle extends Shape {

    public Circle(Paint borderColor, Paint fillColor, double lineWidth) {
        super(borderColor, fillColor, lineWidth);
    }

    @Override
    public void draw(double[] points, GraphicsContext g) {
        g.setStroke(borderColor);
        g.setFill(fillColor);
        g.setLineWidth(lineWidth);

        g.fillOval(points[0], points[1], points[2], points[3]);
    }
}
