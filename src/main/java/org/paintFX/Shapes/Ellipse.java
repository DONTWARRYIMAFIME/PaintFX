package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.MainWindow.PaintMode;
import org.paintFX.MainWindow.SColor;

public class Ellipse implements Shape {

    private final double borderSize;
    private final SColor fillColor;
    private final SColor borderColor;
    private final PaintMode paintMode;

    private final double leftCornerX;
    private final double leftCornerY;
    private final double width;
    private final double height;

    public Ellipse(double[] points, double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.paintMode = paintMode;
        this.borderSize = borderSize;

        leftCornerX = Math.min(points[0], points[2]);
        leftCornerY = Math.min(points[1], points[3]);

        width = Math.abs(points[0] - points[2]);
        height = Math.abs(points[1] - points[3]);
    }

    @Override
    public void draw(GraphicsContext g) {

        g.setLineWidth(borderSize);
        g.setStroke(borderColor.getFXColor());
        g.setFill(fillColor.getFXColor());

        switch (paintMode) {
            case FILLED:
                g.fillOval(leftCornerX, leftCornerY, width, height);
                break;
            case BORDERED:
                g.strokeOval(leftCornerX, leftCornerY, width, height);
                break;
            case FILLED_WITH_BORDER:
                g.fillOval(leftCornerX, leftCornerY, width, height);
                g.strokeOval(leftCornerX, leftCornerY, width, height);
                break;
            default:
                System.out.println("Unknown paint mode");
                break;
        }

    }

}
