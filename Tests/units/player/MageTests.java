package units.player;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import utils.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MageTests {
    private Mage mage;

    @BeforeEach
    public void setUp() {
        mage = new Mage(new Position(0, 0), "TestMage", 100, 10, 5, 300, 30, 15, 5, 3,5);
    }

    @Test
    public void testMageLevelUpSpecifics() {
        int initialManaPool = mage.getManaPool();
        int initialSpellPower = mage.getSpellPower();

        mage.levelUp();

        assertEquals(initialManaPool + (25 * 2), mage.getManaPool(), "Mage should get specific mana pool bonus");

        assertEquals(initialSpellPower + (10 * 2), mage.getSpellPower(), "Mage should get specific spell power bonus");

        assertTrue(mage.getCurrentMana() > 0, "Current mana should increase upon leveling up");
    }

    @Test
    public void testManaRegenerationOnTick() {
        int currentManaBefore = mage.getCurrentMana();

        mage.onTick();

        assertEquals(String.valueOf(Math.min(mage.getManaPool(), currentManaBefore + mage.getLevel())),
                mage.getCurrentMana(),
                "Mage should regenerate mana based on its level every tick");
    }
}
