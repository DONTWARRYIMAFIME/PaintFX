package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.core.PaintMode;
import org.paintFX.core.Point;
import org.paintFX.core.SColor;
import org.paintFX.core.Shape;

import java.util.List;

public class Rectangle implements Shape {

    private final int requiredPointsCount = 2;
    private final Point leftCorner = new Point();
    private double width;
    private double height;

    private final double borderSize;
    private final SColor fillColor;
    private final SColor borderColor;
    private final PaintMode paintMode;

    public Rectangle(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
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
            case CLEAR:
                g.clearRect(leftCorner.getX(), leftCorner.getY(), width, height);
                break;
            case FILLED:
                g.fillRect(leftCorner.getX(), leftCorner.getY(), width, height);
                break;
            case BORDERED:
                g.strokeRect(leftCorner.getX(), leftCorner.getY(), width, height);
                break;
            case FILLED_WITH_BORDER:
                g.fillRect(leftCorner.getX(), leftCorner.getY(), width, height);
                g.strokeRect(leftCorner.getX(), leftCorner.getY(), width, height);
                break;
            default:
                System.out.println("Unknown paint mode");
                break;
        }

    }

    @Override
    public void setPoints(List<Point> points) {
        leftCorner.setX(Math.min(points.get(0).getX(), points.get(1).getX()));
        leftCorner.setY(Math.min(points.get(0).getY(), points.get(1).getY()));

        width = Math.abs(points.get(0).getX() - points.get(1).getX());
        height = Math.abs(points.get(0).getY() - points.get(1).getY());
    }

}
