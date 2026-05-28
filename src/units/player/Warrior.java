package units.player;

import utils.Point;
import visitor.OccupantVisitor;

public class Warrior extends Player {

    private Integer remainingCooldown;
    private Integer abilityCooldown;

    public Warrior(Point point, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints, Integer abilityCooldown) {
        super(point, "Warrior", healthPool, healthAmount, attackPoints, defensePoints);

        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
    }

    public void castAbility(OccupantVisitor visitor) {
        if (remainingCooldown == 0) {
            remainingCooldown = abilityCooldown;
            healthAmount = Math.min(this.healthPool, this.healthAmount + (10 * this.defensePoints));

            this.accept(visitor);
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
    public void accept(OccupantVisitor visitor) {
            visitor.visit((Player)this);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Level: %d | Exp: %d | Cooldown: %d",
                getLevel(), getExperience(), remainingCooldown);
    }
}

