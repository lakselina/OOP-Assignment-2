package units.player;

import utils.Position;
import visitor.OccupantVisitor;

public class Warrior extends Player {

    private int remainingCooldown;
    private int abilityCooldown;

    public Warrior(Position point, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int abilityCooldown) {
        super(point, name, healthPool, healthAmount, attackPoints, defensePoints);

        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
    }

    public void castAbility(OccupantVisitor visitor) {
        if (remainingCooldown == 0) {
            remainingCooldown = abilityCooldown;
            healthAmount = Math.min(this.healthPool, this.healthAmount + (10 * this.defensePoints));

            if (visitor != null) {
                this.accept(visitor);
            }
        }
    }

    protected void levelUpSpecifics(){
        remainingCooldown = 0;
        healthPool += 5 * this.getLevel();
        attackPoints += 2 * this.getLevel();
        defensePoints += this.getLevel();
    }

    @Override
    public void onTick() {
        if (remainingCooldown > 0) {
            remainingCooldown--;
        }
    }

    @Override
    public String description() {
        return super.description() + String.format(" | Level: %d | Exp: %d | Cooldown: %d",
                getLevel(), getExperience(), remainingCooldown);
    }
}

