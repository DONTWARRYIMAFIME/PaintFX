package org.paintFX.MainWindow;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.paintFX.Loader;
import org.paintFX.ShapeFactory.*;
import org.paintFX.core.IService;
import org.paintFX.core.PaintMode;

import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    private ToggleButton btnTrapezium;

    @FXML
    private ToggleGroup paintMode;

    @FXML
    private ToggleGroup tools;

    @FXML
    private VBox toolBox;

    private void loadPlugins() {
        Path pluginsDir = Paths.get("plugins");

        // Будем искать плагины в папке plugins
        ModuleFinder pluginsFinder = ModuleFinder.of(pluginsDir);

        // Пусть ModuleFinder найдёт все модули в папке plugins и вернёт нам список их имён
        List<String> plugins = pluginsFinder
                .findAll()
                .stream()
                .map(ModuleReference::descriptor)
                .map(ModuleDescriptor::name)
                .collect(Collectors.toList());

        // Создадим конфигурацию, которая выполнит резолюцию указанных модулей (проверит корректность графа зависимостей)
        Configuration pluginsConfiguration = ModuleLayer
                .boot()
                .configuration()
                .resolve(pluginsFinder, ModuleFinder.of(), plugins);

        // Создадим слой модулей для плагинов
        ModuleLayer layer = ModuleLayer
                .boot()
                .defineModulesWithOneLoader(pluginsConfiguration, ClassLoader.getSystemClassLoader());

        // Найдём все реализации сервиса IService в слое плагинов и в слое Boot
        List<IService> services = IService.getServices(layer);
        for (IService service : services) {
            ToggleButton button = new ToggleButton();
            button.setToggleGroup(tools);
            button.setGraphic(new ImageView(service.getIcon()));
            button.setOnAction(e -> {
                lblTool.setText("Tool : " + service.getToolName());

                resetMouseEvents();
                model.bindMouseForDrawingShapes();
                model.setShapeFactory(service.createFactory());
            });

            toolBox.getChildren().add(button);
        }
    }


    public void initialize() {
        //Init model
        model = new Model(canvasPane, canvas);

        //TextField pattern
        TextFormatter<?> formatter = new TextFormatter<>(change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
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

        btnTrapezium.setGraphic(new ImageView(Loader.loadImage("icons/trapezium.png")));

        btnRectangle.setGraphic(new ImageView(Loader.loadImage("icons/rectangle.png")));
        //loadPlugins();

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
    }

    public void setBorderColor() {
        model.setBorderColor(cpBorder.getValue());
    }

    public void setBorderSize() {
        model.setBorderSize(Double.parseDouble(brushSize.getText()));
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
        model.bindMouseForDrawingShapes();
        model.setShapeFactory(new RectangleFactory());
    }

    public void setShapeFactoryToCircle() {
        lblTool.setText("Tool : 'Circle' ");

        resetMouseEvents();
        model.bindMouseForDrawingShapes();
        model.setShapeFactory(new CircleFactory());
    }

    public void setShapeFactoryToEllipse() {
        lblTool.setText("Tool : 'Ellipse' ");

        resetMouseEvents();
        model.bindMouseForDrawingShapes();
        model.setShapeFactory(new EllipseFactory());
    }

    public void setShapeFactoryToLine() {
        lblTool.setText("Tool : 'Line' ");

        resetMouseEvents();
        model.bindMouseForDrawingShapes();
        model.setShapeFactory(new LineFactory());
    }

    public void setShapeFactoryToPolygon() {
        lblTool.setText("Tool : 'Polygon' ");

        resetMouseEvents();
        model.bindMouseForDrawingShapes();
        model.setShapeFactory(new PolygonFactory());
    }

    public void setShapeFactoryToTrapezium() {
        lblTool.setText("Tool : 'Trapezium' ");

        resetMouseEvents();
        model.bindMouseForDrawingShapes();
        model.setShapeFactory(new TrapeziumFactory());
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
    }

    public void setPaintModeToBordered() {
        model.setPaintMode(PaintMode.BORDERED);
        setPaintModeLabel();
    }

    public void setPaintModeToFilledWithBorder() {
        model.setPaintMode(PaintMode.FILLED_WITH_BORDER);
        setPaintModeLabel();
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