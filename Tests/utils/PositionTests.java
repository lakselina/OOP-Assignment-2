package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTests {

    private Position p1;
    private Position p2;
    private Position p3;

    @BeforeEach
    public void setUp() {
        p1 = new Position(0, 0);
        p2 = new Position(3, 4);
        p3 = new Position(0, 0);
    }

    @Test
    public void testDistanceCalculation() {
        assertEquals(5, p1.distance(p2), "Distance between (0,0) and (3,4) should be 5");
        assertEquals(5, p2.distance(p1), "Distance calculation should be symmetric");
        assertEquals(0, p1.distance(p1), "Distance to the exact same position should be 0");

        assertEquals(5, p2.distance(), "Distance from (3,4) to origin should be 5");
    }

    @Test
    public void testEquals() {
        assertTrue(p1.equals(p3), "Positions with the same X and Y should be considered equal");
        assertFalse(p1.equals(p2), "Positions with different coordinates should not be equal");
        assertFalse(p1.equals("Not a Position Object"), "Equals should return false for different types");
    }

    @Test
    public void testMoveAndAdd() {
        p1.move(2, 3);
        assertEquals(2, p1.getX(), "X should be updated after move");
        assertEquals(3, p1.getY(), "Y should be updated after move");

        Position p4 = p2.add(1, 1);
        assertEquals(4, p4.getX(), "New position X should be incremented");
        assertEquals(5, p4.getY(), "New position Y should be incremented");

        assertEquals(3, p2.getX(), "Original position X should remain unchanged");
        assertEquals(4, p2.getY(), "Original position Y should remain unchanged");
    }

    @Test
    public void testToString() {
        assertEquals("(3,4)", p2.toString(), "ToString format should be exactly (x,y)");
    }
}