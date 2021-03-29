package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.MainWindow.PaintMode;
import org.paintFX.MainWindow.SColor;

public class Polygon implements Shape {

    private final double borderSize;
    private final SColor fillColor;
    private final SColor borderColor;
    private final PaintMode paintMode;

    private final double[] points;

    public Polygon(double[] points, double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.paintMode = paintMode;
        this.borderSize = borderSize;

        this.points = points;
    }

    @Override
    public void draw(GraphicsContext g) {

        g.setLineWidth(borderSize);
        g.setStroke(borderColor.getFXColor());
        g.setFill(fillColor.getFXColor());

        double[] pointsX = new double[points.length / 2];
        double[] pointsY = new double[points.length / 2];

        for (int i = 0; i < points.length; i++) {
            if (i % 2 == 0) {
                pointsX[i / 2] = points[i];
            } else {
                pointsY[i / 2] = points[i];
            }
        }

        switch (paintMode) {
            case FILLED:
                g.fillPolygon(pointsX, pointsY, points.length / 2);
                break;
            case BORDERED:
                g.strokePolygon(pointsX, pointsY, points.length / 2);
                break;
            case FILLED_WITH_BORDER:
                g.fillPolygon(pointsX, pointsY, points.length / 2);
                g.strokePolygon(pointsX, pointsY, points.length / 2);
                break;
            default:
                System.out.println("Unknown paint mode");
                break;
        }

    }
}
