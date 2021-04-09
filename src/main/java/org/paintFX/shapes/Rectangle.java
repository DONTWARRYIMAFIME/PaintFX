package org.paintFX.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.core.*;

import java.util.List;

public class Rectangle extends Shape {

    private final Point leftCorner = new Point();
    private double width;
    private double height;

    public Rectangle(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        super(borderSize, fillColor, borderColor, paintMode);
        requiredPointsCount = 2;
    }

    @Override
    public void draw(GraphicsContext g) {

        g.setLineWidth(borderSize);
        g.setStroke(borderColor.getFXColor());
        g.setFill(fillColor.getFXColor());

        switch (paintMode) {
            case CLEAR -> g.clearRect(leftCorner.getX(), leftCorner.getY(), width, height);
            case FILLED -> g.fillRect(leftCorner.getX(), leftCorner.getY(), width, height);
            case BORDERED -> g.strokeRect(leftCorner.getX(), leftCorner.getY(), width, height);
            case FILLED_WITH_BORDER -> {
                g.fillRect(leftCorner.getX(), leftCorner.getY(), width, height);
                g.strokeRect(leftCorner.getX(), leftCorner.getY(), width, height);
            }
            default -> System.out.println("Unknown paint mode");
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
