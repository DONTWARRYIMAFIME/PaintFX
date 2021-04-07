package org.paintFX.core;

@FunctionalInterface
public interface ShapeFactory {
    Shape createShape(double borderSize, SColor fillColor, SColor borderColor, PaintMode paintMode);
}
