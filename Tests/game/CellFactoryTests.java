package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import board.Cell;
import board.Floor;
import board.Wall;
import units.enemy.Monster;
import units.enemy.Trap;
import utils.Position;

public class CellFactoryTests {

    @Test
    public void testCreateWall() {
        Position p = new Position(0, 0);
        Cell cell = CellFactory.create('#', p);

        assertNotNull(cell);
        assertTrue(cell instanceof Wall, "Character '#' should create a Wall");
    }

    @Test
    public void testCreateEmptyFloor() {
        Position p = new Position(1, 1);
        Cell cellDot = CellFactory.create('.', p);
        Cell cellPlayer = CellFactory.create('@', p);

        assertTrue(cellDot instanceof Floor, "Character '.' should create a Floor");
        assertTrue(cellPlayer instanceof Floor, "Character '@' should create a Floor");

        assertNull(((Floor) cellDot).getOccupant());
        assertNull(((Floor) cellPlayer).getOccupant());
    }

    @Test
    public void testCreateMonster() {
        Position p = new Position(2, 2);
        Cell cell = CellFactory.create('s', p);

        assertTrue(cell instanceof Floor, "Monster should be placed on a Floor");
        Floor floor = (Floor) cell;

        assertNotNull(floor.getOccupant(), "Floor should have an occupant");
        assertTrue(floor.getOccupant() instanceof Monster, "Occupant should be a Monster");

        Monster monster = (Monster) floor.getOccupant();
        assertEquals('s', monster.getTile());
    }

    @Test
    public void testCreateVariousMonsters() {
        Position p = new Position(3, 3);
        char[] monsterChars = {'k', 'q', 'z', 'b', 'g', 'w', 'M', 'C', 'K'};

        for (char c : monsterChars) {
            Cell cell = CellFactory.create(c, p);
            assertTrue(cell instanceof Floor);
            assertTrue(((Floor) cell).getOccupant() instanceof Monster, "Failed for character: " + c);
        }
    }

    @Test
    public void testCreateTrap() {
        Position p = new Position(4, 4);
        Cell cell = CellFactory.create('D', p);

        assertTrue(cell instanceof Floor, "Trap should be placed on a Floor");
        Floor floor = (Floor) cell;

        assertNotNull(floor.getOccupant(), "Floor should have an occupant");
        assertTrue(floor.getOccupant() instanceof Trap, "Occupant should be a Trap");
    }

    @Test
    public void testCreateVariousTraps() {
        Position p = new Position(5, 5);
        char[] trapChars = {'B', 'Q', 'D'};

        for (char c : trapChars) {
            Cell cell = CellFactory.create(c, p);
            assertTrue(cell instanceof Floor);
            assertTrue(((Floor) cell).getOccupant() instanceof Trap, "Failed for trap character: " + c);
        }
    }

    @Test
    public void testInvalidCharacterThrowsException() {
        Position p = new Position(6, 6);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CellFactory.create('X', p);
        });

        assertTrue(exception.getMessage().contains("Unknown character in level file"),
                "Exception message should indicate the unknown character");
    }
}