package units.enemy;
import board.Cell;
import game.GameBoard;
import utils.Position;
import units.player.Player;
import java.util.Random;

public class Monster extends Enemy{
    private int visionRange;

    public Monster(Position position, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int experienceValue, int visionRange){
        super(position, name, healthPool, healthAmount, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
    }

    @Override
    public void onEnemyTurn(Player player, GameBoard board){
        int dx = 0;
        int dy =0;

        int distance = this.position.distance(player.getPosition());
        if (distance < visionRange){
            int diffX = this.position.getX() - player.getPosition().getX();
            int diffY = this.position.getY() - player.getPosition().getY();
            if (Math.abs(diffX) > Math.abs(diffY)){
                if (diffX > 0){
                    dx = -1;
                }
                else {
                    dx = 1;
                }
            }
            else {
                if (diffY > 0){
                    dy = -1;
                }
                else {
                    dy = 1;
                }
            }
        } else {
            Random rnd = new Random();
            int randomMove = rnd.nextInt(5);
            switch (randomMove){
                case 0: dx = -1; break;
                case 1: dx = 1; break;
                case 2: dy = -1; break;
                case 3: dy = 1; break;
                case 4: break;
            }
        }
        Position newPos = this.position.add(dx, dy);
        Cell targetCell = board.getCell(newPos);
        targetCell.accept(this);
    }

    public int getVisionRange(){
        return visionRange;
    }



}
