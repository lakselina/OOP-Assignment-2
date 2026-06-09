package units.enemy;

import board.Cell;
import game.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import units.player.Warrior;
import utils.Position;

import static org.junit.jupiter.api.Assertions.*;

public class TrapTests {

    private Trap trap;
    private Warrior dummyPlayer;
    private GameBoard dummyBoard;

    @BeforeEach
    public void setUp() {
        trap = new Trap(new Position(0, 0), "TestTrap", 't',100, 10, 5, 50, 3, 2,3);

        dummyPlayer = new Warrior(new Position(1, 1), "Target", 100, 100, 10, 5, 5);

        Cell[][] dummyGrid = new Cell[5][5];

        dummyBoard = new GameBoard(dummyGrid);
    }

    @Test
    public void testTrapVisibilityToggleOnTick() {
        assertTrue(trap.isVisible(), "Trap should initially be visible");

        trap.onEnemyTurn(dummyPlayer, dummyBoard);
        trap.onEnemyTurn(dummyPlayer, dummyBoard);
        trap.onEnemyTurn(dummyPlayer, dummyBoard);

        assertFalse(trap.isVisible(), "Trap should become invisible after visibility time passes");
    }
}