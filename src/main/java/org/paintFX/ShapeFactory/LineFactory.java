package org.paintFX.ShapeFactory;

import javafx.scene.paint.Paint;
import org.paintFX.PaintMode;
import org.paintFX.Shapes.Line;
import org.paintFX.Shapes.Shape;

public class LineFactory implements ShapeFactory {
    @Override
    public Shape createShape(double[] points, double borderSize, Paint fillColor, Paint borderColor, PaintMode paintMode) {
        return new Line(points, borderSize, borderColor, paintMode);
    }
}
