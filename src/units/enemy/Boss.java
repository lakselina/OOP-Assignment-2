package units.enemy;

import units.player.Player;
import utils.Position;

public class Boss extends Monster implements HeroicUnit{
    private int abilityFrequency;
    private int combatTicks;

    public Boss(Position position, String name, int healthPool, int healthAmount, int attackPoints, int defensePoints, int experienceValue, int visionRange, int abilityFrequency){
        super(position,name,healthPool,healthAmount,attackPoints,defensePoints,experienceValue,visionRange);
        this.abilityFrequency = abilityFrequency;
        this.combatTicks = 0;
    }

    @Override
    public void castAbility(Player p) {
        int attackRoll = this.roll(this.attackPoints);

        p.defend(attackRoll);
    }

    public void onEnemyTurn(Player player) {
        double distance = this.position.distance(player.getPosition());

        if (distance < this.getVisionRange()) {
            if (this.combatTicks == this.abilityFrequency) {
                this.combatTicks = 0;
                this.castAbility(player);
            } else {
                this.combatTicks++;
            }
        } else {
            this.combatTicks = 0;
        }
    }
}
