package units.player;

import utils.Point;

public class Rogue extends Player{

    protected Integer cost;
    protected Integer currEnergy;

    public Rogue(Point point, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints, Integer cost){
        super(point, "Mage", healthPool, healthAmount, attackPoints, defensePoints);

        this.cost = cost;
        this.currEnergy = 100;
    }


}
