package org.paintFX.core;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class SColor implements Serializable
{
    private double red;
    private double green;
    private double blue;
    private double alpha;

    public SColor(Color color)
    {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.alpha = color.getOpacity();
    }

    public SColor(double red, double green, double blue, double alpha)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public Color getFXColor()
    {
        return new Color(red, green, blue, alpha);
    }
}
