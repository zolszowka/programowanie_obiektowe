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


public class App extends Application {
    AbstractWorldMap map;
    MoveDirection[] directions;
    IEngine engine;

    public void init() throws Exception {
        super.init();
        try {
            System.out.println("Start");
            String[] args = getParameters().getRaw().toArray(new String[0]);
            directions = OptionsParser.parse(args);
            map = new GrassField(30);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map);
            System.out.println("Stop");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception);
        }

    }

    @Override
    public void start(Stage primaryStage){
        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();
        GridPane grid = new GridPane();

        Label label1 = new Label("y/x");
        grid.add(label1, 0, 0, 1, 1);
        grid.getColumnConstraints().add(new ColumnConstraints(40));
        grid.getRowConstraints().add(new RowConstraints(40));
        GridPane.setHalignment(label1, HPos.CENTER);


        for(int i = lowerLeft.x; i <= upperRight.x; i++){
            Label label2 = new Label(" "+i+" ");
            grid.add(label2, i - lowerLeft.x + 1, 0, 1, 1);
            grid.getColumnConstraints().add(new ColumnConstraints(40));
            GridPane.setHalignment(label2, HPos.CENTER);
        }

        for(int i = lowerLeft.y; i <= upperRight.y; i++){
            Label label3 = new Label(" "+i+" ");
            grid.add(label3, 0, upperRight.y - i + 1, 1, 1);
            grid.getRowConstraints().add(new RowConstraints(40));
            GridPane.setHalignment(label3, HPos.CENTER);
        }

        for(int i = lowerLeft.x; i <= upperRight.x; i++){
            for(int j = lowerLeft.y; j <= upperRight.y; j++){
                Vector2d pos = new Vector2d(i, j);
                if(map.objectAt(pos) != null){
                    Label label4 = new Label(map.objectAt(pos).toString());
                    grid.add(label4, pos.x - lowerLeft.x + 1, upperRight.y - pos.y + 1, 1, 1);
                    GridPane.setHalignment(label4, HPos.CENTER);
                }
            }
        }

        grid.setGridLinesVisible(true);

        Scene scene = new Scene(grid, upperRight.x * 40 + 100 - lowerLeft.x * 40,  upperRight.y * 40 + 100 - lowerLeft.y * 40);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Okno aplikacji");
        primaryStage.show();

    }

}
