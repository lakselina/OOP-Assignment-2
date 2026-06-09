package units.enemy;
import game.GameBoard;
import units.Unit;
import units.player.Player;
import utils.Position;
import visitor.OccupantVisitor;


public abstract class Enemy extends Unit {
    protected int experienceValue;


    public Enemy(Position position, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int experienceValue) {
        super(position, name, healthPool, healthAmount, attackPoints, defensePoints);
        this.experienceValue = experienceValue;
    }

    public int getExperienceValue(){
        return this.experienceValue;
    }

    public void accept(OccupantVisitor visitor){
        visitor.visit(this);
    }

    public abstract void onEnemyTurn(Player player, GameBoard board);

    @Override
    public void visit(Player p){
        this.engageCombat(p);
    }

    @Override
    public void visit(Enemy e){}
}

