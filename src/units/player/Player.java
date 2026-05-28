package units.player;

import units.Unit;
import utils.Point;
import visitor.OccupantVisitor;

public class Player extends Unit {

   public Integer experience;
   public Integer playerLavel;

   public Player(Point point, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints)
   {
       super(point, name,healthPool,healthAmount,attackPoints,defensePoints);

       this.experience = 0;
       this.playerLavel = 1;
   }

   public void levelUp()
   {
       experience += 50 * playerLavel;
       healthPool += 10 * playerLavel;
       healthAmount = healthPool;
       attackPoints += 4 * playerLavel;
       defensePoints += playerLavel;
       playerLavel++;
   }

    @Override
    public void accept(OccupantVisitor visitor) {
        visitor.visit(this);
    }
}
