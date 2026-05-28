package units.player;

import utils.Point;
import visitor.OccupantVisitor;


public class Mage extends Player {

    protected Integer manaPool;
    protected Integer currMana;
    protected Integer manaCost;
    protected Integer spellPower;
    protected Integer hitsCount;
    protected Integer abilityRange;

    public Mage(Point point, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints, Integer manaPool, Integer manaCost, Integer spellPower, Integer hitsCount, Integer abilityRange){
        super(point, "Mage", healthPool, healthAmount, attackPoints, defensePoints);

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

    public Integer getSpellPower() {
        return spellPower;
    }

    public Integer getCurrentMana() {
        return currMana;
    }

    public Integer getAbilityRange(){
        return abilityRange;
    }
}
