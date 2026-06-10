package units.player;

import utils.Position;
import visitor.OccupantVisitor;

public class Rogue extends Player{

    protected int cost;
    protected int currEnergy;
    private final int ENERGY_POOL = 100;

    public Rogue(Position point, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int cost){
        super(point, name, healthPool, healthAmount, attackPoints, defensePoints);

        this.cost = cost;
        this.currEnergy = ENERGY_POOL;
    }

    public void castAbility(OccupantVisitor visitor) {
        if (this.currEnergy >= this.cost) {
            this.currEnergy -= this.cost;
            this.accept(visitor);
        }
    }

    @Override
    public void onTick() {
        this.currEnergy = Math.min(ENERGY_POOL, currEnergy + 10);
    }

    @Override
    protected void levelUpSpecifics() {
        this.currEnergy = ENERGY_POOL;
        this.attackPoints += 3 * getLevel();
    }

    public int getCurrentEnergy() {
        return currEnergy;
    }

    @Override
    public String description() {
        return super.description() + String.format(" | Level: %d | Exp: %d | Energy: %d/100",
                getLevel(), getExperience(), getCurrentEnergy());
    }

}
