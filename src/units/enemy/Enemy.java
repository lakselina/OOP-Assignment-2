package units.enemy;
import units.Unit;
import units.player.Player;
import visitor.OccupantVisitor;
import utils.Point;


public abstract class Enemy extends Unit {
    protected int experienceValue;


    public Enemy(Point point, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints, Integer experienceValue) {
        super(point, name, healthPool, healthAmount, attackPoints, defensePoints);
        this.experienceValue = experienceValue;
    }

    public int getExperienceValue(){
        return this.experienceValue;
    }

    public void accept(OccupantVisitor visitor){
        visitor.visit(this);
    }

    public abstract void onEnemyTurn(Player player);
}

