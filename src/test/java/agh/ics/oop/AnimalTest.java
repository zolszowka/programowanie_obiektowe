package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    private static final IWorldMap map = new RectangularMap(5, 5);
    @Test
    public void OrientationTest() {
        Animal zwierzak = new Animal(map); //NORTH
        for (int i = 0; i < 4; i++) {
            zwierzak.move(MoveDirection.RIGHT);
            switch (i) {
                case 0 -> assertEquals(zwierzak.getOrientation(), MapDirection.EAST);
                case 1 -> assertEquals(zwierzak.getOrientation(), MapDirection.SOUTH);
                case 2 -> assertEquals(zwierzak.getOrientation(), MapDirection.WEST);
                case 3 -> assertEquals(zwierzak.getOrientation(), MapDirection.NORTH);
            }
        }

        for (int i = 0; i < 4; i++) {
            zwierzak.move(MoveDirection.LEFT);
            switch (i) {
                case 0 -> assertEquals(zwierzak.getOrientation(), MapDirection.WEST);
                case 1 -> assertEquals(zwierzak.getOrientation(), MapDirection.SOUTH);
                case 2 -> assertEquals(zwierzak.getOrientation(), MapDirection.EAST);
                case 3 -> assertEquals(zwierzak.getOrientation(), MapDirection.NORTH);
            }
        }
        assertTrue(zwierzak.isAt(new Vector2d(2, 2)));
    }

    @Test
    public void PositionTest() {
        Animal zwierzak = new Animal(map);
        String[] input = {"f", "f", "b", "r", "f", "b", "r", "f", "f", "l", "f"};
        Vector2d[] correct = {new Vector2d(2, 3), new Vector2d(2, 4), new Vector2d(2, 3), new Vector2d(2, 3), new Vector2d(3, 3), new Vector2d(2, 3),new Vector2d(2, 3), new Vector2d(2, 2), new Vector2d(2, 1), new Vector2d(2, 1), new Vector2d(3,1)};
        MoveDirection[] result = OptionsParser.parse(input);
        assertEquals(result.length, correct.length);
        for (int i = 0; i < result.length; i++) {
            zwierzak.move(result[i]);
            assertTrue(zwierzak.isAt(correct[i]));
        }
    }

    @Test
    public void OutOfBoundsTest(){
        Animal zwierzak = new Animal(map);

        for(int i = 0; i<10; i++){
            zwierzak.move(MoveDirection.FORWARD);
        }
        assertTrue(zwierzak.isAt(new Vector2d(2,4)));

        zwierzak.move(MoveDirection.RIGHT);
        for(int i = 0; i<10; i++){
            zwierzak.move(MoveDirection.FORWARD);
        }
        assertTrue(zwierzak.isAt(new Vector2d(4,4)));

        zwierzak.move(MoveDirection.RIGHT);
        for(int i = 0; i<10; i++){
            zwierzak.move(MoveDirection.FORWARD);
        }
        assertTrue(zwierzak.isAt(new Vector2d(4,0)));

        zwierzak.move(MoveDirection.RIGHT);
        for(int i = 0; i<10; i++){
            zwierzak.move(MoveDirection.FORWARD);
        }
        assertTrue(zwierzak.isAt(new Vector2d(0,0)));
    }


    @Test
    public void OptionsParserTest() {
        String[] input = {"ff", "forward", "backward", "k", "fo", "f", "right", "rig", "f", "b", "r", "forward", "left", "l", " "};
        MoveDirection[] correct = {MoveDirection.FORWARD, MoveDirection.BACKWARD,MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.LEFT };
        MoveDirection[] result = OptionsParser.parse(input);
        assertEquals(result.length, correct.length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], correct[i]);
        }
    }
}
