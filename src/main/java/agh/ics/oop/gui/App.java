package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.Vector;

public class App extends Application{

    public void start(Stage primaryStage) {
        primaryStage.show();
        GridPane gridPane = new GridPane();


        MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        GrassField map = new GrassField(6);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Vector2d lowerLeft = map.lowerLeft();
        Vector2d upperRight = map.upperRight();
        Vector2d size = upperRight.subtract(lowerLeft);
        size = size.add(new Vector2d(2,2));
        System.out.println(size.toString());

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
                    content = "*";
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
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
