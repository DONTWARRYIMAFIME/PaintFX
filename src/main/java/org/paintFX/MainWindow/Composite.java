package org.paintFX.MainWindow;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.paintFX.Shapes.Shape;

import java.util.ArrayDeque;
import java.util.Deque;

public class Composite implements Shape {
    private final Deque<Shape> components = new ArrayDeque<>();
    private final Deque<Shape> memory = new ArrayDeque<>();

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

        double currentBorderSize = g.getLineWidth();
        Paint currentFillColor = g.getFill();
        Paint currentBorderColor = g.getStroke();

        for (Shape component : components) {
            component.draw(g);
        }

        g.setLineWidth(currentBorderSize);
        g.setFill(currentFillColor);
        g.setStroke(currentBorderColor);

    }

    public void drawLast(GraphicsContext g) {
            assert components.peekLast() != null;
            components.peekLast().draw(g);
    }


}
