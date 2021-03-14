package org.paintFX;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.paintFX.ShapeFactory.CircleFactory;
import org.paintFX.ShapeFactory.RectangleFactory;

public class Controller {

    private Model model;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker cpFill;

    @FXML
    private ColorPicker cpBorder;

    @FXML
    private TextField brushSize;


    //Labels
    @FXML
    private Label lblTool;

    @FXML
    private Label mouseCoordinates;


    //Tools
    @FXML
    private Button btnPen;

    @FXML
    private Button btnRectangle;


    public void initialize() {
        //Init model
        model = new Model(canvas);

        //Icons for tools
        btnRectangle.setGraphic(new ImageView(Model.loadImage("icons/rectangle.png")));

        setFillColor();
        setBorderColor();
        setBorderSize();

        setFillColor();

        canvas.setOnMouseMoved(e -> showMouseCoordinates(e));
    }

    public void setFillColor() {
        model.setFillColor(cpFill.getValue());
    }

    public void setBorderColor() {
        model.setBorderColor(cpBorder.getValue());
    }

    public void setBorderSize() {
        model.setBorderSize(Double.parseDouble(brushSize.getText()));
    }

    public void showMouseCoordinates(MouseEvent e) {
        mouseCoordinates.setText(model.showMouseCoordinates(e.getX(), e.getY()));
    }

    public void setPenTool() {
        lblTool.setText("Tool : 'Pen' ");
        model.setPenTool(Double.parseDouble(brushSize.getText()));
    }

    public void setEraserTool() {
        lblTool.setText("Tool : 'Eraser' ");
        model.setEraserTool(Double.parseDouble(brushSize.getText()));
    }

    public void setShapeFactoryToRectangle() {
        lblTool.setText("Tool : 'Rectangle' ");

        model.bindMouseForDrawingRegularShapes();
        model.setShapeFactory(new RectangleFactory());
    }

    public void setShapeFactoryToCircle() {
        lblTool.setText("Tool : 'Circle' ");

        model.bindMouseForDrawingRegularShapes();
        model.setShapeFactory(new CircleFactory());
    }

    public void onSwap() {
        Color temp = cpFill.getValue();
        cpFill.setValue(cpBorder.getValue());
        cpBorder.setValue(temp);

        setFillColor();
        setBorderColor();
    }

    public void clearCanvas() {
        model.clearCanvas();
        model.removeComponents();
    }

    public void setPaintModeToFilled() {
        model.setPaintMode(PaintMode.FILLED);
    }

    public void setPaintModeToBordered() {
        model.setPaintMode(PaintMode.BORDERED);
    }

    public void setPaintModeToFilledWithBorder() {
        model.setPaintMode(PaintMode.FILLED_WITH_BORDER);
    }

    public void onSave() {
        model.onSave(canvas);
    }

    public void onExit() {
        model.onExit();
    }

}