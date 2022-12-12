package agh.ics.oop;

import java.util.*;


public class MapBoundary implements IPositionChangeObserver{
    SortedSet<Vector2d> xCoord = new TreeSet((Comparator<Vector2d>) (u, v) -> {
        if(u.x == v.x)
            return u.y - v.y;
        return u.x - v.x;
    });

    SortedSet<Vector2d> yCoord = new TreeSet((Comparator<Vector2d>) (u, v) -> {
        if(u.y == v.y)
            return u.x - v.x;
        return u.y - v.y;
    });

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        if (!xCoord.isEmpty()){
            xCoord.remove(oldPosition);
            yCoord.remove(oldPosition);
        }
        xCoord.add(newPosition);
        yCoord.add(newPosition);
    }


}
