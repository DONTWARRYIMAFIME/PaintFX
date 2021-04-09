package org.paintFX.mainWindow;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.paintFX.createCanvasWindow.CreateCanvasWindow;
import org.paintFX.core.*;
import org.paintFX.shapes.Circle;
import org.paintFX.shapes.Rectangle;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Model {

    private final FileChooser fileChooser = new FileChooser();
    private final List<Point> points = new LinkedList<>();
    private Shape newShape = null;
    private Composite components = new Composite();

    private final Pane canvasPane;
    private final Canvas canvas;
    private final GraphicsContext g;

    private ShapeFactory shapeFactory;
    private PaintMode paintMode;


    public Model(Pane canvasPane, Canvas canvas) {
        this.canvasPane = canvasPane;
        this.canvas = canvas;
        this.g = canvas.getGraphicsContext2D();

        initFileChooser();
    }

    public PaintMode getPaintMode() {
        return paintMode;
    }

    public void addPoint(double x, double y) {
        points.add(new Point(x, y));

        System.out.format(
                "Point %d:  X - %.01f |    Y - %.01f\n",
                points.size(),
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

    public boolean isPainting() {
        return newShape != null;
    }

    public void setPaintSettings() {
        newShape.setPaintSettings(g.getLineWidth(), new SColor((Color)g.getFill()), new SColor((Color)g.getStroke()), paintMode);
    }

    public String showMouseCoordinates(double x, double y) {
        return String.format("X : %.01f       Y : %.01f", x, y);
    }

    public void setPenTool() {
        Composite composite = new Composite();

        canvas.setOnMouseDragged(e -> {
            double size = g.getLineWidth();

            double x = e.getX();
            double y = e.getY();

            List<Point> temp = new LinkedList<>();
            temp.add(new Point(x , y));
            temp.add(new Point(x + size , y + size));

            Shape circle = new Circle(
                    size, new SColor((Color)g.getFill()),
                    new SColor((Color)g.getStroke()), PaintMode.FILLED
            );

            circle.setPoints(temp);

            composite.addComponent(circle);
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

            List<Point> temp = new LinkedList<>();
            temp.add(new Point(x , y));
            temp.add(new Point(x + size , y + size));

            Shape rectangle = new Rectangle(
                    size, new SColor((Color)g.getFill()),
                    new SColor((Color)g.getStroke()), PaintMode.CLEAR
            );

            rectangle.setPoints(temp);

            composite.addComponent(rectangle);
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

    public void bindMouseForDrawingShapes() {

        newShape = null;
        clearPoints();
        clearCanvas();
        components.draw(g);

        newShape = createShape();

        canvas.setOnMousePressed(e -> {
            addPoint(e.getX(), e.getY());
            if (e.getButton() == MouseButton.PRIMARY) {

                if (!newShape.isContinue(points.size())) {
                    components.addComponent(newShape);

                    components.clearMemory();
                    clearPoints();
                    bindMouseForDrawingShapes();
                }

            } else if (e.getButton() == MouseButton.SECONDARY) {

                if (newShape.isInfinite() || !newShape.isContinue(points.size())) {
                    components.addComponent(newShape);
                }

                clearPoints();
                components.clearMemory();
                bindMouseForDrawingShapes();
            }
        });

        canvas.setOnMouseMoved(e -> {
            if (points.size() > 0) {
                clearCanvas();

                addPoint(e.getX(), e.getY());
                newShape.setPoints(points);
                removeLastPoint();

                components.draw(g);
                newShape.draw(g);
            }
        });
    }

    public void resetMouseEvents() {
        canvas.setOnMousePressed(e -> {});
        canvas.setOnMouseReleased(e -> {});
        canvas.setOnMouseDragged(e -> {});
    }

    private Shape createShape() {
        return shapeFactory.createShape(g.getLineWidth(), new SColor((Color)g.getFill()), new SColor((Color)g.getStroke()), paintMode);
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
        points.remove(points.size() - 1);
    }

    //Menu
    public void onCreateCanvas() {
        try {
            new CreateCanvasWindow(canvasPane, canvas);
            clearCanvas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSerialize(File selectedFile) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(selectedFile.getAbsolutePath());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeDouble(canvasPane.getMaxWidth());
            objectOutputStream.writeDouble(canvasPane.getMaxHeight());
            objectOutputStream.writeObject(components);

            fileChooser.setInitialDirectory(selectedFile.getParentFile());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e1) {
            System.out.println("File is not valid");
        }
    }

    public void onDeserialize() {
        Window stage = canvas.getScene().getWindow();

        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Binary files", "*.bin"));

        File selectedFile = fileChooser.showOpenDialog(stage);

        try {
            FileInputStream fileInputStream = new FileInputStream(selectedFile.getAbsolutePath());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            double width = objectInputStream.readDouble();
            double height = objectInputStream.readDouble();

            resizePane(width, height);
            resizeCanvas(width, height);

            clearCanvas();
            components = (Composite) objectInputStream.readObject();
            components.draw(g);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e1) {
            System.out.println("File is not valid");
        }

    }

    private void resizePane(double width, double height) {
        canvasPane.setMaxWidth(width);
        canvasPane.setMaxHeight(height);
    }

    private void resizeCanvas(double width, double height) {
        canvas.setWidth(width);
        canvas.setHeight(height);
    }

    public void onSave(Canvas canvas) {
        Window stage = canvas.getScene().getWindow();

        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("picture");
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Binary files", "*.bin"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
                //new FileChooser.ExtensionFilter("JPEG", "*.jpeg", "*.jpg")
        );

        File selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, selectedFile.getName().length());

            System.out.format("Name: %s ; Extension: %s", fileName,fileExtension);

            if (Objects.equals(fileExtension, "bin")) {
                onSerialize(selectedFile);
            } else {
                savePicture(selectedFile.getAbsolutePath(), fileExtension);
            }

            fileChooser.setInitialDirectory(selectedFile.getParentFile());
        } else {
            System.out.println("File is not valid");
        }

    }

    private void savePicture(String path, String fileExtension) {
        try {
            Image snapshot = canvas.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), fileExtension, new File(path));

            System.out.println("File saved successfully");
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e);
        }
    }

    public void onExit() {
        Platform.exit();
    }

    private void initFileChooser() {
        fileChooser.setInitialDirectory(new File("out/"));

    }

}
