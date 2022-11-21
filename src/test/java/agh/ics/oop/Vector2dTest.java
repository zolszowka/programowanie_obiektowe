package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d position_1_2_a = new Vector2d(1,2);
    Vector2d position_1_2_b = new Vector2d(1,2);
    Vector2d position_3_3 = new Vector2d(3,3);

    @Test
    public void equalsTest() {
        assertTrue(position_1_2_a.equals(position_1_2_b));
        assertFalse(position_1_2_a.equals(position_3_3));
        assertFalse(position_1_2_b.equals(position_3_3));
    }

    @Test
    public void toStringTest() {
        assertEquals(position_1_2_a.toString(), "(1, 2)");
        assertEquals(position_3_3.toString(), "(3, 3)");
        assertNotEquals(position_1_2_a.toString(), "(-1, 2)");
        assertNotEquals(position_3_3.toString(), "(-3, -3)");
    }

    @Test
    public void precedesTest() {
        assertTrue(position_1_2_a.precedes(position_3_3));
        assertTrue(position_1_2_b.precedes(position_3_3));
        assertFalse(position_1_2_a.precedes(new Vector2d(0, 0)));
        assertFalse(position_3_3.precedes(new Vector2d(-5, -4)));
    }

    @Test
    public void followsTest() {
        assertTrue(position_3_3.follows(position_1_2_a));
        assertTrue(position_3_3.follows(position_1_2_b));
        assertFalse(position_1_2_a.follows(new Vector2d(3, 4)));
        assertFalse(position_1_2_b.follows(new Vector2d(2, 6)));
    }

    @Test
    public void upperRightTest() {
        assertEquals((new Vector2d(-5, 4)).upperRight(new Vector2d(0, 3)), new Vector2d(0, 4));
        assertEquals((new Vector2d(1, 2)).upperRight(new Vector2d(3, 1)), new Vector2d(3, 2));
        assertNotEquals((new Vector2d(-5, 0)).upperRight(new Vector2d(2, -2)), new Vector2d(-5, -2));
        assertNotEquals((new Vector2d(1, 2)).upperRight(new Vector2d(1, 1)), new Vector2d(1, 1));
    }

    @Test
    public void lowerLeftTest() {
        assertEquals((new Vector2d(1, 2 )).lowerLeft(new Vector2d(3, 1)), new Vector2d(1, 1));
        assertEquals((new Vector2d(-5, 0)).lowerLeft(new Vector2d(2, -2)), new Vector2d(-5, -2));
        assertNotEquals((new Vector2d(-5, 4)).lowerLeft(new Vector2d(0, 3)), new Vector2d(0, 4));
        assertNotEquals((new Vector2d(1, 2)).lowerLeft(new Vector2d(3, 1)), new Vector2d(-5, -5));
    }

    @Test
    public void addTest() {
        assertEquals(position_1_2_a.add(new Vector2d(2,1)), position_3_3);
        assertEquals(position_1_2_a.add(new Vector2d(0,0)), position_1_2_b);
        assertNotEquals(position_1_2_a.add(new Vector2d(-3,-2)), position_3_3);
        assertNotEquals(position_1_2_a.add(new Vector2d(1,4)), position_1_2_b);
    }

    @Test
    public void subtractTest() {
        assertEquals(position_3_3.subtract(new Vector2d(2,1)), position_1_2_a);
        assertEquals(position_1_2_a.subtract(new Vector2d(0,0)), position_1_2_b);
        assertNotEquals(position_3_3.subtract(new Vector2d(-3,-3)), position_1_2_a);
        assertNotEquals(position_1_2_a.subtract(new Vector2d(4,4)), position_1_2_b);
    }

    @Test
    public void oppositeTest() {
        assertEquals(position_1_2_a.opposite(), new Vector2d(-1,-2));
        assertEquals(position_3_3.opposite(), new Vector2d(-3,-3));
        assertNotEquals(position_1_2_a.opposite(), new Vector2d(-1,2));
        assertNotEquals(position_3_3.opposite(), new Vector2d(3,3));

    }
}
