package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.Vector;

public class App extends Application implements AnimalChangeObserver{

    private MoveDirection[] directions;
    private Vector2d[] positions;
    private GrassField map;
    private SimulationEngine engine;
    private GridPane gridPane;
    private Scene scene;
    private Stage primaryStage;

    private VBox vbox;
    private Button startButton;
    private TextField textField;
    private Thread engineThread;

    private final int moveDelay = 300;

    public void init() {
//        directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        map = new GrassField(6);
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
        engine = new SimulationEngine(map, positions);
        engine.addObserver(this);
        engineThread = new Thread(engine);
        //engineThread.start();
        gridPane = new GridPane();
    }

    private void render() {
        GridPane gridPane = new GridPane();
        Vector2d lowerLeft = map.lowerLeft();
        Vector2d upperRight = map.upperRight();
        Vector2d size = upperRight.subtract(lowerLeft);
        size = size.add(new Vector2d(2,2));

        Label labels[][] = new Label[size.getX()][size.getY()];


        for(int i = 0; i < size.getX(); i++) {
            for(int j = 0; j < size.getY(); j++) {
                String content = "";
                if(j==0) {
                    content = (Integer.toString(i+lowerLeft.getX()-1));
                }
                else if(i==0) {
                    content = (Integer.toString(upperRight.getY()-j+1));

                }
                if(j==0 && i==0) {
                    content = "x/y";
                }

                if(map.isOccupied(new Vector2d(i-1,size.getY()-j-1).add(lowerLeft))) {
                    content = map.objectAt(new Vector2d(i-1,size.getY()-j-1).add(lowerLeft)).toString();
                }
                labels[i][j] = new Label(content);

                gridPane.add(labels[i][j], i, j,1,1);
                GridPane.setHalignment(labels[i][j], HPos.CENTER);
            }
            gridPane.getColumnConstraints().add(new ColumnConstraints(25));
        }

        for(int j = 0; j < size.getY(); j++) {
            gridPane.getRowConstraints().add(new RowConstraints(25));
        }

        gridPane.setGridLinesVisible(true);

        vbox = new VBox(gridPane, startButton, textField);
        scene.setRoot(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void changeHappened() {
        try {
            Thread.sleep(moveDelay);
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
            System.exit(-1);
        }
        Platform.runLater(() -> {
            gridPane.getChildren().clear();
            render();
        });
        //gridPane.getChildren().clear();
        //render();
    }
    public void start(Stage primaryStage) {
        primaryStage.show();
        this.primaryStage = primaryStage;
        startButton = new Button("Start");
        textField = new TextField();
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                directions = new OptionsParser().parse(textField.getText().split("\\s+"));
                engine.setDirections(directions);
                engineThread = new Thread(engine);
                engineThread.start();

            }
        });
        vbox = new VBox(gridPane, textField, startButton);
        scene = new Scene(vbox, 400, 400);
        render();
    }
}
