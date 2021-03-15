package org.paintFX;

import javafx.scene.canvas.GraphicsContext;
import org.paintFX.Shapes.Shape;

import java.util.ArrayDeque;
import java.util.Deque;

public class Composite implements Shape {
    private Deque<Shape> components = new ArrayDeque<>();
    private Deque<Shape> memory = new ArrayDeque<>();

    public void addComponent(Shape component) {
        components.offerLast(component);
    }

    public Shape removeComponent() {
        return components.pollLast();
    }

    public void undoComponent() {
        try {
            memory.offerLast(components.pollLast());
        } catch (NullPointerException e) {
            System.out.println("Nothing to undo");
        }
    }

    public void redoComponent() {
        try {
            components.offerLast(memory.pollLast());
        } catch (NullPointerException e) {
            System.out.println("Nothing to redo");
        }
    }

    public void clearMemory() {
        memory.clear();
    }

    public void removeAllComponents() {
        components.clear();
    }

    @Override
    public void draw(GraphicsContext g) {
        for (Shape component : components) {
            component.draw(g);
        }
    }

    public void drawLast(GraphicsContext g) {
        components.peekLast().draw(g);
    }


}
