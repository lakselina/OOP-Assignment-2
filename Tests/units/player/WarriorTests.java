package units.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; // תוקן ל-JUnit 5
import utils.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WarriorTests {
    private Warrior warrior;

    @BeforeEach
    public void setUp() {
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

    @Test
    public void testAbilityCooldownAndHealing() {
        warrior.levelUp();
        int maxHealth = warrior.getHealthPool();
        int currentHealthBeforeCast = warrior.getHealthAmount();

        warrior.castAbility(null);

        assertEquals(maxHealth, warrior.getHealthAmount(), "Warrior should heal up to max health");

        warrior.levelUp();
        int currentHealthAfterLevelUp = warrior.getHealthAmount();

        warrior.castAbility(null);

        assertTrue(warrior.getHealthAmount() > currentHealthAfterLevelUp, "Warrior should be able to cast ability again because level up resets cooldown");
    }
}