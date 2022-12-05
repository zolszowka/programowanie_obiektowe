package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int NumberOfGrassFields;
    private final Map<Vector2d, Grass> grasses;

    public GrassField(int NumberOfGrassFields) {
        this.NumberOfGrassFields = NumberOfGrassFields;
        this.grasses = new HashMap<>();
        int low = 0;
        int high = (int) Math.floor(Math.sqrt(10 * NumberOfGrassFields));
        Vector2d position = generatePosition(low, high);
        for (int i = 0; i < NumberOfGrassFields; i++) {
            while (isOccupied(position)) {
                position = generatePosition(low, high);
            }
            Grass pieceOfGrass = new Grass(position);
            this.grasses.put(position, pieceOfGrass);
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
        else{
            return grasses.get(position);
        }
    }

    @Override
    public Vector2d[] updateBounds() {
        Vector2d upper = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Vector2d lower = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (IMapElement animal : animals.values()) {
            upper = upper.upperRight(animal.getPosition());
            lower = lower.lowerLeft(animal.getPosition());
        }

        for (IMapElement grass : grasses.values()) {
            upper = upper.upperRight(grass.getPosition());
            lower = lower.lowerLeft(grass.getPosition());
        }
        return new Vector2d[]{lower, upper};
    }

}
