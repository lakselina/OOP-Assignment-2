package units;

import board.Floor;
import board.Wall;
import units.player.Hunter;
import units.player.Mage;
import units.player.Rogue;
import units.player.Warrior;
import utils.Position;
import visitor.CellVisitor;
import visitor.OccupantVisitor;

import java.util.Random;

public abstract class Unit extends Occupant implements CellVisitor, OccupantVisitor {

    protected String name;
    protected int healthPool;
    protected int healthAmount;
    protected int attackPoints;
    protected int defensePoints;
    protected char tile;

    public Unit(Position position, String name, char tile, int healthPool, int healthAmount, int attackPoints, int defensePoints)
    {
        super(position);
        this.name = name;
        this.tile = tile;
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
        Occupant occupant = floor.getOccupant();

        if (occupant == null) {
            this.position = floor.getPosition();

        } else {
            occupant.accept(this);
        }
    }

    protected int roll(int max) {
        return (int) (Math.random() * (max + 1));
    }

    public int defend(int attackDamage) {
        int defenseRoll = roll(this.defensePoints);
        int damageTaken = Math.max(0, attackDamage - defenseRoll);
        this.healthAmount -= damageTaken;
        return damageTaken;
    }

    public void engageCombat(Unit defender) {
        int attackRoll = this.roll(this.attackPoints);
        int damageDealt = defender.defend(attackRoll);
    }

    public void visit(Warrior w){}
    public void visit(Mage m){}
    public void visit(Rogue r){}
    public void visit(Hunter h){}

    public String description() {
        return String.format("%s\t\tHealth: %s/%s\t\tAttack: %d\t\tDefense: %d",
                getName(), getHealthAmount(), getHealthPool(), getAttackPoints(), getDefensePoints());
    }

    @Override
    public String toString(){
        return String.valueOf(this.tile);
    }
}
