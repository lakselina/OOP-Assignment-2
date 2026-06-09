package units.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RogueTests {

    private Rogue rogue;

    @BeforeEach
    public void setUp() {
        rogue = new Rogue(new Position(0, 0), "TestRogue", 100, 20, 5, 10, 5);
    }

    @Test
    public void testRogueLevelUpSpecifics() {
        int initialAttack = rogue.getAttackPoints();

        rogue.castAbility(null);

        rogue.levelUp();

        assertEquals(initialAttack + (7 * 2), rogue.getAttackPoints(), "Rogue should get specific attack bonus");

        assertEquals(100, rogue.getCurrentEnergy(), "Energy should be completely restored upon leveling up");
    }

    @Test
    public void testEnergyRestorationOnTick() {
        rogue.castAbility(null);

        rogue.onTick();

        assertEquals(60, rogue.getCurrentEnergy(), "Rogue should regenerate exactly 10 energy every tick");

        rogue.castAbility(null);
        rogue.onTick();
        assertEquals(100, rogue.getCurrentEnergy(), "Energy should not exceed the maximum capacity of 100");
    }
}