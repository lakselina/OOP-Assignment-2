package units.enemy;

import game.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import units.player.Player;
import utils.Position;
import visitor.OccupantVisitor;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTests {

    private Enemy enemy;

    @BeforeEach
    public void setUp() {
        enemy = new DummyEnemy(new Position(0, 0), "DummyEnemy", 50, 10, 0, 100);
    }

    @Test
    public void testEnemyTakesDamage() {
        enemy.defend(20);

        assertTrue(enemy.getHealthAmount() < 50, "Enemy should lose health after taking damage");
        assertEquals(30, enemy.getHealthAmount(), "Enemy should have exactly 30 health left");
    }

    @Test
    public void testEnemyDeath() {
        enemy.defend(100);

        assertFalse(enemy.isAlive(), "Enemy should be dead when health is 0 or below");
        assertEquals(0, enemy.getHealthAmount(), "Health amount should not drop below 0");
    }

    @Test
    public void testExperienceValue() {
        assertEquals(100, enemy.getExperienceValue(), "Enemy should grant exactly 100 experience points");
    }

    private static class DummyEnemy extends Enemy {
        public DummyEnemy(Position position, String name, Integer healthPool, Integer attackPoints, Integer defensePoints, int experienceValue) {
            super(position, name, healthPool,healthPool, attackPoints, defensePoints, experienceValue);
        }

        public void onTick() {
        }

        @Override
        public void accept(OccupantVisitor visitor) {
        }

        @Override
        public void onEnemyTurn(Player player, GameBoard board) {

        }
    }
}