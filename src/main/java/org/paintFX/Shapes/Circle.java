package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.paintFX.PaintMode;

public class Circle implements Shape {

    private final double borderSize;
    private final Paint fillColor;
    private final Paint borderColor;
    private final PaintMode paintMode;

    private final double centerX;
    private final double centerY;
    private final double diameter;

    public Circle(double[] points, double borderSize, Paint fillColor, Paint borderColor, PaintMode paintMode) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.paintMode = paintMode;
        this.borderSize = borderSize;

        diameter = Math.sqrt(Math.pow(points[2] - points[0], 2) + Math.pow(points[3] - points[1], 2));
        centerX = points[0] - diameter / 2;
        centerY = points[1] - diameter / 2;
    }

    public Circle(double centerX, double centerY, double diameter, double borderSize, Paint fillColor, Paint borderColor, PaintMode paintMode) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.paintMode = paintMode;
        this.borderSize = borderSize;

        this.centerX = centerX;
        this.centerY = centerY;
        this.diameter = diameter;
    }

    @Override
    public void draw(GraphicsContext g) {

        g.setLineWidth(borderSize);
        g.setStroke(borderColor);
        g.setFill(fillColor);

        switch (paintMode) {
            case FILLED:
                g.fillOval(centerX, centerY, diameter, diameter);
                break;
            case BORDERED:
                g.strokeOval(centerX, centerY, diameter, diameter);
                break;
            case FILLED_WITH_BORDER:
                g.fillOval(centerX, centerY, diameter, diameter);
                g.strokeOval(centerX, centerY, diameter, diameter);
                break;
            default:
                System.out.println("Unknown paint mode");
                break;
        }

    }

}
