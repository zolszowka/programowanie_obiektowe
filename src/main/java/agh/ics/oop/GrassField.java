package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GrassField extends AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
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
            boundaryMap.positionChanged(new Vector2d(999, 999), position);
            Vector2d[] newBounds = updateBounds();
            this.lowerLeft = newBounds[0];
            this.upperRight = newBounds[1];


        }
    }

    public Vector2d generatePosition(int low, int high) {
        Vector2d position = new Vector2d((int) (Math.random() * (high - low)), (int) (Math.random() * (high - low)));
        return position;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(isOccupied(position)){
            Object o = objectAt(position);
            if (o instanceof Animal){
                return false;
            }
            if (o instanceof  Grass){  //zwierzątko zjada trawę
                int low = 0;
                int high = (int) Math.floor(Math.sqrt(10 * NumberOfGrassFields));
                Vector2d new_position = generatePosition(low, high);
                while (isOccupied(new_position)) {
                    new_position = generatePosition(low, high);
                }
                grasses.remove(o);
            }
        }
        return true;
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
    protected Vector2d[] updateBounds() {
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
