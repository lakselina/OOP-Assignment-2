package units.enemy;

import utils.Point;

public class Monster extends Enemy{
    private Integer visionRange;

    public Monster(Point point, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints, Integer experienceValue, Integer visionRange){
        super(point, name, healthPool, healthAmount, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
    }


}
