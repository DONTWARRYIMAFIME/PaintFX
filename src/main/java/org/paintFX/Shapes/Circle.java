package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.core.PaintMode;
import org.paintFX.core.Point;
import org.paintFX.core.SColor;
import org.paintFX.core.Shape;

import java.util.List;

public class Circle implements Shape {

    private final int requiredPointsCount = 2;
    private final Point center = new Point();
    private double diameter;

    private final double borderSize;
    private final SColor fillColor;
    private final SColor borderColor;
    private final PaintMode paintMode;

    public Circle(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        this.fillColor = fillColor;
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
        g.setFill(fillColor.getFXColor());

        switch (paintMode) {
            case FILLED:
                g.fillOval(center.getX(), center.getY(), diameter, diameter);
                break;
            case BORDERED:
                g.strokeOval(center.getX(), center.getY(), diameter, diameter);
                break;
            case FILLED_WITH_BORDER:
                g.fillOval(center.getX(), center.getY(), diameter, diameter);
                g.strokeOval(center.getX(), center.getY(), diameter, diameter);
                break;
            default:
                System.out.println("Unknown paint mode");
                break;
        }

    }

    @Override
    public void setPoints(List<Point> points) {
        diameter = Math.sqrt(Math.pow(
                points.get(1).getX() - points.get(0).getX(), 2)
                + Math.pow(points.get(1).getY() - points.get(0).getY(), 2)
        );

        center.setX(points.get(0).getX() - diameter / 2);
        center.setY(points.get(0).getY() - diameter / 2);
    }

}
