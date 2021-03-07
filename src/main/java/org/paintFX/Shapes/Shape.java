package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class Shape implements Drawable {
    protected Paint borderColor;
    protected Paint fillColor;
    protected double lineWidth;

    public Shape(Paint borderColor, Paint fillColor, double lineWidth) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        this.lineWidth = lineWidth;
    }

}
