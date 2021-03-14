package org.paintFX;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Paint;
import org.paintFX.ShapeFactory.ShapeFactory;
import org.paintFX.Shapes.Shape;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Model {

    private final List<Double> points = new ArrayList<>();
    private final Composite components = new Composite();
    private final Canvas canvas;
    private final GraphicsContext g;

    private ShapeFactory shapeFactory;
    private PaintMode paintMode = PaintMode.FILLED;

    public Model(Canvas canvas) {
        this.canvas = canvas;
        this.g = canvas.getGraphicsContext2D();
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

    public void setPaintMode(PaintMode paintMode) {
        this.paintMode = paintMode;
    }

    public void setShapeFactory(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public void setFillColor(Paint color) {
        g.setFill(color);
    }

    public void setBorderColor(Paint color) {
        g.setStroke(color);
    }

    public void setBorderSize(double size) {
        g.setLineWidth(size);
    }

    public Shape createShapeByPoints() {
        Double[] arr = new Double[points.size()];
        points.toArray(arr);

        double[] points = Stream.of(arr).mapToDouble(Double::doubleValue).toArray();
        Shape shape = shapeFactory.createShape(points, g.getLineWidth(), g.getFill(), g.getStroke(), paintMode);

        this.points.clear();

        return shape;
    }

    public String showMouseCoordinates(double x, double y) {
        return String.format("X : %.01f       Y : %.01f", x, y);
    }

    public void draw() {
        components.draw(g);
    }

    public void setPenTool(double size) {
        resetMouseEvents();

        canvas.setOnMouseDragged(e -> {
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;

            g.fillOval(x, y, size, size);

        });
    }

    public void setEraserTool(double size) {
        resetMouseEvents();

        canvas.setOnMouseDragged(e -> {
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;

            g.clearRect(x, y, size, size);
        });
    }

    public void clearCanvas() {
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void removeComponents() {
        components.removeAllComponents();
    }


    public void bindMouseForDrawingRegularShapes() {
        resetMouseEvents();

        canvas.setOnMousePressed(e ->
                addPoint(e.getX(), e.getY())
        );

        canvas.setOnMouseReleased(e -> {
            clearCanvas();

            addPoint(e.getX(), e.getY());
            components.pushComponent(createShapeByPoints());
            draw();
        });

//        canvas.setOnMouseDragged(e -> {
//            clearCanvas();
//
//            model.addPoint(e.getX(), e.getY());
//            Shape shape = model.createShapeByPoints(shapeFactory);
//            components.pushComponent(shape);
//            draw();
//
//        });
    }

    private void bindMouseDrawingDifficultShape() {
        resetMouseEvents();

        canvas.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                addPoint(e.getX(), e.getY());
            } else if (e.getButton() == MouseButton.SECONDARY) {
                components.pushComponent(createShapeByPoints());
                draw();
            }
        });
    }

    private void resetMouseEvents() {
        canvas.setOnMousePressed(e -> {});
        canvas.setOnMouseReleased(e -> {});
        canvas.setOnMouseDragged(e -> {});
        //canvas.setOnMouseMoved(e -> showMouseCoordinates(e.getX(), e.getY()));
    }

    //Menu
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

    //
    public static Image loadImage(String path) {
        Image image = null;
        try {
            image = new Image(Main.class.getResource(path).toURI().toString());
        } catch (Exception e) {
            System.out.format("Cannot convert your path(%s) to URI\n", path);
        }

        return image;
    }

}
