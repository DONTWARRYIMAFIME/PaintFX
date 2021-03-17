package org.paintFX;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import org.paintFX.ShapeFactory.ShapeFactory;
import org.paintFX.Shapes.Circle;
import org.paintFX.Shapes.Rectangle;
import org.paintFX.Shapes.Shape;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Model {

    private final List<Double> points = new ArrayList<>();
    private Composite components = new Composite();

    private final Canvas canvas;
    private final GraphicsContext g;

    private ShapeFactory shapeFactory;
    private PaintMode paintMode;

    public Model(Canvas canvas) {
        this.canvas = canvas;
        this.g = canvas.getGraphicsContext2D();
    }

    public PaintMode getPaintMode() {
        return paintMode;
    }

    public void addPoint(double x, double y) {
        points.add(x);
        points.add(y);

        System.out.format(
                "Point %d:  X - %.01f |    Y - %.01f\n",
                points.size() / 2,
                x, y
        );
    }

    public void setPaintMode(PaintMode paintMode) { this.paintMode = paintMode; }

    public void setShapeFactory(ShapeFactory shapeFactory) { this.shapeFactory = shapeFactory; }

    public void setFillColor(Paint color) {
        g.setFill(color);
    }

    public void setBorderColor(Paint color) {
        g.setStroke(color);
    }

    public void setBorderSize(double size) {
        g.setLineWidth(size);
    }

    public String showMouseCoordinates(double x, double y) {
        return String.format("X : %.01f       Y : %.01f", x, y);
    }

    public void setPenTool() {
        Composite composite = new Composite();

        canvas.setOnMouseDragged(e -> {

            double size = g.getLineWidth();
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;

            composite.addComponent(new Circle(x, y, size, size, new SColor((Color)g.getFill()), new SColor((Color)g.getFill()), PaintMode.FILLED));
            composite.drawLast(g);
        });

        canvas.setOnMouseReleased(e -> {
            components.addComponent(composite);
            components.clearMemory();
            setPenTool();
        });

    }

    public void setEraserTool() {
        Composite composite = new Composite();

        canvas.setOnMouseDragged(e -> {
            double size = g.getLineWidth();
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;

            composite.addComponent(new Rectangle(x, y, size, size, size, new SColor((Color)g.getFill()), new SColor((Color)g.getStroke()), PaintMode.CLEAR));
            composite.drawLast(g);
        });

        canvas.setOnMouseReleased(e -> {
            components.addComponent(composite);
            components.clearMemory();
            setEraserTool();
        });
    }

    public void clearCanvas() { g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); }

    public void removeComponents() {
        components.removeAllComponents();
        components.clearMemory();
    }


    public void bindMouseForDrawingRegularShapes() {
        canvas.setOnMousePressed(e -> addPoint(e.getX(), e.getY()));

        canvas.setOnMouseReleased(e -> {
            addPoint(e.getX(), e.getY());
            components.addComponent(createShapeByPoints());
            components.drawLast(g);

            components.clearMemory();
            clearPoints();
        });

        canvas.setOnMouseDragged(e -> {
            clearCanvas();
            addPoint(e.getX(), e.getY());
            components.addComponent(createShapeByPoints());
            removeLastPoint();
            components.draw(g);
            components.removeComponent();
        });
    }

    public void bindMouseDrawingDifficultShape() {
        canvas.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                addPoint(e.getX(), e.getY());
            } else if (e.getButton() == MouseButton.SECONDARY) {
                if (points.size() > 0) {
                    addPoint(e.getX(), e.getY());
                    components.addComponent(createShapeByPoints());
                    components.drawLast(g);

                    components.clearMemory();
                    clearPoints();
                } else {
                    System.out.println("Polygon will be invisible. Put more points");
                }
            }
        });

        canvas.setOnMouseMoved(e -> {
            if (points.size() > 0) {
                clearCanvas();
                addPoint(e.getX(), e.getY());
                components.addComponent(createShapeByPoints());
                removeLastPoint();
                components.draw(g);
                components.removeComponent();
            }
        });
    }

    public void resetMouseEvents() {
        canvas.setOnMousePressed(e -> {});
        canvas.setOnMouseReleased(e -> {});
        canvas.setOnMouseDragged(e -> {});
    }

    private Shape createShapeByPoints() {
        Double[] arr = new Double[points.size()];
        points.toArray(arr);

        double[] points = Stream.of(arr).mapToDouble(Double::doubleValue).toArray();
        return shapeFactory.createShape(points, g.getLineWidth(), new SColor((Color)g.getFill()), new SColor((Color)g.getStroke()), paintMode);
    }

    public void onUndo() {
        components.undoComponent();
        clearCanvas();
        components.draw(g);
    }

    public void onRedo() {
        components.redoComponent();
        clearCanvas();
        components.draw(g);
    }

    private void clearPoints() {
        points.clear();
    }

    private void removeLastPoint() {
        points.remove(points.size() - 1); //X
        points.remove(points.size() - 1); //Y
    }

    //Menu
    public void onSerialize() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("out/components.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(components);

            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDeserialize() {
        try {
            FileInputStream fileInputStream = new FileInputStream("out/components.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            components = (Composite) objectInputStream.readObject();
            components.draw(g);

            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onSave(Canvas canvas) {
        try {
            String path = "out";

            new File(path).mkdir();

            Image snapshot = canvas.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File(path + "/" + "paint.png"));

            System.out.println("File saved successfully");
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e);
        }
    }

    public void onExit() {
        Platform.exit();
    }

}
