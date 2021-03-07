package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Rectangle extends Shape {

    public Rectangle(Paint borderColor, Paint fillColor, double lineWidth) {
        super(borderColor, fillColor, lineWidth);
    }

    @Override
    public void draw(double[] points, GraphicsContext g) {
        g.setStroke(borderColor);
        g.setFill(fillColor);
        g.setLineWidth(lineWidth);

        g.fillRect(points[0], points[1], points[2], points[3]);
    }
}
