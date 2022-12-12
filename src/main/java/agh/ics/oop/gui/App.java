/*package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Set;

public class App extends Application {
    private AbstractWorldMap map;
    private Vector2d lowerLeft;
    private Vector2d upperRight;

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setGridLinesVisible(true);
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setTitle("World Map Visualization");

        this.addObjectsToGrid(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addObjectsToGrid(GridPane gridPane) {
        Vector2d lower = this.map.getLowerLeft();
        Vector2d upper = this.map.getUpperRight();

        // columns and rows settings
        for (int i = 0; lower.x + i <= upper.x + 1; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(20));
        }
        for (int i = 0; lower.y + i <= upper.y + 1; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(20));
        }

        // axis description
        Label l = new Label("y/x");
        gridPane.add(l, 0, 0);

        for (int i = lower.x; i <= upper.x; i++) {
            Label new_element = new Label(Integer.toString(i));
            GridPane.setHalignment(new_element, HPos.CENTER);
            gridPane.add(new_element, i - lower.x + 1, 0);
        }

        for (int i = lower.y; i <= upper.y; i++) {
            Label new_element = new Label(Integer.toString(i));
            GridPane.setHalignment(new_element, HPos.CENTER);
            gridPane.add(new_element, 0, upper.y + 1 - i);
        }

        for (int x = lower.x; x <= upper.x; x++) {
            for (int y = lower.y; y <= upper.y; y++) {
                if (map.isOccupied(new Vector2d(x, y))) {
                    Object obj = this.map.objectAt(new Vector2d(x, y));
                    if (obj != null) {
                        Label item = new Label(obj.toString());
                        GridPane.setHalignment(item, HPos.CENTER);
                        gridPane.add(item, x - lower.x + 1, upper.y + 1 - y);
                    }
                }
            }
        }
    }

    @Override
    public void init() throws Exception {
        super.init();
        try {
            String[] moves = {"l", "f", "f", "f", "l", "f", "f", "f"};
            MoveDirection[] directions = new OptionsParser().parse(moves);
            this.map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception);
        }
    }
}*/
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
