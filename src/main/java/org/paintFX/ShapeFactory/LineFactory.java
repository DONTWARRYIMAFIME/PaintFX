package org.paintFX.ShapeFactory;

import org.paintFX.MainWindow.PaintMode;
import org.paintFX.MainWindow.SColor;
import org.paintFX.Shapes.Line;
import org.paintFX.Shapes.Shape;

public class LineFactory implements ShapeFactory {
    @Override
    public Shape createShape(double[] points, double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        return new Line(points, borderSize, borderColor, paintMode);
    }
}
