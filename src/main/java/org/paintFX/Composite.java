package org.paintFX;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.Shapes.Shape;

import java.util.ArrayDeque;
import java.util.Deque;

public class Composite implements Shape {
    private Deque<Shape> stack = new ArrayDeque<>();

    public void pushComponent(Shape component) {
        stack.offerLast(component);
    }

    public Shape popComponent() {
        return stack.pollLast();
    }

    public void removeAllComponents() {
        stack.clear();
    }

    @Override
    public void draw(GraphicsContext g) {
        for (Shape component : stack) {
            component.draw(g);
        }
    }


}
