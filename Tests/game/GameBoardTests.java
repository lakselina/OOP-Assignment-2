package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import board.Cell;
import board.Floor;
import board.Wall;
import units.enemy.Enemy;
import units.enemy.Monster;
import units.enemy.Trap;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class GameBoardTests {

    private GameBoard gameBoard;
    private Cell[][] grid;
    private final int WIDTH = 3;
    private final int HEIGHT = 3;

    @BeforeEach
    public void setUp() {
        grid = new Cell[HEIGHT][WIDTH];
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                grid[y][x] = new Floor(new Position(x, y));
            }
        }
        gameBoard = new GameBoard(grid);
    }

    @Test
    public void testGetCell_ValidPosition() {
        Position p = new Position(2, 1);
        Cell cell = gameBoard.getCell(p);

        assertNotNull(cell);
        assertEquals(grid[1][2], cell, "Should return the correct cell from the grid [y][x]");
    }

    @Test
    public void testGetCell_OutOfBoundsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> gameBoard.getCell(new Position(-1, 0)));
        assertThrows(IllegalArgumentException.class, () -> gameBoard.getCell(new Position(WIDTH, 0)));
    }

    @Test
    public void testAddEnemyAndGetEnemies() {
        Position p = new Position(1, 1);
        Monster monster = new Monster(p, "Gold Cloak", 's', 80, 80, 8, 3, 25, 3);

        gameBoard.addEnemy(monster);

        List<Enemy> enemies = gameBoard.getEnemies();
        assertEquals(1, enemies.size());
        assertTrue(enemies.contains(monster));
    }

    @Test
    public void testGetEnemiesInRange() {
        Monster nearMonster = new Monster(new Position(1, 0), "Gold Cloak", 's', 80, 80, 8, 3, 25, 3);
        Monster farMonster = new Monster(new Position(2, 2), "Knight", 'k', 200, 200, 14, 8, 50, 4);

        gameBoard.addEnemy(nearMonster);
        gameBoard.addEnemy(farMonster);

        List<Enemy> inRange = gameBoard.getEnemiesInRange(new Position(0, 0), 1.5);

        assertEquals(1, inRange.size());
        assertTrue(inRange.contains(nearMonster));
        assertFalse(inRange.contains(farMonster));
    }

    @Test
    public void testFindClosestEnemy() {
        Monster farMonster = new Monster(new Position(2, 0), "Gold Cloak", 's', 80, 80, 8, 3, 25, 3);
        Monster nearMonster = new Monster(new Position(1, 0), "Knight", 'k', 200, 200, 14, 8, 50, 4);

        List<Enemy> enemyList = new ArrayList<>();
        enemyList.add(farMonster);
        enemyList.add(nearMonster);

        Enemy closest = gameBoard.findClosestEnemy(new Position(0, 0), enemyList);
        assertEquals(nearMonster, closest, "Should return the closest enemy");
    }

    @Test
    public void testFindClosestEnemy_EmptyOrNullList() {
        Position p = new Position(0, 0);
        assertNull(gameBoard.findClosestEnemy(p, null));
        assertNull(gameBoard.findClosestEnemy(p, new ArrayList<>()));
    }

    @Test
    public void testPlayerStartingPositionGettersSetters() {
        Position start = new Position(0, 1);
        gameBoard.setPlayerStartingPosition(start);
        assertEquals(start, gameBoard.getPlayerStartingPosition());
    }

    @Test
    public void testRemoveDeadEnemies() {
        Monster monster = new Monster(new Position(1, 1), "Gold Cloak", 's', 80, 80, 8, 3, 25, 3);
        gameBoard.addEnemy(monster);

        monster.defend(100);

        gameBoard.removeDeadEnemies();

        assertEquals(0, gameBoard.getEnemies().size(), "Dead enemy should be removed");
    }
}