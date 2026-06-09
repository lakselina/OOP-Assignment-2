package units;

import board.Floor;
import board.Wall;
import utils.Position;
import visitor.CellVisitor;
import visitor.OccupantVisitor;

public abstract class Unit extends Occupant implements CellVisitor {

    protected String name;
    protected int healthPool;
    protected int healthAmount;
    protected int attackPoints;
    protected int defensePoints;

    public Unit(Position position, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints)
    {
        super(position);
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

    public boolean isAlive() {
        return this.healthAmount > 0;
    }

    public int getHealthPool() {
        return healthPool;
    }

    public int getHealthAmount() {
        return healthAmount;
    }

    public void setHealthAmount(int healthAmount){
        this.healthAmount = healthAmount;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void visit(Wall wall){

    }

    public void visit(Floor floor){
        this.position = floor.getPosition();
    }
}
