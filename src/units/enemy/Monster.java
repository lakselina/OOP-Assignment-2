package units.enemy;

import utils.Position;

public class Monster extends Enemy{
    private Integer visionRange;

    public Monster(Position position, String name, Integer healthPool, Integer healthAmount, Integer attackPoints, Integer defensePoints, Integer experienceValue, Integer visionRange){
        super(position, name, healthPool, healthAmount, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
    }


}
