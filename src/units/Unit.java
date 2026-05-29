package units;

import utils.Position;
import visitor.OccupantVisitor;

public abstract class Unit extends Occupant {

    protected String name;
    protected Integer healthPool;
    protected Integer healthAmount;
    protected Integer attackPoints;
    protected Integer defensePoints;

    public Unit(Position point, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints)
    {
        super(point);
        this.name = name;
        this.healthPool = healthPool;
        this.healthAmount = healthAmount;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
    }

    public abstract void accept(OccupantVisitor visitor);

    public String getName() {
        return name;
    }

    public Integer getHealthPool() {
        return healthPool;
    }

    public Integer getHealthAmount() {
        return healthAmount;
    }

    public void setHealthAmount(Integer healthAmount){
        this.healthAmount = healthAmount;
    }

    public Integer getAttackPoints() {
        return attackPoints;
    }

    public Integer getDefensePoints() {
        return defensePoints;
    }
}
