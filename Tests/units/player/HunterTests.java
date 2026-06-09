package units.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HunterTests {

    private Hunter hunter;

    @BeforeEach
    public void setUp() {
        hunter = new Hunter(new Position(0, 0), "TestHunter", 100, 20, 5, 3,3);
    }

    @Test
    public void testHunterLevelUpSpecifics() {
        int initialAttack = hunter.getAttackPoints();
        int initialArrows = hunter.getArrowsCount();

        hunter.levelUp();

        assertEquals(initialAttack + (6 * 2), hunter.getAttackPoints(), "Hunter should get specific attack bonus");

        assertEquals(initialArrows + (10 * 2), hunter.getArrowsCount(), "Arrows should increase by 10 * level upon leveling up");
    }

    @Test
    public void testTickRestoresArrows() {
        int initialArrows = hunter.getArrowsCount();

        hunter.onTick();

        assertEquals(initialArrows + hunter.getLevel(), hunter.getArrowsCount(), "Hunter should gain arrows equal to level on tick");
    }
}