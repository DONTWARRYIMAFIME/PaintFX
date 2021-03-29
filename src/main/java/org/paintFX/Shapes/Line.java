package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.MainWindow.PaintMode;
import org.paintFX.MainWindow.SColor;

public class Line implements Shape {

    private final double borderSize;
    private final SColor borderColor;
    private final PaintMode paintMode;

    private final double[] points;

    public Line(double[] points, double borderSize, SColor borderColor, PaintMode paintMode) {
        this.borderColor = borderColor;
        this.paintMode = paintMode;
        this.borderSize = borderSize;

        this.points = points;
    }

    @Override
    public void draw(GraphicsContext g) {

        g.setLineWidth(borderSize);
        g.setStroke(borderColor.getFXColor());

        switch (paintMode) {
            case FILLED:
            case BORDERED:
            case FILLED_WITH_BORDER:
                g.strokeLine(points[0], points[1], points[2], points[3]);
                break;
            default:
                System.out.println("Unknown paint mode");
                break;
        }

    }
}
