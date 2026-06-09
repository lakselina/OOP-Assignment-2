package units.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import units.player.Warrior;
import utils.Position;

import static org.junit.jupiter.api.Assertions.*;

public class BossTests {

    private Boss boss;
    private Warrior dummyTarget;

    @BeforeEach
    public void setUp() {
        boss = new Boss(new Position(5, 5), "TestBoss", 't',200, 20, 10, 500, 5, 3,3);

        dummyTarget = new Warrior(new Position(1, 1), "Target", 100, 100, 10, 5, 5);
    }

    @Test
    public void testBossCombatTicks() {
        int initialTicks = boss.getCombatTicks();

        boss.onEnemyTurn(dummyTarget);

        assertEquals(initialTicks + 1, boss.getCombatTicks(), "Boss combat ticks should increment on enemy turn");
    }
}