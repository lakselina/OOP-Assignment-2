package units.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HunterTests {

    private Hunter hunter;

    @BeforeEach
    public void setUp() {
        hunter = new Hunter(new Position(0, 0), "TestHunter", 100, 20, 5, 3, 3);
    }

    @Test
    public void testHunterLevelUpSpecifics() {
        int initialAttack = hunter.getAttackPoints();
        int initialArrows = hunter.getArrowsCount();

        hunter.levelUp();

        assertEquals(initialAttack + (6 * 2), hunter.getAttackPoints(), "Hunter should get specific attack bonus combined with base bonus");
        assertEquals(initialArrows + (10 * 2), hunter.getArrowsCount(), "Arrows should increase by 10 * level upon leveling up");
    }

    @Test
    public void testTickRestoresArrowsAfterTenTicks() {
        int initialArrows = hunter.getArrowsCount();

        for (int i = 0; i < 10; i++) {
            hunter.onTick();
        }

        assertEquals(initialArrows + hunter.getLevel(), hunter.getArrowsCount(), "Hunter should gain arrows equal to level after 10 ticks");
    }

    @Test
    public void testTickDoesNotRestoreArrowsBeforeTenTicks() {
        int initialArrows = hunter.getArrowsCount();

        for (int i = 0; i < 9; i++) {
            hunter.onTick();
        }

        assertEquals(initialArrows, hunter.getArrowsCount(), "Hunter should NOT gain arrows before exactly 10 ticks have passed");
    }
}