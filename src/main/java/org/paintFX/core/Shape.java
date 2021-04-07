package org.paintFX.core;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.List;

public interface Shape extends Serializable {

    boolean isContinue(int placedPointsCount);

    boolean isInfinite();

    void draw(GraphicsContext g);

    void setPoints(List<Point> points);

}
