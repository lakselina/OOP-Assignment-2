package units.player;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import utils.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WarriorTests {
    private Warrior warrior;

    @BeforeEach
    void setUp() {
        warrior = new Warrior(new Position(0, 0), "TestWarrior", 100, 100, 20, 10, 5);
    }

    @Test
    public void testWarriorLevelUpSpecifics() {
        int initialHealth = warrior.getHealthPool();
        int initialAttack = warrior.getAttackPoints();
        int initialDefense = warrior.getDefensePoints();

        warrior.levelUp();

        assertEquals(initialHealth + 30, warrior.getHealthPool(), "Warrior should get extra health bonus");

        assertEquals(initialAttack + 12, warrior.getAttackPoints(), "Warrior should get extra attack bonus");

        assertEquals(initialDefense + 4, warrior.getDefensePoints(), "Warrior should get extra defense bonus");
    }
}
