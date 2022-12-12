package agh.ics.oop.gui;


import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class App extends Application implements IPositionChangeObserver {
    AbstractWorldMap map;
    private SimulationEngine engine;
    GridPane grid = new GridPane();
    ;

    public void init() throws Exception {
        super.init();
        try {
            System.out.println("Start.");
            this.map = new GrassField(15);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            engine = new SimulationEngine(map, positions, 900);
            System.out.println("Stop.");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception);
        }

    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        for (Vector2d object : map.getAnimals().keySet()) {
            if (map.getElement(object) instanceof Animal) {
                map.getElement(object).addObserver(this);
            }
        }
        Label header = new Label("Witaj! Aby zwierzaki sie poruszaly wpisz dowolna kombinacje ruchow: f r b l oddzielonych spacjami.");
        header.setWrapText(true);

        Button startbutton = new Button("Start");
        TextField textField = new TextField("Podaj ruchy.");
        startbutton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                MoveDirection[] moves = OptionsParser.parse(textField.getText().split(" "));
                engine.setMoves(moves);
                Thread engineThread = new Thread(engine);
                engineThread.start();
            }
        });

        HBox movesInput = new HBox(startbutton, textField);
        movesInput.setSpacing(30);
        VBox mainGrid = new VBox(header, grid, movesInput);
        mainGrid.setAlignment(Pos.CENTER);

        makeGrid();
        grid.setGridLinesVisible(true);
        Scene scene = new Scene(mainGrid);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Okno aplikacji");
        primaryStage.show();

    }

    public void makeGrid() throws FileNotFoundException {
        grid.getChildren().clear();

        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

        Label label1 = new Label("y/x");
        grid.add(label1, 0, 0, 1, 1);
        grid.getColumnConstraints().add(new ColumnConstraints(40));
        grid.getRowConstraints().add(new RowConstraints(40));
        GridPane.setHalignment(label1, HPos.CENTER);


        for (int i = lowerLeft.x; i <= upperRight.x; i++) {
            Label label2 = new Label(" " + i + " ");
            grid.add(label2, i - lowerLeft.x + 1, 0, 1, 1);
            grid.getColumnConstraints().add(new ColumnConstraints(40));
            GridPane.setHalignment(label2, HPos.CENTER);
        }

        for (int i = lowerLeft.y; i <= upperRight.y; i++) {
            Label label3 = new Label(" " + i + " ");
            grid.add(label3, 0, upperRight.y - i + 1, 1, 1);
            grid.getRowConstraints().add(new RowConstraints(40));
            GridPane.setHalignment(label3, HPos.CENTER);
        }

        for (int i = lowerLeft.x; i <= upperRight.x; i++) {
            for (int j = lowerLeft.y; j <= upperRight.y; j++) {
                Object object = this.map.objectAt(new Vector2d(i, j));
                Vector2d pos = new Vector2d(i, j);
                if (map.isOccupied(pos)) {
                    GuiElementBox VBox = new GuiElementBox((IMapElement) object);
                    grid.add(VBox.getBox(), pos.x - lowerLeft.x + 1, upperRight.y - pos.y + 1, 1, 1);
                    GridPane.setHalignment(label1, HPos.CENTER);
                }
            }
        }

    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() -> {
            try {
                grid.getColumnConstraints().clear();
                grid.getRowConstraints().clear();
                grid.setGridLinesVisible(false);
                makeGrid();
                grid.setGridLinesVisible(true);


            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
                System.out.println(exception.getMessage());
            }
        });
    }
}