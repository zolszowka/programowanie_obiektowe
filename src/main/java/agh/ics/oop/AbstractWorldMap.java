package agh.ics.oop;


import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals;
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;

    protected  Vector2d position;
    protected final MapBoundary boundaryMap = new MapBoundary();

    protected abstract Vector2d[] updateBounds();
    abstract public Vector2d findUpperRight();
    abstract public Vector2d findLowerLeft();

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
            boundaryMap.positionChanged(new Vector2d(9999,9999), animal.getPosition());
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

    public Map<Vector2d, Animal> getAnimals(){
        return this.animals;
    }

    @Override
    public String getPath(){
        return null;
    };


    public Animal getElement(Vector2d pos){
        return this.animals.get(pos);
    }
}
