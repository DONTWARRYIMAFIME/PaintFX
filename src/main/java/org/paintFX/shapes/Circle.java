package org.paintFX.shapes;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.core.*;

import java.util.List;

public class Circle extends Shape {

    private final Point center = new Point();
    private double diameter;

    public Circle(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        super(borderSize, fillColor, borderColor, paintMode);
        requiredPointsCount = 2;
    }

    @Override
    public void draw(GraphicsContext g) {

        g.setLineWidth(borderSize);
        g.setStroke(borderColor.getFXColor());
        g.setFill(fillColor.getFXColor());

        switch (paintMode) {
            case FILLED -> g.fillOval(center.getX(), center.getY(), diameter, diameter);
            case BORDERED -> g.strokeOval(center.getX(), center.getY(), diameter, diameter);
            case FILLED_WITH_BORDER -> {
                g.fillOval(center.getX(), center.getY(), diameter, diameter);
                g.strokeOval(center.getX(), center.getY(), diameter, diameter);
            }
            default -> System.out.println("Unknown paint mode");
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
