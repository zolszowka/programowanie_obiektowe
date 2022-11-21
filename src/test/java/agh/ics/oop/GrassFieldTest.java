package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {

    @Test
    public void boundsTest() {
        GrassField map = new GrassField(0);
        Animal animal1 = new Animal(map, new Vector2d(0, 0));
        Animal animal2 = new Animal(map, new Vector2d(1, 3));
        Animal animal3 = new Animal(map, new Vector2d(4, 2));

        map.place(animal1);
        map.place(animal2);
        map.place(animal3);

        Vector2d lower = new Vector2d(0, 0);
        Vector2d upper = new Vector2d(4, 3);
        Vector2d[] newBounds = map.updateBounds();
        assertEquals(lower, newBounds[0]);
        assertEquals(upper, newBounds[1]);
    }

    @Test
    public void GrassFieldTest() {
        GrassField map = new GrassField(10);
        int counter = 0;
        int low = 0;
        int high = (int) Math.floor(Math.sqrt(10 * 10));
        for (int i = low; i <= high; i++) {
            for (int j = low; j <= high; j++) {
                if (map.objectAt(new Vector2d(i, j)) != null && map.objectAt(new Vector2d(i, j)) instanceof Grass) {
                    counter++;
                }
            }
        }
        assertEquals(counter, 10);
    }


    @Test
    public void placeTest() {
        GrassField map = new GrassField(0);
        Animal animal1 = new Animal(map, new Vector2d(2, 2));
        Animal animal2 = new Animal(map, new Vector2d(0, 0));
        Animal animal3 = new Animal(map, new Vector2d(1, 1));

        assertTrue(map.place(animal1));
        assertTrue(map.place(animal2));
        assertTrue(map.place(animal3));

    }

    @Test
    public void canMoveToTest() {
        GrassField map = new GrassField(0);
        Animal animal1 = new Animal(map, new Vector2d(2, 2));
        Animal animal2 = new Animal(map, new Vector2d(0, 0));
        Animal animal3 = new Animal(map, new Vector2d(1, 1));

        map.place(animal1);
        map.place(animal2);
        map.place(animal3);

        assertTrue(map.canMoveTo(new Vector2d(1, 0)));
        assertTrue(map.canMoveTo(new Vector2d(2, 1)));
        assertFalse(map.canMoveTo(new Vector2d(1, 1)));
        assertFalse(map.canMoveTo(new Vector2d(0, 0)));
    }

    @Test
    public void objectAtTest() {
        GrassField map = new GrassField(0);
        Animal animal1 = new Animal(map, new Vector2d(2, 2));
        Animal animal2 = new Animal(map, new Vector2d(0, 0));
        Animal animal3 = new Animal(map, new Vector2d(1, 1));

        map.place(animal1);
        map.place(animal2);
        map.place(animal3);

        assertEquals(map.objectAt(new Vector2d(2, 2)), animal1);
        assertEquals(map.objectAt(new Vector2d(0, 0)), animal2);
        assertEquals(map.objectAt(new Vector2d(1, 1)), animal3);
    }

    @Test
    public void isOccupiedTest() {
        GrassField map = new GrassField(0);
        Animal animal1 = new Animal(map, new Vector2d(2, 2));
        Animal animal2 = new Animal(map, new Vector2d(0, 0));
        Animal animal3 = new Animal(map, new Vector2d(1, 1));

        map.place(animal1);
        map.place(animal2);
        map.place(animal3);

        assertTrue(map.isOccupied(new Vector2d(0, 0)));
        assertTrue(map.isOccupied(new Vector2d(1, 1)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertFalse(map.isOccupied(new Vector2d(-1, -1)));
        assertFalse(map.isOccupied(new Vector2d(1, 2)));
    }


}


