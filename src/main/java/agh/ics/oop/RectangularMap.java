package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap {
    private final Vector2d upperRight;
    private final Vector2d lowerLeft;
    private final ArrayList<Animal> animals;


    public RectangularMap(int width, int height) {
        this.upperRight = new Vector2d(width - 1, height - 1);
        this.lowerLeft = new Vector2d(0, 0);
        this.animals = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(this.lowerLeft) && position.precedes(this.upperRight) && !isOccupied(position);
    }

    @Override
    public Vector2d[] updateBounds() {
        return new Vector2d[]{this.lowerLeft, this.upperRight};
    }

}
