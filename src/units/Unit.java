package units;

import utils.Point;

public abstract class Unit extends Occupant {

    public String name;
    public Integer healthPool;
    public Integer healthAmount;
    public Integer attackPoints;
    public Integer defensePoints;

    public Unit(Point point, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints)
    {
        super(point);
        this.name = name;
        this.healthPool = healthPool;
        this.healthAmount = healthAmount;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
    }
}
