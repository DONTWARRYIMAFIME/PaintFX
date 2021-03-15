package org.paintFX.ShapeFactory;

import javafx.scene.paint.Paint;
import org.paintFX.PaintMode;
import org.paintFX.Shapes.Polygon;
import org.paintFX.Shapes.Shape;

public class PolygonFactory implements ShapeFactory {
    @Override
    public Shape createShape(double[] points, double borderSize, Paint fillColor, Paint borderColor, PaintMode paintMode) {
        return new Polygon(points, borderSize, fillColor, borderColor, paintMode);
    }
}
