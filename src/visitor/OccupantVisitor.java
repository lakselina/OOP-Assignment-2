package visitor;

import units.enemy.Enemy;
import units.player.Mage;
import units.player.Player;
import units.player.Warrior;

public interface OccupantVisitor {

    void visit(Player p);
    void visit(Enemy e);

    void performAbility(Warrior w);
    void visit(Mage m);
}
