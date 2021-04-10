package org.paintFX.core;

import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public abstract class Shape implements Drawable {

    public int requiredPointsCount;

    protected double borderSize;
    protected SColor fillColor;
    protected SColor borderColor;
    protected PaintMode paintMode;

    public Shape(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.paintMode = paintMode;
        this.borderSize = borderSize;
    }

    public boolean isContinue(int placedPointsCount) {
        return requiredPointsCount == 0 ? true : placedPointsCount < requiredPointsCount;
    }

    public boolean isInfinite() {
        return requiredPointsCount == 0;
    }

    public void setPaintSettings(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode) {
        this.borderSize = borderSize;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.paintMode = paintMode;
    }

    public abstract void setPoints(List<Point> points);

    @Override
    public abstract void draw(GraphicsContext g);
}
