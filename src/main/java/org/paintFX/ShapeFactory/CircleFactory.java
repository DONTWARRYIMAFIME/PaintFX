package org.paintFX.ShapeFactory;

import org.paintFX.MainWindow.PaintMode;
import org.paintFX.MainWindow.SColor;
import org.paintFX.Shapes.Circle;
import org.paintFX.Shapes.Shape;

public class CircleFactory implements ShapeFactory{
    @Override
    public Shape createShape(double[] points, double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Circle(points, borderSize, fillColor, borderColor, paintMode);
    }
}
