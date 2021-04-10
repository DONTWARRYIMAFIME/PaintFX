package org.paintFX.mainWindow;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.paintFX.core.Drawable;

import java.util.ArrayDeque;
import java.util.Deque;

public class Composite implements Drawable {
    private final Deque<Drawable> components = new ArrayDeque<>();
    private final Deque<Drawable> history = new ArrayDeque<>();

    public void addComponent(Drawable component) {
        components.offerLast(component);
    }

    public void addComponentToHistory(Drawable component) {
        history.offerLast(component);
    }

    public Deque<Drawable> getComponents() {
        return components;
    }

    public Deque<Drawable> getHistory() {
        return history;
    }

    public void clear() {
        components.clear();
    }

    public void undoComponent() {
        try {
            history.offerLast(components.pollLast());
        } catch (NullPointerException e) {
            System.out.println("Nothing to undo");
        }
    }

    public void redoComponent() {
        try {
            components.offerLast(history.pollLast());
        } catch (NullPointerException e) {
            System.out.println("Nothing to redo");
        }
    }

    public void clearHistory() {
        history.clear();
    }

    public void removeAllComponents() {
        components.clear();
    }

    @Override
    public void draw(GraphicsContext g) {

        double currentBorderSize = g.getLineWidth();
        Paint currentFillColor = g.getFill();
        Paint currentBorderColor = g.getStroke();

        for (Drawable component : components) {
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
