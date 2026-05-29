package units.player;

import utils.Position;
import visitor.OccupantVisitor;


public class Mage extends Player {

    protected int manaPool;
    protected int currMana;
    protected int manaCost;
    protected int spellPower;
    protected int hitsCount;
    protected int abilityRange;

    public Mage(Position point, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange){
        super(point, name, healthPool, healthAmount, attackPoints, defensePoints);

        this.manaPool = manaPool;
        this.currMana = manaPool/4;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    public void castAbility(OccupantVisitor visitor) {
        if (this.currMana >= this.manaCost) {
            this.currMana -= this.manaCost;
            this.accept(visitor);
        }
    }

    protected void levelUpSpecifics(){
        this.manaPool += 25 * this.getLevel();
        this.currMana = Math.min(currMana + (manaPool / 4), manaPool);
        this.spellPower += 10 * this.getLevel();
    }

    public void onTick(){
        this.currMana = Math.min(manaPool, currMana + (this.getLevel()));
    }

    public void accept(OccupantVisitor visitor) {
        visitor.visit((Player)this);
    }

    public int getSpellPower() {
        return spellPower;
    }

    public int getCurrentMana() {
        return currMana;
    }

    public int getAbilityRange(){
        return abilityRange;
    }
}
