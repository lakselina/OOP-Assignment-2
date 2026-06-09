package units.enemy;
import game.GameBoard;
import units.player.Player;
import utils.Position;
import java.util.Random;

public class Trap extends Enemy {
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;
    private char tile;

    public Trap(Position position, String name, char tile, int healthPool, int helathAmount, int attackPoints, int defensePoints, int experienceValue, int visibilityTime, int invisibilityTime){
        super(position, name, tile, healthPool, helathAmount, attackPoints, defensePoints, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;
    }

    public void onEnemyTurn(Player player, GameBoard board){
        this.visible = (this.ticksCount < this.visibilityTime);
        if (this.ticksCount == (this.visibilityTime + this.invisibilityTime)){
            this.ticksCount = 0;
        }
        else{
            this.ticksCount++;
        }

        if (this.position.distance(player.getPosition()) < 2){
            Random rnd = new Random();
            int attackRoll = rnd.nextInt(this.attackPoints + 1);
            player.defend(attackRoll);
        }
    }

    public String toString(){
        if (visible){
            return String.valueOf(this.tile);
        }
        else{
            return ".";
        }
    }
}
