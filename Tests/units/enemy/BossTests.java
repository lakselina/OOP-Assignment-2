package units.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import units.player.Warrior;
import utils.Position;

import static org.junit.jupiter.api.Assertions.*;

public class BossTests {

    private Boss boss;
    private Warrior closeTarget;
    private Warrior farTarget;

    @BeforeEach
    public void setUp() {
        boss = new Boss(new Position(5, 5), "TestBoss", 't', 200, 200, 10, 5, 500, 5, 3);

        closeTarget = new Warrior(new Position(4, 5), "Close Target", 100, 100, 10, 5, 5);

        farTarget = new Warrior(new Position(1, 1), "Far Target", 100, 100, 10, 5, 5);
    }

    @Test
    public void testCombatTicksIncrementWhenInRange() {
        assertEquals(0, boss.getCombatTicks(), "Initial combat ticks should be 0");

        boss.onEnemyTurn(closeTarget);

        assertEquals(1, boss.getCombatTicks(), "Boss combat ticks should increment when player is in range");
    }

    @Test
    public void testCombatTicksResetWhenOutOfRange() {
        boss.onEnemyTurn(closeTarget);
        assertEquals(1, boss.getCombatTicks(), "Ticks should be 1 after one close turn");

        boss.onEnemyTurn(farTarget);

        assertEquals(0, boss.getCombatTicks(), "Boss combat ticks should reset to 0 when player is out of range");
    }

    @Test
    public void testCastAbilityResetsTicks() {
        boss.onEnemyTurn(closeTarget);
        boss.onEnemyTurn(closeTarget);
        boss.onEnemyTurn(closeTarget);

        assertEquals(3, boss.getCombatTicks(), "Ticks should reach abilityFrequency (3)");

        boss.onEnemyTurn(closeTarget);

        assertEquals(0, boss.getCombatTicks(), "Boss combat ticks should reset to 0 after casting ability");
    }
}