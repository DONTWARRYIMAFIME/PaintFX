package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.core.PaintMode;
import org.paintFX.core.Point;
import org.paintFX.core.SColor;
import org.paintFX.core.Shape;

import java.util.List;

public class Line implements Shape {

    private final int requiredPointsCount = 0;
    private double[] pointsX;
    private double[] pointsY;

    private final double borderSize;
    private final SColor borderColor;
    private final PaintMode paintMode;

    public Line(double borderSize, SColor borderColor, PaintMode paintMode) {
        this.borderColor = borderColor;
        this.paintMode = paintMode;
        this.borderSize = borderSize;
    }

    @Override
    public boolean isContinue(int placedPointsCount) {
        return requiredPointsCount == 0 ? true : placedPointsCount < requiredPointsCount;
    }

    @Override
    public boolean isInfinite() {
        return requiredPointsCount == 0;
    }

    @Override
    public void draw(GraphicsContext g) {

        g.setLineWidth(borderSize);
        g.setStroke(borderColor.getFXColor());

        switch (paintMode) {
            case FILLED:
            case BORDERED:
            case FILLED_WITH_BORDER:
                g.strokePolyline(pointsX, pointsY, pointsX.length);
                break;
            default:
                System.out.println("Unknown paint mode");
                break;
        }

    }

    @Override
    public void setPoints(List<Point> points) {
        pointsX = new double[points.size()];
        pointsY = new double[points.size()];

        for (int i = 0; i < points.size(); i++) {
            pointsX[i] = points.get(i).getX();
            pointsY[i] = points.get(i).getY();
        }
    }

}
