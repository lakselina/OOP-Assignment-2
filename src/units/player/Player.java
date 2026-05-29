package units.player;

import units.Unit;
import utils.Point;
import visitor.OccupantVisitor;

public abstract class Player extends Unit {

   private Integer experience;
   private Integer playerLevel;

   public Player(Point point, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints)
   {
       super(point, name,healthPool,healthAmount,attackPoints,defensePoints);

       this.experience = 0;
       this.playerLevel = 1;
   }

    public void levelUp() {
        experience -= 50 * playerLevel;
        playerLevel++;

        healthPool += 10 * playerLevel;
        healthAmount = healthPool;
        attackPoints += 4 * playerLevel;
        defensePoints += playerLevel;

        levelUpSpecifics();
    }

    protected abstract void levelUpSpecifics();

    public Integer getLevel() {
        return playerLevel;
    }

    public Integer getExperience() {
        return experience;
    }

    public abstract void onTick();

    @Override
    public void accept(OccupantVisitor visitor) {
        visitor.visit(this);
    }
}
