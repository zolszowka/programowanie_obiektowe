package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox extends Node {
    private final VBox vbox;

    public GuiElementBox(IMapElement object) throws FileNotFoundException {
        Image image = null;
        try {
            image = new Image(new FileInputStream(object.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        Label label = new Label(object.getPosition().x + ", " + object.getPosition().y);
        vbox = new VBox(0);
        vbox.getChildren().addAll(imageView, label);
        vbox.setPrefWidth(100);
        vbox.setAlignment(Pos.CENTER);
    }

    public VBox getBox() {
        return vbox;
    }
}
