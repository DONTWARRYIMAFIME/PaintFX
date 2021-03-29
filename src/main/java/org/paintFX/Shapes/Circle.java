package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.MainWindow.PaintMode;
import org.paintFX.MainWindow.SColor;

public class Circle implements Shape {

    private final double borderSize;
    private final SColor fillColor;
    private final SColor borderColor;
    private final PaintMode paintMode;

    private final double centerX;
    private final double centerY;
    private final double diameter;

    public Circle(double[] points, double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.paintMode = paintMode;
        this.borderSize = borderSize;

        diameter = Math.sqrt(Math.pow(points[2] - points[0], 2) + Math.pow(points[3] - points[1], 2));
        centerX = points[0] - diameter / 2;
        centerY = points[1] - diameter / 2;
    }

    @Override
    public void draw(GraphicsContext g) {

        g.setLineWidth(borderSize);
        g.setStroke(borderColor.getFXColor());
        g.setFill(fillColor.getFXColor());

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
