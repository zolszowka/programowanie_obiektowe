package agh.ics.oop;

import java.util.ArrayList;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int NumberOfGrassFields;
    private final ArrayList<Grass> grasses;

    public GrassField(int NumberOfGrassFields) {
        this.NumberOfGrassFields = NumberOfGrassFields;
        this.grasses = new ArrayList<>();
        int low = 0;
        int high = (int) Math.floor(Math.sqrt(10 * NumberOfGrassFields));
        Vector2d position = generatePosition(low, high);
        for (int i = 0; i < NumberOfGrassFields; i++) {
            while (isOccupied(position)) {
                position = generatePosition(low, high);
            }
            grasses.add(new Grass(position));
        }
    }

    public Vector2d generatePosition(int low, int high) {
        Vector2d position = new Vector2d((int) (Math.random() * (high - low)), (int) (Math.random() * (high - low)));
        return position;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) {
            return super.objectAt(position);
        }
        for (Grass grass : grasses) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    public Vector2d[] updateBounds() {
        Vector2d upper = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Vector2d lower = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (Animal animal : this.animals) {
            upper = upper.upperRight(animal.getPosition());
            lower = lower.lowerLeft(animal.getPosition());
        }

        for (Grass grass : this.grasses) {
            upper = upper.upperRight(grass.getPosition());
            lower = lower.lowerLeft(grass.getPosition());
        }
        return new Vector2d[]{lower, upper};
    }

}
