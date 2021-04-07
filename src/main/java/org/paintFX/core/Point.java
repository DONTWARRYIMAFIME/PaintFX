package org.paintFX.core;

import java.io.Serializable;

public class Point implements Serializable {

    private double x;
    private double y;

    public Point() {
        this.x = 0.0f;
        this.y = 0.0f;
    }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
