package org.paintFX.mainWindow;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.paintFX.Loader;
import org.paintFX.shapeFactory.*;
import org.paintFX.core.PaintMode;

import java.util.regex.Pattern;

public class MainSceneController {

    private Model model;
    private final Pattern pattern = Pattern.compile("\\d{1,3}");

    @FXML
    private AnchorPane canvasPane;

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
    private Label lblPaintMode;

    @FXML
    private Label lblTool;

    @FXML
    private Label mouseCoordinates;


    //Tools
    @FXML
    private Button btnUndo;

    @FXML
    private Button btnRedo;

    @FXML
    private ToggleButton btnPen;

    @FXML
    private ToggleButton btnEraser;

    @FXML
    private ToggleButton btnCircle;

    @FXML
    private ToggleButton btnEllipse;

    @FXML
    private ToggleButton btnLine;

    @FXML
    private ToggleButton btnPolygon;

    @FXML
    private ToggleButton btnRectangle;

    @FXML
    private ToggleGroup paintMode;

    @FXML
    private ToggleGroup tools;

    @FXML
    private HBox lastBox;

    @FXML
    private VBox toolBox;

    private void loadPlugins() {
        model.loadPlugins(lblTool, tools, lastBox, toolBox);
    }

    public void initialize() {
        //Init model
        model = new Model(canvasPane, canvas);

        //TextField pattern
        TextFormatter<?> formatter = new TextFormatter<>(change -> pattern.matcher(change.getControlNewText()).matches() ? change : null);
        brushSize.setTextFormatter(formatter);

        //Icons for tools
        btnUndo.setGraphic(new ImageView(Loader.loadImage("icons/undo.png")));
        btnRedo.setGraphic(new ImageView(Loader.loadImage("icons/redo.png")));

        btnPen.setGraphic(new ImageView(Loader.loadImage("icons/pen.png")));
        btnEraser.setGraphic(new ImageView(Loader.loadImage("icons/eraser.png")));
        btnCircle.setGraphic(new ImageView(Loader.loadImage("icons/circle.png")));
        btnEllipse.setGraphic(new ImageView(Loader.loadImage("icons/ellipse.png")));
        btnLine.setGraphic(new ImageView(Loader.loadImage("icons/line.png")));
        btnPolygon.setGraphic(new ImageView(Loader.loadImage("icons/polygon.png")));
        btnRectangle.setGraphic(new ImageView(Loader.loadImage("icons/rectangle.png")));

        loadPlugins();

        setFillColor();
        setBorderColor();
        setBorderSize();

        setPaintModeToFilled();
        setPenTool();

        paintMode.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });

        tools.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
    }

    public void setFillColor() {
        model.setFillColor(cpFill.getValue());
        if (model.isPainting()) {
            model.setPaintSettings();
        }
    }

    public void setBorderColor() {
        model.setBorderColor(cpBorder.getValue());
        if (model.isPainting()) {
            model.setPaintSettings();
        }
    }

    public void setBorderSize() {
        model.setBorderSize(Double.parseDouble(brushSize.getText()));
        if (model.isPainting()) {
            model.setPaintSettings();
        }
    }

    private void resetMouseEvents() {
        model.resetMouseEvents();
        canvas.setOnMouseMoved(this::showMouseCoordinates);
    }

    public void showMouseCoordinates(MouseEvent e) {
        mouseCoordinates.setText(model.showMouseCoordinates(e.getX(), e.getY()));
    }

    public void setPenTool() {
        lblTool.setText("Tool : 'Pen' ");

        resetMouseEvents();
        model.setPenTool();
    }

    public void setEraserTool() {
        lblTool.setText("Tool : 'Eraser' ");

        resetMouseEvents();
        model.setEraserTool();
    }

    public void setShapeFactoryToRectangle() {
        lblTool.setText("Tool : 'Rectangle' ");

        resetMouseEvents();

        model.setShapeFactory(new RectangleFactory());
        model.bindMouseForDrawingShapes();
    }

    public void setShapeFactoryToCircle() {
        lblTool.setText("Tool : 'Circle' ");

        resetMouseEvents();

        model.setShapeFactory(new CircleFactory());
        model.bindMouseForDrawingShapes();
    }

    public void setShapeFactoryToEllipse() {
        lblTool.setText("Tool : 'Ellipse' ");

        resetMouseEvents();

        model.setShapeFactory(new EllipseFactory());
        model.bindMouseForDrawingShapes();
    }

    public void setShapeFactoryToLine() {
        lblTool.setText("Tool : 'Line' ");

        resetMouseEvents();

        model.setShapeFactory(new LineFactory());
        model.bindMouseForDrawingShapes();
    }

    public void setShapeFactoryToPolygon() {
        lblTool.setText("Tool : 'Polygon' ");

        resetMouseEvents();

        model.setShapeFactory(new PolygonFactory());
        model.bindMouseForDrawingShapes();
    }

    public void onSwap() {
        Color temp = cpFill.getValue();
        cpFill.setValue(cpBorder.getValue());
        cpBorder.setValue(temp);

        setFillColor();
        setBorderColor();
    }

    public void onUndo() {
        model.onUndo();
    }

    public void onRedo() {
        model.onRedo();
    }

    public void clearCanvas() {
        model.clearCanvas();
        model.removeComponents();
    }

    public void setPaintModeToFilled() {
        model.setPaintMode(PaintMode.FILLED);
        setPaintModeLabel();
        if (model.isPainting()) {
            model.setPaintSettings();
        }
    }

    public void setPaintModeToBordered() {
        model.setPaintMode(PaintMode.BORDERED);
        setPaintModeLabel();
        if (model.isPainting()) {
            model.setPaintSettings();
        }
    }

    public void setPaintModeToFilledWithBorder() {
        model.setPaintMode(PaintMode.FILLED_WITH_BORDER);
        setPaintModeLabel();
        if (model.isPainting()) {
            model.setPaintSettings();
        }
    }

    public void setPaintModeLabel() {
        lblPaintMode.setText("Paint mode : " + model.getPaintMode().getName());
    }

    public void onCreateCanvas() {
        model.onCreateCanvas();
    }

    public void onDeserialize() {
        model.onDeserialize();
    }

    public void onSave() {
        model.onSave(canvas);
    }

    public void onExit() {
        model.onExit();
    }

}