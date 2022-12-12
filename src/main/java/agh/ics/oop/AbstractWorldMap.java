package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap,IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals;
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected Set<IMapElement> newMapElements = new HashSet<>();
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected final MapBoundary boundaryMap = new MapBoundary();

    protected abstract Vector2d[] updateBounds();

    public AbstractWorldMap() {
        this.animals = new HashMap<>();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition, animal);
        animals.put(newPosition, animal);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            this.animals.put(position, animal);
            animal.addObserver(this);
            return true;
        } else {
            throw new IllegalArgumentException("Can not place at specified position");
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.animals.get(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public String toString() {
        this.updateBounds();
        return this.mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }
    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }


}
