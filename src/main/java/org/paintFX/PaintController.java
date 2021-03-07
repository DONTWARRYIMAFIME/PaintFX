package org.paintFX;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.paintFX.ShapeFactory.CircleFactory;
import org.paintFX.ShapeFactory.RectangleFactory;
import org.paintFX.ShapeFactory.ShapeFactory;
import org.paintFX.Shapes.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PaintController {

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField brushSize;

    @FXML
    private CheckBox eraser;

    @FXML
    private Label mouseCoordinates;

    private final PaintModel model = new PaintModel();
    private GraphicsContext g;

    private List<Double> points = new ArrayList<>();
    private List<Label> pointsLabels;

    private ShapeFactory shapeFactory;

    public void initialize() {
        g = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> {

            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;

            if (eraser.isSelected()) {
                g.clearRect(x, y, size, size);
            } else {
                g.setFill(colorPicker.getValue());
                g.fillOval(x, y, size, size);
            }
        });
    }

    public void setMouseCoordinates(MouseEvent e) {
        mouseCoordinates.setText(String.format("X : %.01f       Y : %.01f", e.getX(),e.getY()));
    }

    public void onMouseClickCanvas(MouseEvent e) {

        //If we choose shape that we want to draw
        if (shapeFactory != null) {
            if (e.getButton() == MouseButton.PRIMARY) {

                points.add(e.getX());
                points.add(e.getY());


                System.out.format(
                        "Point %d:  X - %.01f |    Y - %.01f\n",
                        points.size() / 2,
                        e.getX(),
                        e.getX()
                );


            } else if (e.getButton() == MouseButton.SECONDARY) {
                Shape shape = shapeFactory.createShape(g.getStroke(), colorPicker.getValue(), g.getLineWidth());

                Double[] arr = new Double[points.size()];
                points.toArray(arr);

                shape.draw(Stream.of(arr).mapToDouble(Double::doubleValue).toArray(), g);

                points.clear();
                shapeFactory = null;
            }
        }
    }

    public void setShapeFactoryToRectangle() {
        shapeFactory = new RectangleFactory();
    }

    public void setShapeFactoryToCircle() {
        shapeFactory = new CircleFactory();
    }

    public void onSave() {
        model.onSave(canvas);
    }

    public void onExit() {
        model.onExit();
    }

}