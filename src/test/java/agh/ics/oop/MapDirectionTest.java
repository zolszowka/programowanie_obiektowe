package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    MapDirection E = MapDirection.EAST;
    MapDirection S = MapDirection.SOUTH;
    MapDirection W = MapDirection.WEST;
    MapDirection N = MapDirection.NORTH;

    @Test
    public void testNext(){
        assertEquals(E, N.next());
        assertEquals(S, E.next());
        assertEquals(W, S.next());
        assertEquals(N, W.next());
    }

    @Test
    public void testPrevious(){
        assertEquals(E, S.previous());
        assertEquals(S, W.previous());
        assertEquals(W, N.previous());
        assertEquals(N, E.previous());
    }
}
