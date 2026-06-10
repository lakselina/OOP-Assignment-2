package units.player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import units.enemy.Monster;
import utils.Position;
import visitor.OccupantVisitor;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {
    private Player player;
    private Monster dummyEnemy;

    @BeforeEach
    void setUp() {
        player = new DummyPlayer(new Position(0, 0), "TestHero", 100, 20, 0);
        dummyEnemy = new Monster(new Position(1, 0), "TestMonster", 'm',50, 50, 10, 0, 100, 1);
    }

    @Test
    public void testDefendCalculatesDamageCorrectly() {
        player.defend(30);

        assertTrue(player.getHealthAmount() < 100, "Player should lose health after taking damage");
        assertEquals(70, player.getHealthAmount(), "Player should have exactly 70 health left");
    }

    @Test
    public void testPlayerDeath() {
        player.defend(1000);

        assertFalse(player.isAlive(), "Player should be dead when health is 0 or below");
        assertEquals(0, player.getHealthAmount(), "Health amount should not drop below 0");
    }

    @Test
    public void testExperienceGainAndLevelUp() {
        int initialLevel = player.getLevel();

        dummyEnemy.defend(1000);
        player.visit(dummyEnemy);

        assertEquals(initialLevel + 1, player.getLevel(), "Player must level up after gaining enough experience");
        assertEquals(50, player.getExperience(), "Player should have 50 experience left after leveling up");
    }

    @Test
    public void testLevelUpBaseStats() {
        int initialLevel = player.getLevel();
        int initialHealth = player.getHealthPool();
        int initialAttack = player.getAttackPoints();
        int initialDefense = player.getDefensePoints();

        player.levelUp();

        assertEquals(initialLevel + 1, player.getLevel(), "Player level should increase by 1");

        assertEquals(initialHealth + (10 * player.getLevel()), player.getHealthPool(), "Base health pool should increase correctly");
        assertEquals(initialAttack + (4 * player.getLevel()), player.getAttackPoints(), "Base attack should increase correctly");
        assertEquals(initialDefense + (player.getLevel()), player.getDefensePoints(), "Base defense should increase correctly");

        assertEquals(player.getHealthPool(), player.getHealthAmount(), "Health amount should be fully restored upon leveling up");
    }

    private static class DummyPlayer extends Player {
        public DummyPlayer(Position position, String name, Integer healthPool, Integer attackPoints, Integer defensePoints) {
            super(position, name, healthPool, healthPool, attackPoints, defensePoints);
        }

        public void onPlayerTurn() {
        }

        @Override
        protected void levelUpSpecifics() {
        }

        @Override
        public void castAbility(OccupantVisitor visitor) {

        }

        @Override
        public void onTick() {

        }
    }
}
