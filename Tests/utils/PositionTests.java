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
        assertEquals(5.0, p1.distance(p2), "Distance between (0,0) and (3,4) should be 5.0");

        assertEquals(5.0, p2.distance(p1), "Distance calculation should be symmetric");

        assertEquals(0.0, p1.distance(p1), "Distance to the exact same position should be 0.0");
    }

    @Test
    public void testEqualsAndHashCode() {
        assertTrue(p1.equals(p3), "Positions with the same X and Y should be considered equal");

        assertFalse(p1.equals(p2), "Positions with different coordinates should not be equal");
    }

    @Test
    public void testCompareTo() {
        Position lowerRight = new Position(1, 1);

        assertTrue(p1.equals(lowerRight), "Position (0,0) should come before (1,1)");

        assertEquals(0, p1.equals(p3), "Comparing identical positions should return 0");
    }
}