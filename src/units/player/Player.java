package units.player;

import units.enemy.Enemy;
import utils.Position;
import units.Unit;
import visitor.OccupantVisitor;

public abstract class Player extends Unit {

   private int experience;
   private int playerLevel;

   public Player(Position point, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints)
   {
       super(point, name, '@', healthPool, healthAmount, attackPoints, defensePoints);

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

    public abstract void castAbility(OccupantVisitor visitor);

    public int getLevel() {
        return playerLevel;
    }

    public int getExperience() {
        return experience;
    }

    public abstract void onTick();

    public void visit(Player p) {
    }

    public void visit(Enemy e) {
        this.engageCombat(e);

        if (!e.isAlive()) {
            this.experience += e.getExperienceValue();

            while (this.experience >= 50 * this.getLevel()) {
                this.levelUp();
            }
        }
    }

    @Override
    public void accept(OccupantVisitor visitor) {
        visitor.visit(this);
    }
}
