package org.paintFX.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.core.*;

import java.util.List;

public class Line extends Shape {

    private double[] pointsX;
    private double[] pointsY;

    public Line(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        super(borderSize, fillColor, borderColor, paintMode);
    }

    @Override
    public void draw(GraphicsContext g) {

        g.setLineWidth(borderSize);
        g.setStroke(borderColor.getFXColor());

        switch (paintMode) {
            case FILLED, BORDERED, FILLED_WITH_BORDER -> g.strokePolyline(pointsX, pointsY, pointsX.length);
            default -> System.out.println("Unknown paint mode");
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
