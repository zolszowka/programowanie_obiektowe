package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.*;

public class Animal implements IMapElement {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map) {
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d newPosition) {
        this.map = map;
        this.position = newPosition;
    }

    public String toString() {
        return switch (this.orientation) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
        };
    }

    @Override
    public String getPath() {
        return switch (this.getOrientation()) {
            case NORTH -> "src/main/resources/up.png";
            case SOUTH -> "src/main/resources/down.png";
            case EAST -> "src/main/resources/right.png";
            case WEST -> "src/main/resources/left.png";
        };
    }

    public void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }

    protected void notify(Vector2d oldPos, Vector2d newPos) {
        for (IPositionChangeObserver obs : observers) {
            obs.positionChanged(oldPos, newPos);
        }
    }


    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                Vector2d new_position = this.position.add(this.orientation.toUnitVector());
                if (this.map.canMoveTo(new_position)) {
                    notify(this.position, new_position);
                    this.position = new_position;
                }
            }
            case BACKWARD -> {
                Vector2d new_position = this.position.subtract(this.orientation.toUnitVector());
                if (this.map.canMoveTo(new_position)) {
                    notify(this.position, new_position);
                    this.position = new_position;
                }
            }
        }
    }


}
