package agh.ics.oop;

public class Animal implements IMapElement {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);
    private final IWorldMap map;

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
                    this.position = new_position;
                }
            }
            case BACKWARD -> {
                Vector2d new_position = this.position.subtract(this.orientation.toUnitVector());
                if (this.map.canMoveTo(new_position)) {
                    this.position = new_position;
                }
            }
        }
    }


}
