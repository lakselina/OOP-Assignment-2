package units.player;

import utils.Position;
import visitor.OccupantVisitor;

public class Hunter extends Player {

    protected int range;
    protected int arrowsCount;
    protected int ticksCount;

    public Hunter (Position point, int healthPool, int healthAmount, int attackPoints, int defensePoints, int range){
        super(point, "Hunter", healthPool, healthAmount, attackPoints, defensePoints);

        this.range = range;
        this.arrowsCount = 10 * getLevel();
        this.ticksCount = 0;
    }

    public void castAbility(OccupantVisitor visitor) {
        if (this.arrowsCount > 0) {
            this.arrowsCount--;
            this.accept(visitor);
        }
    }

    @Override
    public void onTick() {
        this.ticksCount++;
        if (this.ticksCount == 1) {
            this.arrowsCount += getLevel();
            this.ticksCount = 0;
        }
    }

    @Override
    protected void levelUpSpecifics() {
        this.arrowsCount += 10 * getLevel();
        this.attackPoints += 2 * getLevel();
        this.defensePoints += getLevel();
    }

    @Override
    public void accept(OccupantVisitor visitor) {
        visitor.visit(this);
    }

    public int getArrowsCount() {
        return arrowsCount;
    }

    public int getAbilityRange() {
        return range;
    }
}
